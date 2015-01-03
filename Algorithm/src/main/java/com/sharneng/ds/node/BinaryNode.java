package com.sharneng.ds.node;

public interface BinaryNode<E extends BinaryNode<E>> extends OrderedNode<E> {
	E getLeft();
	void setLeft(E e);
	
	E getRight();
	void setRight(E e);
}
