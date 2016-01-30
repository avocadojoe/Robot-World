package platformer.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import platformer.blocks.Block;
import platformer.blocks.BlockAir;
import platformer.blocks.BlockCoin;
import platformer.blocks.BlockSpike;
import platformer.effect.Particle;
import platformer.entity.Entity;
import platformer.entity.player.EntityPlayer;
import platformer.resources.ImageLoader;
import platformer.start.Start;

public class World {
	Random rand = new Random();
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public World(int width, int height) {
		blocks = new Block[width][height];
	}

	public Block[][] blocks;
	
	public void setBlock(int x, int y,Block b){
		blocks[x][y] = b;
	}
	
	public Block getBlock(int x, int y){
		if(x>0&&y>0 && x<blocks.length&&y<blocks[0].length){
			return blocks[x][y];
		}else{
			return new BlockAir();
		}
	}
	int damageDealt = 0;
	public void draw(Graphics2D g){
		
		
		
		for(int x = 0; x< blocks.length;x++){
			for(int y = 0; y< blocks[x].length;y++){
				if(blocks[x][y] !=null){
					
					g.drawImage(blocks[x][y].getTexture(), x*32, y*32, 32, 32, null);
					
					}else{
					setBlock(x,y,Start.instance.air);
				}
				blocks[x][y].onUpdate(x,y);
				
			}
			
		}
		if(getBlock((int)Start.instance.p.posX/32,((int)Start.instance.p.posY/32)+1) instanceof BlockCoin){
			setBlock((int)Start.instance.p.posX/32, ((int)Start.instance.p.posY/32)+1, Start.instance.air);
			Start.instance.p.coins++;
		}
		if(getBlock((int)Start.instance.p.posX/32,((int)Start.instance.p.posY/32)+2) instanceof BlockSpike){
			this.damageDealt =  10+(rand.nextInt(10)-5);
			Start.instance.p.lives-=damageDealt;
			Start.instance.world.addEntity(new Particle("-"+damageDealt,15,Color.RED), Start.instance.p.posX, Start.instance.p.posY);
			Start.instance.p.jumpHeight = Start.instance.p.posY-Start.instance.p.maxJumpHeight;
			Start.instance.p.jumping = true;
			Start.instance.p.onGround = false;
		}
		for(int i = 0; i <  entities.size();i++){
			if(!entities.get(i).dead){
				entities.get(i).draw(g);
				entities.get(i).update();
			}

		}
		
	}
	
	public void explosion(int x,int y,int size){
		for(int x1 = 0; x1 < size/5;x1++){
			for(int y1 = 0; y1 < size/5;y1++){
				Particle p =new Particle(ImageLoader.loadImage(getClass(), "/textures/misc/flame.png"),20);
				p.size = 20;
				this.addEntity(p, x-(size/2)+x1*5, y-(size/2)+y1*5);

			}
		}
	}
	public void doomexplosion(int x,int y,int size){
		boolean shouldBeHurt = false;
		int s = 0;
		for(int x1 = 0; x1 < size/5;x1++){
			for(int y1 = 0; y1 < size/5;y1++){
				Particle p =new Particle(ImageLoader.loadImage(getClass(), "/textures/misc/flame.png"),20);
				p.size = 20;
				this.addEntity(p, x-(size/2)+x1*5, y-(size/2)+y1*5);
				if(y1 < entities.size()){
					if(entities.get(y1).posX > x-(size/2) && entities.get(y1).posX < x+(size/2) && entities.get(y1).posY > y-(size/2) && entities.get(y1).posY < y+(size/2)){
						if(entities.get(y1) instanceof EntityPlayer){
							shouldBeHurt = true;
							s = y1;
						}
					}
				}
			}
		}
if(shouldBeHurt){
	this.damageDealt =  10+(rand.nextInt(10)-5);
	((EntityPlayer)entities.get(s)).lives-=damageDealt;
	Start.instance.p.lives-=damageDealt;
	Start.instance.world.addEntity(new Particle("-"+damageDealt,15,Color.RED), Start.instance.p.posX, Start.instance.p.posY);
}
	}
	
	
	public void addEntity(Entity e, int posX,int posY){
		e.posX = posX;
		e.posY = posY;
		e.startX = posX;
		e.startY = posY;
		e.id = entities.size()+1;
		entities.add(e);
	}
	
	public Entity getEntityByID(int id){
		return entities.get(id);
	}
}
