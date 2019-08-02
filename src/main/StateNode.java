package main;

import java.util.LinkedList;

/**
 * This node will contain a 2D grid of the current values. It will be able to be 
 * compared with other StateNodes to see if they contain the same state
 * @author Bryce DeVaughn
 *
 */
public class StateNode implements Comparable<StateNode> {
	
	/**
	 * This 2D int array will contain the values for the current State of the Puzzle 8
	 */
	private int[][] grid;
	/**
	 * This class will hold up to four nodes as children
	 */
	private StateNode cOne,cTwo,cThree,cFour;
	
	/**
	 * This will be the parent of the node
	 */
	private StateNode parent;
	
	/**
	 * This is the base length of the side on the 2D grid
	 */
	private static final int LENGTH = 3;

	public StateNode() {
		this.grid = new int[LENGTH][LENGTH];
		this.cOne = null;
		this.cTwo = null;
		this.cThree = null;
		this.cFour = null;
		this.parent = null;
	}
	
	public StateNode(int[][] grid, StateNode parent){
		this.grid = grid;
		this.cOne = null;
		this.cTwo = null;
		this.cThree = null;
		this.cFour = null;
		this.parent = parent;
	}
	
	/**
	 * This method will return the coordinates of the blank (zero) spot.
	 */
	public Coord getBlankSpot(){
		int r = 0;
		int c = 0;
		for(int i = 0; i < LENGTH; i++){
			for(int j = 0; j < LENGTH; j++){
				if(grid[i][j] == 0){
					r = i;
					c = j;
				}
			}
		}
		return new Coord(r,c);
	}
	
	/**
	 * This method will return a LinkedList of all of the possible states from the current one.
	 * Then the children of this node will be set according to the different nodes
	 * -THIS IS THE WRAPPER METHOD-
	 */
	public LinkedList<StateNode> getAllPossibleStates(){
		LinkedList<StateNode> list = new LinkedList<StateNode>();
		Coord blankSpot = getBlankSpot();
		return getAllPossibleStates(blankSpot.getRow(),blankSpot.getCol(),list);
	}

	private LinkedList<StateNode> getAllPossibleStates(int r, int c, LinkedList<StateNode> list) {
		/*
		 * Finding the state for an upward move
		 */
		if(r - 1 >= 0){
			int[][] newGrid = moveUp(r,c);
			cOne = new StateNode(newGrid,this);
			if(cOne != null)
				list.add(cOne);
		}
		
		/*
		 * Finding the state for a downward move
		 */
		if(r + 1 < LENGTH){
			int[][] newGrid = moveDown(r,c);
			cTwo = new StateNode(newGrid,this);
			if(cTwo != null)
				list.add(cTwo);
		}
		
		/*
		 * Finding the state for an leftward move
		 */
		if(c - 1 >= 0){
			int[][] newGrid = moveLeft(r,c);
			cThree = new StateNode(newGrid,this);
			if(cThree != null)
				list.add(cThree);
		}
		
		/*
		 * Finding the state for a rightward move
		 */
		if(c + 1 < LENGTH){
			int[][] newGrid = moveRight(r,c);
			cFour = new StateNode(newGrid,this);
			if(cFour != null)
				list.add(cFour);
		}
		
		return list;
	}
	
	/**
	 * This method will return the state after an upward move based off of the current state
	 * @param r is the row index of the blank spot
	 * @param c is the column index of the blank spot
	 * @return the new state's grid
	 */
	public int[][] moveUp(int r, int c){
		int[][] rtnGrid = new int[LENGTH][LENGTH];
		rtnGrid = copyArray();//Duplicating the current state's grid so it isn't modified
		if(r-1 < 0){
			return null;
		}
		int temp = rtnGrid[r-1][c];//The spot that the blank space will move to
		/*
		 * Swapping the blank space and the spot the space is moving to
		 */
		rtnGrid[r-1][c] = rtnGrid[r][c];
		rtnGrid[r][c] = temp;
		
		
		return rtnGrid;
	}
	
	/**
	 * This method will return the state after an upward move based off of the current state
	 * @param r is the row index of the blank spot
	 * @param c is the column index of the blank spot
	 * @return the new state the
	 */
	public int[][] moveDown(int r, int c){
		int[][] rtnGrid = new int[LENGTH][LENGTH];
		rtnGrid = copyArray();//Duplicating the current state's grid so it isn't modified
		if(r+1 >= LENGTH){
			return null;
		}
		int temp = rtnGrid[r+1][c];//The spot that the blank space will move to
		/*
		 * Swapping the blank space and the spot the space is moving to
		 */
		rtnGrid[r+1][c] = rtnGrid[r][c];
		rtnGrid[r][c] = temp;
		
		
		return rtnGrid;
	}
	
