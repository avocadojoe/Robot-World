package platformer.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import platformer.effect.Particle;
import platformer.resources.ImageLoader;
import platformer.start.Start;

public class EntityBomb extends Entity{
int timer = 0;
int targetX = 0;
int targetY = 0;
public EntityBomb(int targetX, int targetY){
	this.targetX = targetX;
	this.targetY = targetY;
}
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
	g.fillOval(posX, posY, 10, 10);	
	}

	public void update() {
		Start.instance.world.addEntity(new Particle(ImageLoader.loadImage(getClass(), "/textures/misc/flame.png"), 20), posX, posY);
		if(posX > targetX-10 && posX < targetX+10&&posY > targetY-10 && posY < targetY+10){
			Start.instance.world.doomexplosion(posX, posY, 90);
			this.dead = true;
		}
		
		if(posX < targetX){
			posX+=10;
		}
		if(posX > targetX){
			posX-=10;
		}
		if(posY < targetY){
			posY+=10;
		}
		if(posY >targetY){
			posY-=10;
		}
	}

}
