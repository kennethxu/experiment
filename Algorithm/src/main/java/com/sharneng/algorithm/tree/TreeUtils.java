package com.sharneng.algorithm.tree;

import java.util.Comparator;

import org.apache.commons.collections4.comparators.ComparableComparator;

import com.sharneng.ds.node.BinaryNode;

public class TreeUtils {
	private static class OrderChecker<E> {
		private Comparator<? super E> comparator;
		private E last;
		public OrderChecker(Comparator<? super E> comparator) {
			this.comparator = comparator;
		}
		public boolean isAscending(E current) {
			boolean isAscending = last == null || comparator.compare(last, current) <= 0;
			this.last = current;
			return isAscending;
		}
	}
	
	public static <E extends BinaryNode<E>> boolean isBinarySearchTree(E tree) {
		if (tree == null) return true;
		if (!(tree instanceof Comparable<?>)) throw new IllegalArgumentException("tree is not comparable.");
		@SuppressWarnings({"unchecked" })
		Comparator<? super E> comparator = (Comparator<? super E>)  ComparableComparator.INSTANCE;
		return isBinarySearchTree(tree, comparator);
	}
	
	public static <E extends BinaryNode<E>> boolean isBinarySearchTree(E tree, Comparator<? super E> comparator) {
		return isBinarySearchTree(tree, new OrderChecker<E>(comparator));
	}

	public static <E extends BinaryNode<E>> boolean isBinarySearchTree(E tree, OrderChecker<E> tracker) {
		return tree == null || 
				isBinarySearchTree(tree.getLeft(), tracker) && 
				tracker.isAscending(tree) &&
				isBinarySearchTree(tree.getRight(), tracker);
	}
}
