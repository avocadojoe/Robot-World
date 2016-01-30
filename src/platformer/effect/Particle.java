package platformer.effect;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import platformer.entity.Entity;

public class Particle extends Entity{

	Random rand = new Random();
	protected Image image;
	protected boolean isImage =true;
	String t = "";
	
	protected int maxSize = 0;
	
	public double size  = 0;
	int offsetX = 0;
	int offsetY = 0;

	public Particle(Image image,int maxSize){
		this.image = image;
		this.maxSize = maxSize;
		this.size = rand.nextInt(maxSize);
		this.offsetX = rand.nextInt(maxSize*2);
		this.offsetY = rand.nextInt(maxSize*2);
		isImage = true;
	}
	Color c = Color.WHITE;
	public Particle(String a,int maxSize,Color color){
		this.t = a;
		this.maxSize = maxSize;
		this.size = maxSize;
		this.offsetX = rand.nextInt(maxSize*2);
		this.offsetY = rand.nextInt(maxSize*2);
		this.c = color;
		isImage = false;
	}
	
	public void draw(Graphics2D g) {
		if(isImage){
		
			g.drawImage(image, posX+offsetX, posY+offsetY, (int)Math.round(size), (int)Math.round(size), null);
		}else{
			g.setFont(new Font(Font.SERIF,Font.PLAIN,20));
			g.setColor(c);
			g.drawString(t, posX+offsetX, posY+offsetY);
		}
	}

	int d = 0;
	public void update() {
		size -= 0.9;
		d= rand.nextInt(4);
		switch(d){
		case 0:
			posY++;
		break;
		case 1:
			posX++;
		break;
		case 2:
			posX--;
		break;
		case 3:
			posY++;
		break;
		default:
			posY++;
		break;

		}
		if(size <= 0.5){
			this.dead = true;
		}
	}

}
