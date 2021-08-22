package bigint;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in this integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 *    
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 *    5 --> 3 --> 2  (No zeros after the last 2)        
	 */
	DigitNode front;
	
	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first 
	 * character (no sign means positive), and at least one digit character
	 * (including zero). 
	 * Examples of correct format, with corresponding values
	 *      Format     Value
	 *       +0            0
	 *       -0            0
	 *       +123        123
	 *       1023       1023
	 *       0012         12  
	 *       0             0
	 *       -123       -123
	 *       -001         -1
	 *       +000          0
	 *       
	 * Leading and trailing spaces are ignored. So "  +123  " will still parse 
	 * correctly, as +123, after ignoring leading and trailing spaces in the input
	 * string.
	 * 
	 * Spaces between digits are not ignored. So "12  345" will not parse as
	 * an integer - the input is incorrectly formatted.
	 * 
	 * An integer with value 0 will correspond to a null (empty) list - see the BigInteger
	 * constructor
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer.
	 * @throws IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) throws IllegalArgumentException {
		if (integer == null) throw new IllegalArgumentException("integer most not be null");

		// removing leading and trailing spaces and keep the original input for error messaging.
		String s = integer.trim();
		if (s.length() == 0 || (s.length() == 1 && ! Character.isDigit(s.charAt(0)))) {
			throw new IllegalArgumentException('"' + integer + '"' + ": is not a valid integer");
		}

		BigInteger i = new BigInteger();

		int index = 0;

		// determine the sign based on the first char
		char first = s.charAt(index);
		if (first == '+' || first == '-') {
			i.negative = (first == '-');
			index++;
		}

		while(index < s.length() && s.charAt(index) == '0') index++; // skip leading 0s

		// from now on, everything remaining should be significant digits.
		while (index < s.length()) {
			char c = s.charAt(index++);
			if (Character.isDigit(c)) i.front = new DigitNode(c - '0', i.front);
			else throw new IllegalArgumentException('"' + integer + '"' + ": is not a valid integer");
			i.numDigits++;
		}

		if (i.front == null) i.negative = false; // 0 is always positive
		
		return i;
	}
	
	/**
	 * Adds the first and second big integers, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative.
	 * (Which means this method can effectively subtract as well.)
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	public static BigInteger add(BigInteger first, BigInteger second) {
		return normalize(forceAdd(first, second));

	}

	// perform addition and determine negativity. Only works with two any normalized BigInteger, or unnormalized
	private static BigInteger forceAdd(BigInteger first, BigInteger second) {
		BigInteger i = new BigInteger();
		int sign1 = first.sign(), sign2 = second.sign();
		i.front = new DigitNode(0, null); // dummy
		for(DigitNode n1 = first.front, n2 = second.front, n = i.front; n1 != null || n2 !=null; n = n.next) {
			int value = 0;
			if (n1 != null) {
				value += n1.digit*sign1;
				n1 = n1.next;
			}
			if (n2 != null) {
				value += n2.digit*sign2;
				n2 = n2.next;
			}
			n.next = new DigitNode(value, null);
			if (value != 0) i.negative = (value < 0);
			i.numDigits++;
		}
		i.front = i.front.next; //skip the dummy
		return i;
	}

	// adjust borrow and carry, track leading 0
	private static BigInteger normalize(BigInteger i) {
		int carry = 0, leadingZeroCount = 0, sign = i.sign();
		DigitNode last = i.front, highest = null;
		for(DigitNode n = i.front; n != null; n = n.next) {
			last = n;
			int sum = n.digit * sign + carry;
			n.digit = Math.floorMod(sum, 10);
			carry = Math.floorDiv(sum, 10);
			if (n.digit != 0) {
				highest = n;
				leadingZeroCount = 0;
			}
			else leadingZeroCount++;
		}
		if (carry == 0) {
			// remove leading 0s
			i.numDigits -= leadingZeroCount;
			if (highest == null) {
				i.front = null;
				i.negative = false;
			} else highest.next = null;
		} else for(DigitNode n = last; carry > 0; carry /= 10, n = n.next, i.numDigits++){
			n.next = new DigitNode(carry % 10, null);
		}
		return i;
	}

	private int sign() {
		return negative ? -1 : 1;
	}
	
	/**
	 * Returns the BigInteger obtained by multiplying the first big integer
	 * with the second big integer
	 * 
	 * This method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big integers
	 */
	public static BigInteger multiply(BigInteger first, BigInteger second) {
		
		BigInteger sum = new BigInteger();
		int z = 0;
		for(DigitNode n1 = first.front, dummy = new DigitNode(0, null), trailingZeros = dummy;
			n1 != null;
			n1 = n1.next, trailingZeros = new DigitNode(0, trailingZeros)) {
			BigInteger i = new BigInteger();
			i.numDigits = z++;
			i.front = trailingZeros;
			for(DigitNode n2 = second.front, n = dummy; n2 != null; n2 = n2.next, n = n.next) {
				int value = n1.digit * n2.digit;
				n.next = new DigitNode(value, null);
				i.numDigits++;
			}
			i.front = i.front.next; //skip the dummy
			sum = forceAdd(sum, i);
		}

		normalize(sum);

		sum.negative = first.negative ^ second.negative;

		return sum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		return retval;
	}
}
