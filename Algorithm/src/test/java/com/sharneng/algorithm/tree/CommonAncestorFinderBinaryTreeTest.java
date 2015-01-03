package com.sharneng.algorithm.tree;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import com.sharneng.ds.node.BinaryNode;
import com.sharneng.ds.node.BinaryNodeBase;

public class CommonAncestorFinderBinaryTreeTest {
	private static class BNode extends BinaryNodeBase<BNode> implements BinaryNode<BNode>{
	}

	//         .______[2]______.
	//     .__[3]__.       .__[8]__.
	//  ._[7]_.   [9]    [6]     [5]
	// [0]   [1]_.
	//          [4] 
	BNode root;
	BNode[] nodes = new BNode[10];
	
	public CommonAncestorFinderBinaryTreeTest() {
		nodes[4] = newNode(null, null);
		nodes[1] = newNode(null, nodes[4]);
		nodes[0] = newNode(null, null);
		nodes[7] = newNode(nodes[0], nodes[1]);
		nodes[9] = newNode(null, null);
		nodes[3] = newNode(nodes[7], nodes[9]);
		nodes[6] = newNode(null, null);
		nodes[5] = newNode(null, null);
		nodes[8] = newNode(nodes[6], nodes[5]);
		nodes[2] = newNode(nodes[3], nodes[8]);
		root = nodes[2];
	}
	
	private BNode newNode(BNode left, BNode right)
	{
		BNode n = new BNode();
		n.setLeft(left);
		n.setRight(right);
		return n;
	}
	
	@Test
	public void findCommonAncestor_returnNull_onNullTree() {
		Assert.assertThat(CommonAncestorFinder.findInTree(null, nodes[0], nodes[2]), nullValue());
	}
	
	@Test
	public void findCommonAncestor_returnsParentN1() {
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[1], nodes[4]), is(nodes[1]));
	}
	
	@Test
	public void findCommonAncestor_returnsParentN2() {
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[0], nodes[3]), is(nodes[3]));
	}
	
	@Test
	public void findCommonAncestor_returnsSubtree() {
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[9], nodes[4]), is(nodes[3]));
	}
	
	@Test
	public void findCommonAncestor_returnsRoot() {
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[5], nodes[7]), is(nodes[2]));
	}
	
	@Test
	public void findCommonAncestor_returnsNull_whenNoN1() {
		Assert.assertThat(CommonAncestorFinder.findInTree(root, newNode(null, null), nodes[4]), nullValue());
	}
	
	@Test
	public void findCommonAncestor_returnsNull_whenNoN2() {
		Assert.assertThat(CommonAncestorFinder.findInTree(root, nodes[9], newNode(null, null)), nullValue());
	}
}
