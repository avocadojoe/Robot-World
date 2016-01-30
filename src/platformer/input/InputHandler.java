package platformer.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener,MouseListener,MouseMotionListener{

	public boolean mousePressed = false;
	
	public int mouseX = 0;
	public int mouseY = 0;
	
	public boolean keyPressed = false;
	public int keyCode= 0 ;
	
	public boolean[] keys = new boolean[200];
	
	public InputHandler(){
		for(int i = 0; i <200;i++){
			keys[i] = false;
		}
	}
	
	public boolean isKeyDown(int keyCode){
		return keys[keyCode];
	}
	
	@Override
	public void mouseDragged(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

	}

	@Override
	public void mouseMoved(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

	}

	@Override
	public void mouseClicked(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

	}

	@Override
	public void mousePressed(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

		mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

		mousePressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

	}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent) {
		mouseX = paramMouseEvent.getX();
		mouseY = paramMouseEvent.getY();

	}

	@Override
	public void keyTyped(KeyEvent paramKeyEvent) {
		
	}

	@Override
	public void keyPressed(KeyEvent paramKeyEvent) {
		keyPressed = true;
		keys[paramKeyEvent.getKeyCode()] =true;
		keyCode = paramKeyEvent.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		keys[paramKeyEvent.getKeyCode()] =false;

		keyPressed = false;
	}

}
