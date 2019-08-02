package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.MainFrame;

public class Controller {

	private static final int SOLVE_SPEED = 750;
	/**
	 * This object is a JFrame containing everything the user will see
	 */
	private MainFrame mf;
	/**
	 * This object will handle the checking of solutions by determining what is the best path to the solution
	 */
	private Solver s;
	/**
	 * The StateNode that is being modified in the game.
	 */
	private StateNode modelNode;
	
	/**
	 * This node will be kkept to know if any changes have been made to the state node
	 */
	private StateNode originNode;
	
	
	/**
	 * This object will handle the ActionEvents 
	 */
	private ActionHelper aH;

	public static final String SCRAMBLE_BTN = "Scramble!";
	public static final String RIGHT_BTN = "Right",DOWN_BTN = "Down", UP_BTN = "Up", LEFT_BTN = "Left";
	public static final String RIGHT = "R", LEFT = "L", UP = "U", DOWN = "D";
	public static final String RESET_BTN = "Reset (Wow You're Giving Up Huh?)";
	public static final String SOLVE_BTN = "Solve";
	/**
	 * The moves that the user has taken so far.
	 */
	private int movesTaken;

	/*
	 * This is the minimum amount of moves that can be taken to reach the goal state
	 */
	private int minMoves;

	/*
	 * This will contain a list of the shortest path
	 */
	private LinkedList<StateNode> path;
	private KeyHelper kH;
	
	private boolean gameRunning = false;

	public Controller() {
		this.s = new Solver();
		this.aH = new ActionHelper(this);
		this.kH = new KeyHelper(this);
		this.movesTaken = 0;
		this.minMoves = 0;
		this.modelNode = new StateNode();
		this.modelNode.createGoalState();//Setting the modelNode to the goal state so the scramble can happen correctly
		this.originNode = null; //Will be set when model Node has been scrambled
		this.mf = new MainFrame("Puzzle 8",this,3);

		this.path = new LinkedList<StateNode>();
	}
	
	/**
	 * This method will reset the game
	 */
	public void reset(){
		movesTaken = 0;
		minMoves = 0;
		modelNode.createGoalState();
		mf.shutoff();
	}

	/**
	 * This method will scramble the modelNode
	 * @param numOfMoves is the amount of times a move will be made before the scramble is done
	 */
	public void scramble(int numOfMoves){
		/*
		 * This is the possible range of times it will scramble 80 - 100
		 */
//		int numOfMoves = 80 + (int)(Math.random()*21);
		int[][] newStateGrid = null;
		for(int i = 0; i < numOfMoves; i++){

			//do{
				int randDir = (int)(Math.random()*4);

				Coord c = modelNode.getBlankSpot();
				switch(randDir){
				case 0:
					newStateGrid = this.modelNode.moveUp(c.getRow(), c.getCol());
					break;
				case 1:
					newStateGrid = this.modelNode.moveDown(c.getRow(), c.getCol());
					break;
				case 2:
					newStateGrid = this.modelNode.moveLeft(c.getRow(), c.getCol());
					break;
				default:
					newStateGrid = this.modelNode.moveRight(c.getRow(), c.getCol());
					break;
				}
				
			//}while(newStateGrid == null);
				
		if(newStateGrid != null){
			this.modelNode.setGrid(newStateGrid);
			this.update();
		}
		
		}

		/*
		 * Setting the shortest move path
		 */
		StateNode solved = this.s.findSolution(modelNode);
		path.clear();
		this.minMoves = this.s.getSolutionPath(path, solved);
		this.movesTaken = 0;
		this.originNode = new StateNode(solved.getGrid(),null);
		this.gameRunning = true;
		mf.scramble();
	}

	/**
	 * This method will call the update function on the MainFrame to show the changes in the GUI
	 */
	public void update(){
		mf.update();
	}
	
	/**
	 * Moves the blank spot and updates the modelNode
	 */
	public void move(String dir){
		Coord blank = modelNode.getBlankSpot();
		int[][] newGrid = null;
		switch(dir){
		case UP:
			newGrid = modelNode.moveUp(blank.getRow(), blank.getCol());
			/*
			 * Checking to see if the move is possible and if not then show the user they can't move that way.
			 */
			if(newGrid != null){
				modelNode.setGrid(newGrid);
				movesTaken++;
			}else{
				JOptionPane.showConfirmDialog(mf, "Can't move up from that position!");
			}
			
			break;
		case DOWN:
			newGrid = modelNode.moveDown(blank.getRow(), blank.getCol());
			/*
			 * Checking to see if the move is possible and if not then show the user they can't move that way.
			 */
			if(newGrid != null){
				modelNode.setGrid(newGrid);
				movesTaken++;
			}else{
				JOptionPane.showConfirmDialog(mf, "Can't move down from that position!");
			}
			
			break;
		case LEFT:
			newGrid = modelNode.moveLeft(blank.getRow(), blank.getCol());
			/*
			 * Checking to see if the move is possible and if not then show the user they can't move that way.
			 */
			if(newGrid != null){
				modelNode.setGrid(newGrid);
				movesTaken++;
			}else{
				JOptionPane.showConfirmDialog(mf, "Can't move left from that position!");
			}
			
			break;
		default:
			newGrid = modelNode.moveRight(blank.getRow(), blank.getCol());
			/*
			 * Checking to see if the move is possible and if not then show the user they can't move that way.
			 */
			if(newGrid != null){
				modelNode.setGrid(newGrid);
				movesTaken++;
			}else{
				JOptionPane.showConfirmDialog(mf, "Can't move right from that position!");
			}
			
			break;
		}
		
		if(modelNode.compareTo(s.getGoalNode()) == 0){
			this.win();
		}
	}

	/**
	 * This is used when the user has finished the making the picture.
	 */
	private void win() {
		gameRunning = false;
		mf.shutoff();
		update();
		JOptionPane.showMessageDialog(mf, "Congrats! You won!");
	}
	
	/**
	 * This method will start a animation to solve the puzzle.
	 * @author Bryce
	 *
	 */
	public void solve(){
		mf.animationStart();//This is called to disable all of the buttons during animations
		gameRunning = false;//Disable the keyboard shortcuts

		if(originNode.compareTo(modelNode) != 0){
			StateNode solved = s.findSolution(modelNode);
			path.clear();
			minMoves = s.getSolutionPath(path, solved);
		}
		
		Runnable run = new Runnable(){
			public void run(){
				for(int i = path.size()-1; i >= 0; i--){
					modelNode.setGrid(path.get(i).getGrid());
					update();
					try {
						Thread.sleep(SOLVE_SPEED);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				mf.animationEnd();//This will enable all of the buttons again
				win();
			}
		};
		
		Thread thr = new Thread(run);
		thr.start();
		
	}
	
	
	public int[][] getStateGrid() {
		return modelNode.getGrid();
	}

	public ActionListener getActionHelper() {
		return aH;
	}

	public MainFrame getMf() {
		return mf;
	}

	public Solver getS() {
		return s;
	}

	public StateNode getModelNode() {
		return modelNode;
	}

	public ActionHelper getaH() {
		return aH;
	}

	public int getMovesTaken() {
		return movesTaken;
	}

	public int getMinMoves() {
		return minMoves;
	}

	public LinkedList<StateNode> getPath() {
		return path;
	}

	public KeyListener getKeyHelper() {
		// TODO Auto-generated method stub
		return kH;
	}
	
	public boolean isGameRunnig(){
		return gameRunning;
	}

}
