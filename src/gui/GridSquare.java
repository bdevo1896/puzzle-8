package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GridSquare {
	private int x,y,sideLength;
	private ImageIcon img;
	private boolean isClicked;
	private final Color clickedColor = new Color(245,186,197);
	//private final int FONTSIZE = 60;

	public GridSquare(int x, int y, int sideLength, ImageIcon img){
		this.x = x;
		this.y = y;
		this.sideLength = sideLength;
		this.img = img;
		this.isClicked = false;
	}

	public void drawOn(Graphics g){
		/*
		 * This is checking if the user has clicked on the gird square they want to edit
		 */
		if(isClicked){
			g.setColor(clickedColor);
		}else {
			g.setColor(Color.white);
		}

		g.fillRect(x, y, sideLength, sideLength);

		//Border for the grid square 
		g.setColor(Color.black);
		g.drawRect(x, y, sideLength, sideLength);

		if(img != null){
			g.drawImage(img.getImage(),x,y,sideLength,sideLength,null);
		}
//		else{
//			g.setColor(clickedColor);
//			g.fillRect(x, y, sideLength, sideLength);
//		}

		//Border for the grid square 
		g.setColor(Color.black);
		g.drawRect(x, y, sideLength, sideLength);

	}

	/**
	 * This method will return true if the x and y inputed is inside the square
	 * @return
	 */
	public boolean contains(int x, int y){
		return x >= this.x && x <= (this.x + this.sideLength) && y >= this.y && y <= (this.y + this.sideLength);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}



}
