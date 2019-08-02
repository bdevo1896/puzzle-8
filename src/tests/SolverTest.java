package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import main.Solver;
import main.StateNode;

public class SolverTest {
	StateNode testNode;
	
	@Before
	public void setUp(){
//		int[][] grid = new int[3][3];
//		
//		grid[0][0] = 4;
//		grid[0][1] = 1;
//		grid[0][2] = 2;
//		grid[1][0] = 5;
//		grid[1][1] = 0;
//		grid[1][2] = 3;
//		grid[2][0] = 7;
//		grid[2][1] = 8;
//		grid[2][2] = 6;
		int[][] grid = {{4, 1, 2}, {5, 0, 3}, {7, 8, 6}};
		
		testNode = new StateNode(grid,null);
		
	}

	@Test
	public void testFind() {
		Solver s = new Solver();
		StateNode testSol = s.findSolution(testNode);
		assertArrayEquals(testSol.getGrid(), s.getGoalNode().getGrid());
	}
	
	@Test
	public void testGetSolutionPath(){
		Solver s = new Solver();
		StateNode testSol = s.findSolution(testNode);
		int path = 0;
		path = s.getSolutionPath(new LinkedList<StateNode>(), testSol);
		assertTrue("The Path length: "+path,path == 6);
	}

}
