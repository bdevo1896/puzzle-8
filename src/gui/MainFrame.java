package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Controller;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controller c;
	private GridPanel gPanel;
	private static final int MAX_W = 807;
	private static final int MAX_H = 750;
	private JButton scrambleBtn,resetBtn,solveBtn;
	private DetailsPanel dPanel;
	private ButtonPanel bPanel;

	public MainFrame(String arg0,Controller c,int N) throws HeadlessException {
		super(arg0);
		this.c = c;
		this.gPanel = new GridPanel(N,c);
		this.dPanel = new DetailsPanel(this);
		this.bPanel = new ButtonPanel(this, c.getaH());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		/*
		 * This panel will contain the JButton used to scramble the puzzle and then be added to the south side of the mainPanel
		 */
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		JPanel southButtonPanel = new JPanel();
		southButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		resetBtn = new JButton(Controller.RESET_BTN);
		resetBtn.addActionListener(c.getActionHelper());
		southButtonPanel.add(resetBtn);
		
		scrambleBtn = new JButton(Controller.SCRAMBLE_BTN);
		scrambleBtn.addActionListener(c.getActionHelper());
		southButtonPanel.add(scrambleBtn);
		
		solveBtn = new JButton(Controller.SOLVE_BTN);
		solveBtn.addActionListener(c.getActionHelper());
		southButtonPanel.add(solveBtn);
		southPanel.add(southButtonPanel,BorderLayout.SOUTH);

		/*
		 * Adding the buttons panel to the frame
		 */
		southPanel.add(bPanel, BorderLayout.CENTER);
		
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		/*
		 * Adding the GridPanel to the center of the mainPanel
		 */
		mainPanel.add(gPanel,BorderLayout.CENTER);
		
		/*
		 * Adding the details panel to the frame
		 */
		mainPanel.add(dPanel, BorderLayout.EAST);
		
		
		this.setMinimumSize(new Dimension(MAX_W,MAX_H));
		this.setMaximumSize(new Dimension(MAX_W,MAX_H));
		this.setPreferredSize(new Dimension(MAX_W,MAX_H));
		this.setResizable(false);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.shutoff();
		
	}
	/**
	 * This method will be called when the grid is scrambled and starts the game
	 */
	public void scramble(){
		scrambleBtn.setEnabled(false);
		update();
		bPanel.start();
		resetBtn.setEnabled(true);
		solveBtn.setEnabled(true);
	}

	public void update() {
		gPanel.update();
		updateDetailsPanel();
	}
	
	public void updateDetailsPanel(){
		dPanel.getMovesMadeLbl().setText(c.getMovesTaken()+"");
		dPanel.getMinMovesLbl().setText(c.getMinMoves()+"");
	}
	
	public void shutoff() {
		bPanel.stop();
		scrambleBtn.setEnabled(true);
		resetBtn.setEnabled(false);
		solveBtn.setEnabled(false);
	}
	
	public void animationStart(){
		bPanel.stop();
		scrambleBtn.setEnabled(false);
		solveBtn.setEnabled(false);
		resetBtn.setEnabled(false);
	}
	
	public void animationEnd(){
		shutoff();
	}

}
