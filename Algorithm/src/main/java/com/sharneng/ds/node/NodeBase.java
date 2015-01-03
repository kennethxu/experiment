package com.sharneng.ds.node;

import java.util.ArrayList;

public class NodeBase<E extends NodeBase<E>> extends ArrayList<E> implements OrderedNode<E>{
	private static final long serialVersionUID = 5123465269893550266L;
}
