package com.sharneng.algorithm.heap;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * @author Kenneth Xu
 * 
 */
public abstract class AbstractPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    protected int modCount;

    @Override
    public E peek() {
        return size() > 0 ? elementAt(0) : null;
    }

    protected abstract E elementAt(int i);

    /**
     * Version of remove using reference equality, not equals. Needed by iterator.remove.
     * 
     * @param o
     *            element to be removed from this queue, if present
     * @return {@code true} if removed
     */
    protected abstract boolean removeEq(E o);

    /**
     * Removes the ith element from queue.
     * 
     * Normally this method leaves the elements at up to i-1, inclusive, untouched. Under these circumstances, it
     * returns null. Occasionally, in order to maintain the heap invariant, it must swap a later element of the list
     * with one earlier than i. Under these circumstances, this method returns the element that was previously at the
     * end of the list and is now at some position before i. This fact is used by iterator.remove so as to avoid missing
     * traversing elements.
     */
    protected abstract E removeAt(int i);

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        /**
         * Index (into queue array) of element to be returned by subsequent call to next.
         */
        private int cursor = 0;

        /**
         * Index of element returned by most recent call to next, unless that element came from the forgetMeNot list.
         * Set to -1 if element is deleted by a call to remove.
         */
        private int lastRet = -1;

        /**
         * A queue of elements that were moved from the unvisited portion of the heap into the visited portion as a
         * result of "unlucky" element removals during the iteration. (Unlucky element removals are those that require a
         * siftup instead of a siftdown.) We must visit all of the elements in this list to complete the iteration. We
         * do this after we've completed the "normal" iteration.
         * 
         * We expect that most iterations, even those involving removals, will not need to store elements in this field.
         */
        private ArrayDeque<E> forgetMeNot = null;

        /**
         * Element returned by the most recent call to next iff that element was drawn from the forgetMeNot list.
         */
        private E lastRetElt = null;

        /**
         * The modCount value that the iterator believes that the backing Queue should have. If this expectation is
         * violated, the iterator has detected concurrent modification.
         */
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < size() || (forgetMeNot != null && !forgetMeNot.isEmpty());
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
            if (cursor < size()) return elementAt(lastRet = cursor++);
            if (forgetMeNot != null) {
                lastRet = -1;
                lastRetElt = forgetMeNot.poll();
                if (lastRetElt != null) return lastRetElt;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
            if (lastRet != -1) {
                E moved = removeAt(lastRet);
                lastRet = -1;
                if (moved == null) cursor--;
                else {
                    if (forgetMeNot == null) forgetMeNot = new ArrayDeque<>();
                    forgetMeNot.add(moved);
                }
            } else if (lastRetElt != null) {
                removeEq(lastRetElt);
                lastRetElt = null;
            } else {
                throw new IllegalStateException();
            }
            expectedModCount = modCount;
        }
    }
}
