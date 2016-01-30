package platformer.item;

import java.awt.Graphics2D;
import java.util.Random;

import platformer.resources.ImageLoader;

public class PlazmaCannon extends LightningGun{


	public PlazmaCannon(){
	  this.name = "Plazma Cannon";
	  this.damage = 25;
	  this.texture = ImageLoader.loadImage(getClass(), "/textures/items/plazmacannon.png");
	}
	Random rand = new Random();
	double rotation = 0;
	public void onItemUse(Graphics2D g) {
		super.onItemUse(g);
	}
}
