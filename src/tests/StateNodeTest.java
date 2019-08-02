package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import main.StateNode;

public class StateNodeTest {
	int[][] grid = new int[3][3];
	StateNode testNode;
	
	@Before
	public void setUp(){
		testNode = new StateNode();
		testNode.createGoalState();
		
		//grid = new int[3][3];
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

	@Test
	public void testCreateGoalState() {
		
		int[][] testGrid = testNode.getGrid();
		
		assertArrayEquals(grid,testGrid);
	}
	
	@Test
	public void testMoveUp_Blank_Spot_In_Top_Row(){
		int[][] testGrid = testNode.moveUp(0, 0);
		assertTrue(testGrid == null);
	}
	
	@Test
	public void testMoveDown_Blank_Spot_In_Bottom_Row(){
		int[][] testGrid = testNode.moveDown(2, 0);
		assertTrue(testGrid == null);
	}
	
	@Test
	public void testMoveLeft_Blank_Spot_In_Left_Column(){
		int[][] testGrid = testNode.moveLeft(0, 0);
		assertTrue(testGrid == null);
	}
	
	@Test
	public void testMoveRight_Blank_Spot_In_Right_Column(){
		int[][] testGrid = testNode.moveRight(0, 2);
		assertTrue(testGrid == null);
	}
	
	@Test
	public void testMoveRight_On_Center(){
		int testVal = grid[1][1];
		int[][] newGrid = testNode.moveRight(1, 1);
		assertTrue("Actual Value: "+newGrid[1][2],newGrid[1][2]==testVal);
	}
	
	@Test
	public void testMoveLeft_On_Center(){
		int testVal = grid[1][1];
		int[][] newGrid = testNode.moveLeft(1, 1);
		assertTrue("Actual Value: "+newGrid[1][0],newGrid[1][0]==testVal);
	}
	
	@Test
	public void testMoveDown_On_Center(){
		int testVal = grid[1][1];
		int[][] newGrid = testNode.moveDown(1, 1);
		assertTrue("Actual Value: "+newGrid[2][1],newGrid[2][1]==testVal);
	}
	
	@Test
	public void testMoveUp_On_Center(){
		int testVal = grid[1][1];
		int[][] newGrid = testNode.moveUp(1, 1);
		assertTrue("Actual Value: "+newGrid[0][1],newGrid[0][1]==testVal);
	}
	
	@Test
	public void testCompareTo_When_Nodes_Are_Equal(){
		StateNode n = new StateNode(grid,null);
		assertTrue(n.compareTo(testNode) == 0);
	}
	
	@Test
	public void testCompareTo_When_Nodes_Are_Not_Equal(){
		StateNode n = new StateNode(new int[3][3],null);
		assertFalse(n.compareTo(testNode) == 0);
	}
	
	@Test
	public void testAllPossibilities(){
		final int[][] g = new int[3][3];
		g[0][0] = 1;
		g[0][1] = 2;
		g[0][2] = 3;
		g[1][0] = 4;
		g[1][1] = 0;
		g[1][2] = 5;
		g[2][0] = 6;
		g[2][1] = 7;
		g[2][2] = 8;
		
		StateNode newTestNode = new StateNode(g,null);
		
		newTestNode.getAllPossibleStates();
		
		StateNode cOne = newTestNode.getcOne();
		StateNode cTwo = newTestNode.getcTwo();
		StateNode cThree = newTestNode.getcThree();
		StateNode cFour = newTestNode.getcFour();
		
		
		
		int[][] grid1 = new int[3][3];
		grid1[0][0] = 1;
		grid1[0][1] = 0;
		grid1[0][2] = 3;
		grid1[1][0] = 4;
		grid1[1][1] = 2;
		grid1[1][2] = 5;
		grid1[2][0] = 6;
		grid1[2][1] = 7;
		grid1[2][2] = 8;
		
		assertArrayEquals("Move Up Is Wrong" + "[" + grid1[0][0] + grid1[0][1] + grid1[0][2] + "], " +
				"[" + grid1[1][0] + grid1[1][1] + grid1[1][2] + "], " +
				"[" + grid1[2][0] + grid1[2][1] + grid1[2][2] + "]" + "\n" + cOne.printGrid(),cOne.getGrid(), grid1);
		
		int[][] grid2 = new int[3][3];
		grid2[0][0] = 1;
		grid2[0][1] = 2;
		grid2[0][2] = 3;
		grid2[1][0] = 4;
		grid2[1][1] = 7;
		grid2[1][2] = 5;
		grid2[2][0] = 6;
		grid2[2][1] = 0;
		grid2[2][2] = 8;
		
		assertArrayEquals("Move Down Is Wrong" + "[" + grid2[0][0] + grid2[0][1] + grid2[0][2] + "], " +
				"[" + grid2[1][0] + grid2[1][1] + grid2[1][2] + "], " +
				"[" + grid2[2][0] + grid2[2][1] + grid2[2][2] + "]",cTwo.getGrid(), grid2);
		
		int[][] grid3 = new int[3][3];
		grid3[0][0] = 1;
		grid3[0][1] = 2;
		grid3[0][2] = 3;
		grid3[1][0] = 0;
		grid3[1][1] = 4;
		grid3[1][2] = 5;
		grid3[2][0] = 6;
		grid3[2][1] = 7;
		grid3[2][2] = 8;
		
		assertArrayEquals("Move Left Is Wrong" + "[" + grid3[0][0] + grid3[0][1] + grid3[0][2] + "], " +
				"[" + grid3[1][0] + grid3[1][1] + grid3[1][2] + "], " +
				"[" + grid3[2][0] + grid3[2][1] + grid3[2][2] + "]",cThree.getGrid(), grid3);
		
		int[][] grid4 = new int[3][3];
		grid4[0][0] = 1;
		grid4[0][1] = 2;
		grid4[0][2] = 3;
		grid4[1][0] = 4;
		grid4[1][1] = 5;
		grid4[1][2] = 0;
		grid4[2][0] = 6;
		grid4[2][1] = 7;
		grid4[2][2] = 8;
		
		assertArrayEquals("Move Right Is Wrong" + "[" + grid4[0][0] + grid4[0][1] + grid4[0][2] + "], " +
				"[" + grid4[1][0] + grid4[1][1] + grid4[1][2] + "], " +
				"[" + grid4[2][0] + grid4[2][1] + grid4[2][2] + "]",cFour.getGrid(), grid4);
	}
}
