package platformer.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import platformer.effect.Particle;
import platformer.item.LightningGun;
import platformer.item.PlazmaCannon;
import platformer.resources.ImageLoader;
import platformer.start.Start;

public class EntityBoss extends Entity{
	int lives = 1000;
	int sheilds  = 5;
	Random r = new Random();
	int damageDealt = 0;
	int timer = 0;
		public void draw(Graphics2D g) {
			if(timer >=100){
				Start.instance.world.addEntity(new EntityMonster(), posX, posY);
			timer = 0;
			}
			if(timer >30 && timer<=31){
				Start.instance.world.addEntity(new EntityBomb(Start.instance.p.posX,Start.instance.p.posY), posX, posY);

			}
			if(timer >10 && timer<=11){
				Start.instance.world.addEntity(new EntityBomb(Start.instance.p.posX,Start.instance.p.posY), posX, posY);

			}
			
			if(timer >50 && timer<=51){
				Start.instance.world.addEntity(new EntityBomb(Start.instance.p.posX,Start.instance.p.posY), posX, posY);

			}
			
			if(timer >70 && timer<=71){
				Start.instance.world.addEntity(new EntityBomb(Start.instance.p.posX,Start.instance.p.posY), posX, posY);

			}
			
			if(timer >90 && timer<=91){
				Start.instance.world.addEntity(new EntityBomb(Start.instance.p.posX,Start.instance.p.posY), posX, posY);

			}
			if(timer <= 100){
				timer++;

			}
			if(!dead){
				g.setColor(Color.DARK_GRAY);

				g.fill3DRect(posX-10, posY-15, 100+4, 10+4,true);
	g.setColor(Color.RED);
				g.fillRect(posX-8, posY-13, lives/10, 10);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Serif", Font.BOLD,12));

				g.drawString(lives+"/1000", posX, posY-5);
				

				g.drawImage(ImageLoader.loadImage(getClass(), "/textures/misc/boss_1.png"), posX, posY, 200, 200, null);

			}
		}

		boolean a = false;
		int t = 0;
		int timeScinceHit = 100;
		public void update() {
			if(posX < Start.instance.p.posX){
				posX+=1;
			}
			if(posX > Start.instance.p.posX){
				posX-=1;
			}
			if(posY < Start.instance.p.posY){
				posY+=1;
			}
			if(posY >Start.instance.p.posY){
				posY-=1;
			}
			if(Start.instance.p.posX>=posX&& Start.instance.p.posX <= posX+32&&Start.instance.p.posY>=posY-32&& Start.instance.p.posY <= posY+32){
				this.lives -= 1;
				Start.instance.p.onGround = true;
	Start.instance.p.jumping = true;
				Start.instance.p.jumpHeight = Start.instance.p.posY-Start.instance.p.maxJumpHeight;
			}
			
			
			if(timeScinceHit >=10){
				if(Start.instance.p.posX>=posX&& Start.instance.p.posX <= posX+32&&Start.instance.p.posY>=posY&& Start.instance.p.posY <= posY+64){
					this.damageDealt =  40+(r.nextInt(10)-5);
					this.lives-=damageDealt;
					Start.instance.p.lives-=damageDealt;
					Start.instance.world.addEntity(new Particle("-"+damageDealt,15,Color.RED), Start.instance.p.posX, Start.instance.p.posY);

					timeScinceHit = 0;
				}
			}
			if(Start.in.mousePressed && Start.instance.p.itemHeld instanceof LightningGun){
				if(Start.in.mouseY > posY-10&&Start.in.mouseY < posY+200){
					
					if(Start.in.mouseX > posX-10&&Start.in.mouseY > posY&&Start.in.mouseY < posY+200){
						this.damageDealt =  (Start.instance.p.itemHeld.damage-sheilds);
						if(damageDealt > 0){
							this.lives-=damageDealt;
							Start.instance.world.addEntity(new Particle(""+damageDealt,15,Color.WHITE), posX, posY);

						}else{
							this.lives-=1;
							Start.instance.world.addEntity(new Particle("1",15,Color.WHITE), posX, posY);

						}
						
					}
				}
			}
		
			if(lives <= 0){
				Start.instance.world.addEntity(new Particle("unlocked first spaceship!!!",20,Color.GREEN), Start.instance.p.posX-40, Start.instance.p.posY-40);

				Start.instance.world.addEntity(new Particle("obtained new item: plazma cannon",20,Color.GREEN), Start.instance.p.posX-40, Start.instance.p.posY-40);
Start.instance.p.itemHeld = new PlazmaCannon();
				Start.instance.p.coins+=100;
				Start.instance.world.explosion(posX, posY, 300);
				
				dead = true;
			}
		}


}
