package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Controller;

/**
 * This class will be used to have the user create the grid that the word find algorithm 
 * will go through. It will contain a special Square class that is clickable.
 * @author Bryce DeVaughn.
 *
 */
public class GridPanel extends JPanel implements MouseListener{

	private GridSquare[][] displayGrid;//Contains the GridSquares that the user will be able to click on
	private int[][] gridVals;//Contains the display characters that are in each grid square
	private final int WIDTH = 600;
	private int N;
	/**
	 * This array will contain all of the ImageIcons of the puzzle we have
	 */
	private ImageIcon[] images;

	private int GRIDSQSIDELENGTH;
	
	private Controller c;

	
	public GridPanel(int N,Controller c){
		this.N = N;
		this.c = c;
		images = getImages();
		displayGrid = new GridSquare[N][N];
		gridVals = c.getStateGrid();
		this.GRIDSQSIDELENGTH= WIDTH/N;
		this.fillLists();
		this.setFocusable(true);
		this.setBackground(Color.black);
		this.setSize(new Dimension(WIDTH, WIDTH));
		this.addMouseListener(this);
		this.addKeyListener(c.getKeyHelper());
		
	}
	
	/**
	 * This method will create a list of ImageIcons read from the resources package 
	 */
	public ImageIcon[] getImages(){
		String mainStr = "dummyImg";
		ImageIcon[] rtnList = new ImageIcon[9];
		for(int i = 0; i < 8; i ++){
			rtnList[i] = new ImageIcon(mainStr+(i+1)+".png");
		}
		return rtnList;
	}
	

	/**
	 * This will fill the lists gridVals and displayGrid
	 */
	public void fillLists(){
		int count = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				GridSquare sq = new GridSquare(j * GRIDSQSIDELENGTH, i * GRIDSQSIDELENGTH, GRIDSQSIDELENGTH, images[count]);
				displayGrid[i][j] = sq;
				count++;
			}
		}
	}
	
	public void paintComponent(Graphics g){

		for(int i = 0; i < displayGrid.length; i++){
			for(int j = 0; j < displayGrid[i].length; j++){
				displayGrid[i][j].drawOn(g);
			}
		}
	}
	
	/**
	 * This method will check every grid square to see if it has been clicked
	 * @return
	 */
	public void checkClickLoc(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		for(int i = 0; i < displayGrid.length; i++){
			for(int j = 0; j < displayGrid[i].length; j++){
				GridSquare sq = displayGrid[i][j];
				if(sq.contains(mx,my)){
					sq.setClicked(true);
					this.repaint();
//					String str = JOptionPane.showInputDialog("Enter a letter.", null);
//					gridVals[i][j] = str;
//					sq.setDisplayLetter(str);
					sq.setClicked(false);
					this.repaint();
					
				}
			}
		}
		
	}

	public GridSquare[][] getDisplayGrid() {
		return displayGrid;
	}

	public int[][] getGridVals() {
		return gridVals;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//this.checkClickLoc(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}
	
	/**
	 * Called to repaint and update the displayGrid
	 */
	public void update() {
		this.updateVisualGrid();
		this.repaint();
	}
	
	/**
	 * This method will update the displayGrid
	 */
	private void updateVisualGrid(){
		gridVals = c.getStateGrid();
		
		for(int i = 0; i < gridVals.length; i++){
			for(int j = 0; j < gridVals[i].length; j++){
				ImageIcon newImg = new ImageIcon("dummyImg"+gridVals[i][j]+".png");
				displayGrid[i][j].setImg(newImg);
			}
		}
	}
}
