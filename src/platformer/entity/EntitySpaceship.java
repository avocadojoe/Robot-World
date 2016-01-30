package platformer.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import platformer.resources.ImageLoader;
import platformer.start.Start;

public  class EntitySpaceship extends Entity{
boolean hasPlayerMounted = false;
	public void draw(Graphics2D g) {
		g.drawImage(ImageLoader.loadImage(getClass(), "/textures/misc/spaceship.png"), posX, posY, null);
	}

	public void update() {
		if(Start.in.mousePressed&& Start.in.mouseX > posX && Start.in.mouseX < posX+256&&Start.in.mouseY > posY && Start.in.mouseY < posY+128){
			hasPlayerMounted = true;
		}
		
		if(hasPlayerMounted && Start.in.isKeyDown(KeyEvent.VK_SHIFT)){
			hasPlayerMounted = false;
		}
		if(hasPlayerMounted){
			Start.instance.p.posX = posX+100;
			Start.instance.p.posY = posY+30;
		}
		if(Start.in.isKeyDown(KeyEvent.VK_UP)){
			posY-=10;
		}
		if(Start.in.isKeyDown(KeyEvent.VK_DOWN)){
			posY+=10;
		}
		if(Start.in.isKeyDown(KeyEvent.VK_RIGHT)){
			posX+=10;
		}
		if(Start.in.isKeyDown(KeyEvent.VK_LEFT)){
			posX-=10;
		}
	}

}
