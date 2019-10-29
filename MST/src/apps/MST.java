package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		/* COMPLETE THIS METHOD */
		PartialTreeList ptl = new PartialTreeList();
		for(Vertex v : graph.vertices) {
			PartialTree t = new PartialTree(v);
			Vertex.Neighbor ptr = v.neighbors;
			while(ptr!=null) {
				t.getArcs().insert(new PartialTree.Arc(v, ptr.vertex, ptr.weight));
				ptr=ptr.next;
			}
			ptl.append(t);
		}
		return ptl;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */
		ArrayList<PartialTree.Arc> mst = new ArrayList<PartialTree.Arc>();
		
		while(ptlist.size()>1) {
			PartialTree first = ptlist.remove();
			PartialTree removed = null;
			PartialTree.Arc arc = first.getArcs().deleteMin();
			Vertex v1 = arc.v1;
			Vertex v2 = arc.v2;
			while(!v1.parent.equals(v1)) {v1 = v1.parent;}			
			while(!v2.parent.equals(v2)) {v2 = v2.parent;}
			if(v1.parent.equals(v2.parent)) {
				while(v1.parent.equals(v2.parent)) {
					arc = first.getArcs().deleteMin();
					v1 = arc.v1;
					v2 = arc.v2;
					while(!v2.parent.equals(v2)) {v2 = v2.parent;}
					while(!v1.parent.equals(v1)) {v1 = v1.parent;}
				}
			}
			boolean inList = false;
			for(PartialTree p: ptlist) {
				if(p.getRoot().equals(v2)) {inList = true;}
			}
			if(!inList) {v2 = v2.parent;}
			mst.add(arc);
			for(PartialTree p: ptlist) {
				if(p.getRoot().equals(v2)) {removed = ptlist.removeTreeContaining(p.getRoot());}
			}
			first.merge(removed);
			ptlist.append(first);
		}
		
		System.out.print("Final MST: ");
		for(PartialTree.Arc ar : mst) {
			System.out.print(ar.toString()+" ");
		}
		System.out.println();
		
		return mst;
	}
}
