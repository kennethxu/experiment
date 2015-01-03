package com.sharneng.ds.node;

import java.util.AbstractList;

public abstract class BinaryNodeBase<E extends BinaryNodeBase<E>> extends AbstractList<E> implements BinaryNode<E> {

	private E left;
	private E right;

	public BinaryNodeBase() {
		super();
	}

	public E getLeft() {
		return left;
	}

	public void setLeft(E left) {
		this.left = left;
	}

	public E getRight() {
		return right;
	}

	public void setRight(E right) {
		this.right = right;
	}

	@Override
	public E get(int index) {
		switch(index) {
		case 0: return left;
		case 1: return right;
		default:
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
	}

	@Override
	public int size() {
		return 2;
	}

}