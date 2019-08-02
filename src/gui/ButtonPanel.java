package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.Controller;

/**
 * This class will contain all the buttons needed to move 
 */
class ButtonPanel extends JPanel{
	
	/**
	 * 
	 */
	private final MainFrame mf;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton upBtn,downBtn,leftBtn,rightBtn;
	
	public ButtonPanel(MainFrame mainFrame, ActionListener aH){
		mf = mainFrame;
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		upBtn = new JButton(Controller.UP_BTN);
		upBtn.setEnabled(false);
		upBtn.addActionListener(aH);
		
		downBtn = new JButton(Controller.DOWN_BTN);
		downBtn.setEnabled(false);
		downBtn.addActionListener(aH);
		
		leftBtn = new JButton(Controller.LEFT_BTN);
		leftBtn.setEnabled(false);
		leftBtn.addActionListener(aH);
		
		rightBtn = new JButton(Controller.RIGHT_BTN);
		rightBtn.setEnabled(false);
		rightBtn.addActionListener(aH);
		
		this.add(upBtn);
		this.add(downBtn);
		this.add(leftBtn);
		this.add(rightBtn);
		
		this.setBorder(new TitledBorder("Move Blank Square"));
	}

	public void start() {
		upBtn.setEnabled(true);
		downBtn.setEnabled(true);
		leftBtn.setEnabled(true);
		rightBtn.setEnabled(true);
	}
	
	public void stop(){
		upBtn.setEnabled(false);
		downBtn.setEnabled(false);
		leftBtn.setEnabled(false);
		rightBtn.setEnabled(false);
	}
}