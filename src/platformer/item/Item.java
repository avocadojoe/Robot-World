package platformer.item;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Item {

	public Image texture;
	
	public String name;
	
	public int damage = 0;
	
	public abstract void onItemUse(Graphics2D gs);
}
