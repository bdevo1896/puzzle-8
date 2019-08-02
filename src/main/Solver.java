package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * This class will be used to create a working Puzzle 8 solution set. It will hold a solution list.
 * It will have a recursive method to find a solution from a certain state.
 * @author Bryce DeVaughn
 *
 */
public class Solver {
	/**
	 * This is the check node to see if the solution has been found yet
	 */
	private StateNode goalNode;
	/**
	 * This is a list of array lists that contains previously checked nodes according to 
	 */
	ArrayList<StateNode> oldNodes;

	public Solver() {
		this.goalNode = new StateNode();
		this.goalNode.createGoalState();
		this.oldNodes = new ArrayList<StateNode>();
	}
	
	/**
	 * This method will find the solution to the Puzzle 8 state that is inputed.
	 * @param children is a list containing all of the possibilities from the current node
	 * @return
	 */
	public StateNode findSolution(StateNode puzzle){
		ArrayList<StateNode> list = new ArrayList<StateNode>();
		oldNodes.clear();
		list.add(puzzle);
		return find(list);
	}
	
	/**
	 * This will iterate through the children list to find the solution, if it is
	 * not found then all of the children from the 
	 * @param children
	 * @return
	 */
	public StateNode find(ArrayList<StateNode> children){
		ArrayList<StateNode> cList = new ArrayList<StateNode>();//Holds on to the next nodes to use
		for(StateNode n: children){
			if(n.compareTo(goalNode) == 0){
				return n;
			}
			cList.addAll(n.getAllPossibleStates());//Adds the next nodes to check
			oldNodes.add(n);//adds the just checked node to the old list
			
		}
		
		/*
		 * This goes through the list of the past nodes and removes them from the new cList so that it doesn't go backwards
		 */
		for(StateNode s: oldNodes){
			for(int i = 0; i < cList.size(); i++){				
				if(cList.get(i).compareTo(s)==0){
					cList.remove(i);
				}
			}
		}
		
		
		return find(cList);
	}
	
	/**
	 *This method will put the nodes on the path to the solution in a list. Returns the size of the list and changes it
	 * @param list
	 * @param n
	 * @return list (with all nodes)
	 */
	public int getSolutionPath(LinkedList<StateNode> list, StateNode n){
		if(n.getParent() != null){
			list.add(n);
			return getSolutionPath(list, n.getParent());
		}
		
		return list.size();
	}


	public StateNode getGoalNode() {
		return goalNode;
	}
}
