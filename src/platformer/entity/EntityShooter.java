package platformer.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

import platformer.effect.Particle;
import platformer.start.Start;

public class EntityShooter extends Entity{
int lives = 50;
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);

		g.fill3DRect(posX-12, posY-15, 50+4, 10+4,true);
g.setColor(Color.RED);
		g.fillRect(posX-10, posY-13, lives, 10);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD,12));

		g.drawString(lives+"/50", posX, posY-5);
				
	}
	Random r = new Random();
int damageDealt = 0;
	@Override
	public void update() {
		Object[] b =  Start.instance.world.entities.toArray();
		for (Object bullet : b){
			if(bullet instanceof EntityBullet){
		
				if(!((EntityBullet)bullet).dead && Point2D.distance(posX, posY, ((EntityBullet)bullet).posX, ((EntityBullet)bullet).posY)<80){
					((Entity)bullet).dead = true;
					this.damageDealt =  Start.instance.p.itemHeld.damage+(r.nextInt(10)-5);
					this.lives-=damageDealt;
					Start.instance.world.addEntity(new Particle(""+damageDealt,15,Color.WHITE), posX, posY);
				}
			}
		}
	
		if(lives <= 0){
			Start.instance.world.setBlock(posX/32, posY/32, Start.instance.blockTypes[2]);
			Start.instance.world.explosion(posX, posY, 100);
			
			dead = true;
		}		
	}

}
