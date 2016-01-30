package platformer.entity;

import java.awt.Graphics2D;

public abstract class Entity {

	public int id = 0;
	
	public int posX = 0;
	public int posY = 0;
	
	public int startX = 0;
	public int startY = 0;
	
	public boolean dead = false;
	
	public abstract void draw(Graphics2D g);
	
	public abstract void update();

}
