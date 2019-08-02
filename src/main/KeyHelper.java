package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyHelper implements KeyListener{

	/**
	 * 
	 */
	private final Controller controller;

	/**
	 * @param controller
	 */
	KeyHelper(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(controller.isGameRunnig()){
			if(arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_W){
				controller.move(Controller.UP);
				controller.update();
			}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN || arg0.getKeyCode() == KeyEvent.VK_S){
				controller.move(Controller.DOWN);
				controller.update();
			}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT || arg0.getKeyCode() == KeyEvent.VK_D){
				controller.move(Controller.RIGHT);
				controller.update();
			}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_A){
				controller.move(Controller.LEFT);
				controller.update();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}