	/**
	 * This method will return the state after an upward move based off of the current state
	 * @param r is the row index of the blank spot
	 * @param c is the column index of the blank spot
	 * @return the new state the
	 */
	public int[][] moveRight(int r, int c){
		int[][] rtnGrid = new int[LENGTH][LENGTH];
		rtnGrid = copyArray();//Duplicating the current state's grid so it isn't modified
		if(c+1 >= LENGTH){
			return null;
		}
		int temp = rtnGrid[r][c+1];//The spot that the blank space will move to
		/*
		 * Swapping the blank space and the spot the space is moving to
		 */
		rtnGrid[r][c+1] = rtnGrid[r][c];
		rtnGrid[r][c] = temp;
		
		
		return rtnGrid;
	}
	
	/**
	 * This method will return the state after an upward move based off of the current state
	 * @param r is the row index of the blank spot
	 * @param c is the column index of the blank spot
	 * @return the new state the
	 */
	public int[][] moveLeft(int r, int c){
		int[][] rtnGrid = new int[LENGTH][LENGTH];
		rtnGrid = copyArray();//Duplicating the current state's grid so it isn't modified
		if(c-1 < 0)
			return null;
		int temp = rtnGrid[r][c-1];//The spot that the blank space will move to
		
		/*
		 * Swapping the blank space and the spot the space is moving to
		 */
		rtnGrid[r][c-1] = rtnGrid[r][c];
		rtnGrid[r][c] = temp;
		
		
		return rtnGrid;
	}

	/**
	 * This method will set the grid of this class' instance to that of the goal state
	 */
	public void createGoalState(){
		grid[0][0] = 1;//Top left
		grid[0][1] = 2;//Top mid
		grid[0][2] = 3;//Top right
		grid[1][0] = 4;//Mid left
		grid[1][1] = 5;//Center
		grid[1][2] = 6;//Mid right
		grid[2][0] = 7;//Bottom left
		grid[2][1] = 8;//Bottom mid
		grid[2][2] = 0;//Bottom right
	}

	@Override
	public int compareTo(StateNode n) {
		int rtnVal = 0;
		
		for(int i = 0; i < LENGTH; i++){
			for(int j = 0; j < LENGTH; j++){
				if(grid[i][j] != n.getValAtLoc(i,j)){
					rtnVal++;
				}
			}
		}
		
		return rtnVal;
	}
	
	/**
	 * Returns a new 2D int array with all of the values copied over from the grid field
	 * @return
	 */
	private int[][] copyArray(){
		int[][] rtnGrid = new int[LENGTH][LENGTH];
		for(int i = 0; i < LENGTH; i++){
			for(int j = 0; j < LENGTH; j++){
				rtnGrid[i][j] = grid[i][j];
			}
		}
		return rtnGrid;
	}
	
	/**
	 * This method returns the value in the grid at the specified row and column.
	 * @param i
	 * @param j
	 * @return grid[i][j]
	 */
	public int getValAtLoc(int i, int j) {
		
		if(i < 0 || i >= LENGTH || j < 0 || j >= LENGTH){
			return -9999;
		}
		
		return grid[i][j];
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public StateNode getcOne() {
		return cOne;
	}

	public void setcOne(StateNode cOne) {
		this.cOne = cOne;
	}

	public StateNode getcTwo() {
		return cTwo;
	}

	public void setcTwo(StateNode cTwo) {
		this.cTwo = cTwo;
	}

	public StateNode getcThree() {
		return cThree;
	}

	public void setcThree(StateNode cThree) {
		this.cThree = cThree;
	}

	public StateNode getcFour() {
		return cFour;
	}

	public void setcFour(StateNode cFour) {
		this.cFour = cFour;
	}

	public StateNode getParent() {
		return parent;
	}

	public void setParent(StateNode parent) {
		this.parent = parent;
	}
	
	public String printGrid()
	{
		return "[" + grid[0][0] + grid[0][1] + grid[0][2] + "], " +
				"[" + grid[1][0] + grid[1][1] + grid[1][2] + "], " +
				"[" + grid[2][0] + grid[2][1] + grid[2][2] + "]";
	}
	

}
