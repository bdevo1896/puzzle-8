package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActionHelper implements ActionListener{

	private Controller c;

	public ActionHelper(Controller c){
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		onEvent(e);
	}

	private void onEvent(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if(btn.getText() == Controller.SCRAMBLE_BTN){
			c.scramble(50);
		}

		if(btn.getText() == Controller.UP_BTN){
			c.move(Controller.UP);
			c.update();
		}

		if(btn.getText() == Controller.DOWN_BTN){
			c.move(Controller.DOWN);
			c.update();
		}

		if(btn.getText() == Controller.LEFT_BTN){
			c.move(Controller.LEFT);
			c.update();
		}

		if(btn.getText() == Controller.RIGHT_BTN){
			c.move(Controller.RIGHT);
			c.update();
		}

		if(btn.getText() == Controller.RESET_BTN){
			c.reset();
			c.update();
		}

		if(btn.getText() == Controller.SOLVE_BTN){
			c.solve();
		}
	}
}
