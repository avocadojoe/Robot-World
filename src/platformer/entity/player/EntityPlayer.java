package platformer.entity.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import platformer.effect.Particle;
import platformer.entity.Entity;
import platformer.item.Item;
import platformer.item.LightningGun;
import platformer.resources.ImageLoader;
import platformer.start.Start;


public class EntityPlayer extends Entity
{

	public ArrayList<Image> animation = new ArrayList<Image>();
	int jetpackFuel = 200;
	
	boolean usingJetpack = false;
	
	public int type = 0;
	public String name = "joe";
	public int speed = 7;
	
	public int exp = 0;
	public boolean direction = false;
	public boolean moving =false;
	public boolean onGround = false;
	public boolean jumping = false;
	public int jumpHeight = 0;
	public double jumpSpeed = 15;
	public int maxJumpHeight = 200;
	public double fallSpeed = 7;
	
	public Item itemHeld = new LightningGun();
	
	public double armRot = 0;
	
	public int coins = 0;
	
	public int lives = 200;
	public int maxLives = 200;
	
	public Image faceTexture;
	public Image armTexture;

	public boolean jetpack = false;
	
	public EntityPlayer(int type){
		if(type == 0){
			animation.add(ImageLoader.loadImage(getClass(), "/textures/player/player_1.png"));
			animation.add(ImageLoader.loadImage(getClass(), "/textures/player/player_2.png"));
			faceTexture = ImageLoader.loadImage(getClass(), "/textures/player/player_face.png");
			armTexture = ImageLoader.loadImage(getClass(), "/textures/player/arm.png");
		}else{
			animation.add(ImageLoader.loadImage(getClass(), "/textures/player/robot_1.png"));
			animation.add(ImageLoader.loadImage(getClass(), "/textures/player/robot_2.png"));
			faceTexture = ImageLoader.loadImage(getClass(), "/textures/player/robot_face.png");
			armTexture = ImageLoader.loadImage(getClass(), "/textures/player/arm2.png");
speed = 8;
jetpack = true;
		}
	

	}
	public int frame = 0;
	public int frame1 = 0;
	int timer = 0;

	public void draw(Graphics2D g) {
if(direction){
	g.drawImage(animation.get(frame), posX+32, posY, -32,64, null);
	g.drawImage(faceTexture, posX+32, posY-10, -32,32, null);
g.translate(posX+20, posY+17);
g.rotate(armRot);
g.drawImage(armTexture, 0, 0, -(10), -(28), null);
g.drawImage(itemHeld.texture, -10, -28,itemHeld.texture.getWidth(null),-itemHeld.texture.getHeight(null), null);


g.rotate(-armRot);
g.translate(-(posX+20), -(posY+17));

}else{
	g.drawImage(animation.get(frame), posX, posY, 32,64, null);
	g.drawImage(faceTexture, posX, posY-10, 32,32, null);
	g.translate(posX+12, posY+17);
	g.rotate(armRot);
	g.drawImage(armTexture, 0, 0, (10), -(28), null);

	g.drawImage(itemHeld.texture, 10, -28,-itemHeld.texture.getWidth(null),-itemHeld.texture.getHeight(null), null);

	g.rotate(-armRot);
	g.translate(-(posX+12), -(posY+17));

}
if(Start.in.mousePressed){
	itemHeld.onItemUse(g);
}


	}
boolean b = false;
double rotation = 0;
	public void update() {
		if(timer > 20 ){
			if(lives<maxLives){
				lives++;
				Start.instance.world.addEntity(new Particle("+"+1,15,Color.GREEN), posX, posY);


			}
			timer = 0;
		
		}
		timer++;
		 int x = posX+12;
         int y = posY+17;

         int deltaX = Start.in.mouseX - x;
         int deltaY = Start.in.mouseY - y;

         rotation = -Math.atan2(deltaX, deltaY);

         rotation = Math.toDegrees(rotation) + 180;
		armRot = Math.toRadians(rotation);

		
		if(moving){
			if(b){
				frame1--;
			}else{
				frame1++;
			}
		}
		
		
		if(frame1 > 1){
			frame =0;
		}else{
			frame =1;

		}
		if(!moving){
		frame = 0;
		}
		
		if(Start.in.isKeyDown(KeyEvent.VK_RIGHT)){
			if(!Start.instance.world.getBlock((posX/32)+1, (posY/32)+1).canCollideWithPlayer()){

			posX+=speed;	
			}
			direction = false;
			moving = true;

			if(frame1 > 1){
				b = true;
				
			}
			if(frame1 < 0){
				b= false;
			}

		}else if(Start.in.isKeyDown(KeyEvent.VK_LEFT)){
			if(!Start.instance.world.getBlock((posX/32), (posY/32)+1).canCollideWithPlayer()){
				posX-=speed;	
			}
		direction = true;

		moving = true;
		if(frame1 > 1){
			b = true;
			
		}
		
		if(frame1 < 0){
			b= false;
		}
		
		
		}else{
		moving = false;
		
		}
		
		if(Start.in.isKeyDown(KeyEvent.VK_UP) && onGround){
			jumping = true;
			jumpHeight = posY-maxJumpHeight;
			onGround = false;
		}
		
		if(jumping){
			if(posY > jumpHeight){
				posY-=Math.round(jumpSpeed);
			}
			
			if(posY <= jumpHeight || Start.instance.world.getBlock((posX/32), (posY/32)).canCollideWithPlayer()){
				jumping = false;
			}
		}
		if(!this.jumping && Start.instance.world.getBlock((posX/32), (posY/32)+2).canCollideWithPlayer()){
			onGround = true;
			fallSpeed = 7;
			jumpSpeed = 15;
		}
		
		if( !jumping && !Start.instance.world.getBlock((posX/32), (posY/32)+2).canCollideWithPlayer()){
			posY+=15;
			fallSpeed+=0.1;
		}
		
		if(posX >= 1200){
			Start.instance.level++;
			Start.instance.world = Start.instance.loadMap("level"+Start.instance.level+".map");
			Start.instance.world.addEntity(this, 20, posY);
		}
		if(Start.instance.level > 0 && posX <= 0){
			Start.instance.level--;
			Start.instance.world = Start.instance.loadMap("level"+Start.instance.level+".map");
			Start.instance.world.addEntity(this, 1150, posY);
		}
		
		if(lives <=0){
			Start.instance.level = 1;
			Start.instance.world = Start.instance.loadMap("level"+Start.instance.level+".map");
		
			this.lives = maxLives;
			this.posX = 200;
			this.posY = 500;
			this.coins = 0;
			Start.instance.world.addEntity(this, 200, 200);

		}
		
		
	}

}
