package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class will extend a JPanel that shows relevant information to the current game
 */
class DetailsPanel extends JPanel {
	/**
	 * 
	 */
	private final MainFrame mf;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TITLE_LBL_FONT = 1;
	private static final int REG_LBL_FONT = 2;
	private JLabel minMovesLbl,movesMadeLbl;
	
	public DetailsPanel(MainFrame mainFrame){
		mf = mainFrame;
		this.setLayout(new BorderLayout());
		/*
		 * This panel will show the number goal of moves and moves already made
		 */
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		
		/*
		 * This is a container panel to hold two labels to display : "Minimum Moves: 3 "
		 */
		JPanel minMovesPnl = new JPanel();
		minMovesPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel minMovesTitle = setLabelLook("Minimum Moves: ",Color.GREEN,TITLE_LBL_FONT);
		minMovesLbl = setLabelLook(mf.c.getMinMoves()+"",Color.black,REG_LBL_FONT);
		minMovesPnl.add(minMovesTitle);
		minMovesPnl.add(minMovesLbl);
		topPanel.add(minMovesPnl);
		
		/**
		 * This is a container panel for the moves taken by the user
		 */
		JPanel movesMadePnl = new JPanel();
		movesMadePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel movesMadeTitle = setLabelLook("Moves Made: ",Color.RED,TITLE_LBL_FONT);
		movesMadeLbl = setLabelLook(mf.c.getMovesTaken()+"",Color.black,REG_LBL_FONT);
		movesMadePnl.add(movesMadeTitle);
		movesMadePnl.add(movesMadeLbl);
		topPanel.add(movesMadePnl);
		
		this.add(topPanel,BorderLayout.NORTH);
		
		/*
		 * This is a container panel for the Goal picture
		 */
		ImageIcon fullPic = new ImageIcon("dummyImg.png");
		JLabel picLbl = new JLabel(fullPic);
		JLabel picLblTitle = setLabelLook("Goal Picture",Color.darkGray,TITLE_LBL_FONT);
		
		JPanel goalPicPnl = new JPanel();
		goalPicPnl.setLayout(new BoxLayout(goalPicPnl,BoxLayout.Y_AXIS));
		goalPicPnl.add(picLblTitle);
		goalPicPnl.add(picLbl);
		
		this.add(goalPicPnl, BorderLayout.CENTER);
		
		this.setBorder(new TitledBorder("Details"));
	}
	
	/**
	 * Creates and returns a JLable with a specified look.
	 * @param text 
	 * @param col is the color of the label
	 * @param fontType is the type of font represented by a number
	 * @return
	 */
	public JLabel setLabelLook(String text, Color col,int fontType){
		JLabel rtnLbl = new JLabel(text);
		rtnLbl.setForeground(col);
		
		if(TITLE_LBL_FONT == fontType){
			rtnLbl.setFont(new Font("Calibri",Font.BOLD,20));
		}else if(REG_LBL_FONT == fontType){
			rtnLbl.setFont(new Font("CalibriBody",Font.PLAIN,20));

		}
		
		return rtnLbl;
	}

	public JLabel getMinMovesLbl() {
		return minMovesLbl;
	}

	public JLabel getMovesMadeLbl() {
		return movesMadeLbl;
	}
	
	
}