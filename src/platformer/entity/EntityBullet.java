package platformer.entity;

import java.awt.Graphics2D;
import java.awt.Image;

import platformer.resources.ImageLoader;
import platformer.start.Start;

public class EntityBullet extends Entity{

	public Image image;
	
	public float angle = 0;
	
	public EntityBullet(float angle,int x,int y){
		this.angle = angle;
		image = ImageLoader.loadImage(getClass(), "/textures/misc/lazer.png");
		this.posX = x;
		this.posY = y;
	}
	
	public void draw(Graphics2D g) {
		g.translate(posX, posY);
		g.rotate(-(angle+45+22.5));
		g.drawImage(image, -74, -10, 32, 32, null);
		g.rotate(angle+45+22.5);
		g.translate(-posX, -posY);


	}
	

	public void update() {
		 posX -= 10 * Math.sin(angle);
		 posY -= 10 * Math.cos(angle);
		 if(Start.instance.world.getBlock(posX/32, posY/32).canCollideWithPlayer()&&Start.instance.world.getBlock(posX/32, posY/32).canCollideWithPlayer()){
			 this.dead = true;
		 }
	}

}
