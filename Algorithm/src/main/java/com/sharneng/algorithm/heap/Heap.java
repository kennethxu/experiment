package com.sharneng.algorithm.heap;

public interface Heap<E> {
    E pollAndOffer(E e);

    public abstract int size();

    public abstract E peek();

    public abstract E poll();

    public abstract boolean offer(E e);
}
