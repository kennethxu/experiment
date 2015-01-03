package com.sharneng.ds.node;

public class NodeWithPayload<E> extends NodeBase<NodeWithPayload<E>> {
	
	private static final long serialVersionUID = 7321550075820486369L;
	
	private E payload;

	public E getPayload() {
		return payload;
	}

	public void setPayload(E e) {
		payload = e;
	}
}
