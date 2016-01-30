package platformer.start;

import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame{

	private static final long serialVersionUID = 1L;

	Draw d;
	@SuppressWarnings("deprecation")
	public void setup(){
		Start.instance.world.addEntity(Start.instance.p, 200, 300);

		this.setTitle("Robot World");
		d = new Draw(this);
		this.setSize(1200,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setCursor(Cursor.CROSSHAIR_CURSOR);
		this.setLocationRelativeTo(null);
		this.add(d);
d.setVisible(true);
		this.setVisible(true);

	}
	
}

class Draw extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Draw(JFrame f){
		this.setDoubleBuffered(true);
		this.setSize(f.getSize());
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		Start.instance.draw(g);
	}
}
