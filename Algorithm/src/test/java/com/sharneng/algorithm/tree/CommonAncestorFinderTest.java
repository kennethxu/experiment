package com.sharneng.algorithm.tree;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import com.sharneng.ds.node.NodeBase;

public class CommonAncestorFinderTest {
	private static class MyNode extends NodeBase<MyNode> {
		private static final long serialVersionUID = 1L;
	}
	
	//         .______[2]______.
	//     .__[3]__.       .__[8]__.
	//  ._[7]_.   [9]     [6]     [5]
	// [0]   [1]_.
	//          [4] 
	MyNode root;
	MyNode[] nodes = new MyNode[10];
	
	public CommonAncestorFinderTest() {
		nodes[4] = newNode();
		nodes[1] = newNode(null, nodes[4]);
		nodes[0] = newNode();
		nodes[7] = newNode(nodes[0], nodes[1]);
		nodes[9] = newNode();
		nodes[3] = newNode(nodes[7], nodes[9]);
		nodes[6] = newNode();
		nodes[5] = newNode();
		nodes[8] = newNode(nodes[6], nodes[5]);
		nodes[2] = newNode(nodes[3], nodes[8]);
		root = nodes[2];
	}
	
	private MyNode newNode(MyNode... children)
	{
		MyNode n = new MyNode();
		for(MyNode c : children) n.add(c);
		return n;
	}
	
	@Test
	public void findCommonAncestor_returnNull_onNullTree() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(null, nodes[0], nodes[2]), nullValue());
	}
	
	@Test
	public void findCommonAncestor_returnsParentN1() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(root, nodes[1], nodes[4]), is(nodes[1]));
	}
	
	@Test
	public void findCommonAncestor_returnsParentN2() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(root, nodes[0], nodes[3]), is(nodes[3]));
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[0], nodes[3]), is(nodes[3]));
	}
	
	@Test
	public void findCommonAncestor_returnsSubtree() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(root, nodes[9], nodes[4]), is(nodes[3]));
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[9], nodes[4]), is(nodes[3]));
	}
	
	@Test
	public void findCommonAncestor_returnsRoot() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(root, nodes[5], nodes[7]), is(nodes[2]));
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[5], nodes[7]), is(nodes[2]));
	}
	
	@Test
	public void findCommonAncestor_returnsNull_whenNoN1() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(root, newNode(), nodes[4]), nullValue());
		Assert.assertThat(CommonAncestorFinder.findInTree(root, newNode(), nodes[4]), nullValue());
	}
	
	@Test
	public void findCommonAncestor_returnsNull_whenNoN2() {
		Assert.assertThat(CommonAncestorFinder.findInGraph(root, nodes[9], newNode()), nullValue());
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[9], newNode()), nullValue());
	}
}
