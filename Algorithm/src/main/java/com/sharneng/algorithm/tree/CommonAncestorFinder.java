package com.sharneng.algorithm.tree;

import java.util.BitSet;
import java.util.IdentityHashMap;
import java.util.Map;

import com.sharneng.ds.node.Node;

public class CommonAncestorFinder<E extends Node<E>> {
	private final static BitSet EMPTY = new BitSet(0);

	private E result;
	private E[] nodes;
	private long found;
	
	private CommonAncestorFinder(E[] nodes) {
		if (nodes == null)
			throw new NullPointerException("parameter nodes must not be null");
		for(int i = 0; i<nodes.length; i++)
		if (nodes[i] == null) {
			throw new NullPointerException("parameter nodes has null element at index " + i);
		}
		this.nodes = nodes;
	}

	@SafeVarargs
	public static <E extends Node<E>> E findInGraph(E graph, E... nodes) {
		CommonAncestorFinder<E> finder = new CommonAncestorFinder<E>(nodes);
		if (nodes.length <= 62) {
			finder.found = (1L << nodes.length) - 1;
			finder.findInGraphFast(graph);
		} else {
			finder.findInGraph(graph);
		}
		return finder.result;
	}

	@SafeVarargs
	public static <E extends Node<E>> E findInTreex(E tree, E... nodes) {
		CommonAncestorFinder<E> finder = new CommonAncestorFinder<E>(nodes);
		finder.findInTree(tree);
		return finder.result;
	}
	

public static <E extends Node<E>> E findInTree(E tree, E n1, E n2) {
    if(tree==null)
        return null;
    if(tree==n1 || tree==n2)
        return tree;
    if(n1==null || n2==null)
        return null;
    Map<E, E> parents=new IdentityHashMap<E, E>();
    Map<E, Integer> levels=new IdentityHashMap<E, Integer>();
    levels.put(tree, 1);
    DFS(tree, parents, levels);
    if(!levels.containsKey(n1) || !levels.containsKey(n2))
        return null;
    int level1=levels.get(n1);
    int level2=levels.get(n2);
    while(level1<level2){
        n2=parents.get(n2);
        level2--;
    }
    while(level1>level2)
    {
        n1=parents.get(n1);
        level1--;
    }
    while(n1!=n2){
        n1=parents.get(n1);
        n2=parents.get(n2);
    }
    return n1;
}

private static <E extends Node<E>> void DFS(E node, Map<E, E> parents, Map<E, Integer> levels){
    if(node==null)
        return;
    int level=levels.get(node);
    for(E child : node){
        if(child!=null)
        {
            parents.put(child, node);
            levels.put(child, level+1);
            DFS(child, parents, levels);
        }
    }
}
	
	private BitSet findInGraph(E graph) {
		if (graph == null) return EMPTY;
		int count = nodes.length;
		BitSet flags = new BitSet(count);
		int i = 0;
		for(E n : nodes) {
			flags.set(i++, n==graph);
		}
		for (E child : graph) {
			if (flags.cardinality() == count) break;
			BitSet f = findInGraph(child);
			if (f.cardinality() == count) return f;
			flags.or(f);
		}
		if (flags.cardinality() == count) result = graph;
		return flags;
	}
	
	private long findInGraphFast(E graph) {
		if (graph == null) return 0;
		long flags = 0;
		for(int i = nodes.length -1; i>=0; i--)
		{
			flags <<= 1;
			flags |= nodes[i] == graph ? 1 : 0;
		}
		for (E child : graph) {
			if (flags == found) break;
			long f = findInGraphFast(child);
			if (f == found) return f;
			flags |= f;
		}
		if (flags == found) result = graph;
		return flags;
	}

	private int findInTree(E tree) {
		if (tree == null) return 0;
		int count = 0;
		for(E n : nodes) {
			count += (n == tree) ? 1 : 0;
		}
		for (E child : tree) {
			if (count == nodes.length) break;
			int c = findInTree(child);
			if (c == nodes.length) return c;
			count += c;
		}
		if (count == nodes.length) result = tree;
		return count;
	}

}
