package platformer.item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import platformer.entity.EntityBullet;
import platformer.resources.ImageLoader;
import platformer.start.Start;

public class LightningGun extends Item{
	public float time = 0;

	public LightningGun(){
	  this.name = "Plazma Rifle";
	  this.damage = 10;
	  this.texture = ImageLoader.loadImage(getClass(), "/textures/items/lazergun.png");
	}
	Random rand = new Random();
	double rotation = 0;
	public void onItemUse(Graphics2D g) {
		if(time>10){
			g.setColor(Color.GREEN);
			 int x = Start.instance.p.posX+12;
	         int y = Start.instance.p.posY+17;
	         
	         int deltaX = Start.in.mouseX - x;
	         int deltaY = Start.in.mouseY - y;

	         rotation = -Math.atan2(deltaX, deltaY);

	         rotation = Math.toDegrees(rotation) + 180;
	         
	         Start.instance.world.entities.add(new EntityBullet((float)-Start.instance.p.armRot,Start.instance.p.posX+10,Start.instance.p.posY+20));
	         time = 0;
		}
		time++;
		
	}

}
