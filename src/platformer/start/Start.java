 package platformer.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import platformer.blocks.Block;
import platformer.blocks.BlockAir;
import platformer.blocks.BlockCoin;
import platformer.blocks.BlockDirt;
import platformer.blocks.BlockGrass;
import platformer.blocks.BlockMoonStone;
import platformer.blocks.BlockShooter;
import platformer.blocks.BlockSpike;
import platformer.entity.EntityBoss;
import platformer.entity.EntityMonster;
import platformer.entity.EntityShooter;
import platformer.entity.player.EntityPlayer;
import platformer.input.InputHandler;
import platformer.resources.ImageLoader;
import platformer.world.Planet;
import platformer.world.World;

public class Start {
	public static Start instance = new Start();

	public Display d = new Display();
	public boolean networkHost = false;
	public boolean paused = false;
	public boolean hasSpaceship = true;
	public boolean mainmenu = true;
	public EntityPlayer p = new EntityPlayer(1);
	
	
	public Planet planet = Planet.EARTH;
	public static InputHandler in = new InputHandler();
	
	public Block air = new BlockAir();
	
	public Block[] blockTypes = {new BlockDirt(),new BlockGrass(), new BlockCoin(),new BlockSpike(),new BlockShooter(),new BlockMoonStone()};
	 public World loadMap(String name)
		
	  {
try{
	    String filename = "/maps/"+planet.getName()+"/" + name;
	    
	    ArrayList<String> lines = new ArrayList<String>();
	    int width = 0;
	    int height = 0;
	    try
	    {
	      BufferedReader reader = new BufferedReader(new InputStreamReader(
	        getClass().getResourceAsStream(filename)));
	      for (;;)
	      {
	        String line = reader.readLine();
	        if (line == null)
	        {
	          reader.close();
	          break;
	        }
	        if (!line.startsWith("#"))
	        {
	          lines.add(line);
	          width = Math.max(width, line.length());
	        }
	      }
	      height = lines.size();
	      World newMap = new World(width, height);
	      for (int y = 0; y < height; y++)
	      {
	        String line = (String)lines.get(y);
	        for (int x = 0; x < line.length(); x++)
	        {
	          char ch = line.charAt(x);
	          newMap.setBlock(x, y, air);

	          if(ch == 'A'){
		          newMap.setBlock(x, y, blockTypes[1]);

	          }
	          if(ch == 'B'){
		          newMap.setBlock(x, y, blockTypes[0]);

	          }
	          if(ch == 'O'){
		          newMap.setBlock(x, y, blockTypes[2]);

	          }
	          if(ch == '^'){
		          newMap.setBlock(x, y, blockTypes[3]);

	          }
	          if(ch == 'T'){
		          newMap.setBlock(x, y, blockTypes[5]);

	          }
	          if(ch == '*'){
		          newMap.addEntity(new EntityMonster(), x*32, (y*32)-64);

	          }
	          if(ch == 'i'){
		          newMap.addEntity(new EntityBoss(), x*32, (y*32)-64);

	          }
	          
	          if(ch == '!'){
		          newMap.addEntity(new EntityShooter(), x*32, (y*32));

		          newMap.setBlock(x, y, blockTypes[4]);

	          }
	          
	          
	        }
	      }
	      return newMap;
	    }
	    catch (FileNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
}catch(Exception e){
	 e.printStackTrace();

}
   return null;

	  }
	 public EntityPlayer loadPlayer(String name)
		
			  {
		 try{
			    String filename = "/playersaves/" + name;
			    
			    ArrayList<String> lines = new ArrayList<String>();
			    int width = 0;
			    int height = 0;
			    try
			    {
			      BufferedReader reader = new BufferedReader(new InputStreamReader(
			        getClass().getResourceAsStream(filename)));
			      for (;;)
			      {
			        String line = reader.readLine();
			        if (line == null)
			        {
			          reader.close();
			          break;
			        }
			        if (!line.startsWith("#"))
			        {
			          lines.add(line);
			          width = Math.max(width, line.length());
			        }
			      }
			      height = lines.size();
			      EntityPlayer player = new EntityPlayer(1);
			      for (int y = 0; y < height; y++)
			      {
			        String line = (String)lines.get(y);
			        if(line.startsWith("player_name=")){
			        	player.name = line.substring("player_name=".length());
			        }
			        if(line.startsWith("player_health=")){
			        	player.lives = Integer.parseInt(line.substring("player_health=".length()));
			        }
			        if(line.startsWith("player_coins=")){
			        	player.coins = Integer.parseInt(line.substring("player_coins=".length()));
			        }
			        if(line.startsWith("player_x=")){
			        	player.posX =  Integer.parseInt(line.substring("player_x=".length()));
			        }
			        if(line.startsWith("player_y=")){
			        	player.posY =  Integer.parseInt(line.substring("player_y=".length()));
			        }
			        if(line.startsWith("player_map=")){
			        	this.level = Integer.parseInt(line.substring("player_map=".length()));
			        }
			        if(line.startsWith("player_type=")){
			        	player.type = Integer.parseInt(line.substring("player_type=".length()));
			        }
			      }
			      return player;
			    }
			    catch (FileNotFoundException ex)
			    {
			      ex.printStackTrace();
			    }
		 }catch(Exception e){
			 e.printStackTrace();

		 }
		    return null;

			  }

	   public World world = loadMap("level1.map");
	
	
	public static void main(String[] args) {
		Start main = new Start();
		main.run();
		
	}

	public int level= 1;
	int t = 0;

	public int guiState = 1;
public int frame = 0;
	public void run(){
	
		p = loadPlayer("player.properties");
		init();
	
		while(true){
			
			if(frame <= 5){
				frame+=0.2;
			}
			if(frame >= 5){
				frame = 0;
			}
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			d.repaint();
			if(!networkHost && paused){
				pause();
			}                   
			else{ 
			
				gameLoop();
				
			}
		}
	}

	public void init(){


		d.setup();
		d.addMouseListener(in);
		d.addKeyListener(in);
		d.addMouseMotionListener(in);
	

		
	}
	public void pause(){}
	
	public void gameLoop(){

	}
	
	public void save(){
		
	}
	public double time = 0;
	public double time1 = 0;
int loadTime = 0;
	boolean a = true;
	
	public void button(int x,int y, int w, int h,Graphics g){
		if(in.mouseX > x && in.mouseX < x+w && in.mouseY > y && in.mouseY < y+h){
			g.fill3DRect(x, y-h/2, w, h, false);
		}else{
			g.fill3DRect(x, y-h/2, w, h, true);
		}
	}
	public void draw(Graphics g1){
		g1.setFont(new Font("Impact",Font.BOLD,20));
		if(guiState == -1){
			Graphics2D g = (Graphics2D)g1;
			g.clearRect(0, 0, d.getWidth(), d.getHeight());
			g.drawImage(ImageLoader.loadImage(getClass(), "/textures/backgrounds/logo.png"),0, 0, 1200, 700,null);
			loadTime++;
			if(loadTime >150){
				if(loadTime-100 < 255){
					g.setColor(new Color(0,0,0,loadTime-100));

				}
				g.fillRect(0, 0, 1200, 700);
			}
			if(loadTime >450){
				guiState = 0;
			}
		}
		if(guiState == 0){
			Graphics2D g = (Graphics2D)g1;
			g.clearRect(0, 0, d.getWidth(), d.getHeight());
			g.drawImage(ImageLoader.loadImage(getClass(), "/textures/misc/logo.png"),0, 0, 1200, 600,null);
			g.setColor(Color.DARK_GRAY);
			g.fill3DRect(0, 580, 1200, 120, true);
			
			button(500,320,100,40,g);
		}
		
		if(guiState == 1){
			Graphics2D g = (Graphics2D)g1;
			g.setColor(new Color(10,10,(int)time1));
			g.fillRect(0, 0, 1200, 700);
			g.translate(600,500);
			g.rotate(time);
			g.drawImage(ImageLoader.loadImage(getClass(), "/textures/misc/sun.png"), -500, 0, 84*2, 84*2, null);
			if(planet == Planet.MOON){
				g.drawImage(ImageLoader.loadImage(getClass(), "/textures/misc/earth.png"), 300, 20, 84*2, 84*2, null);

			}else{
			g.drawImage(ImageLoader.loadImage(getClass(), "/textures/misc/moon.png"), 300, 20, 84*2, 84*2, null);
			}
			g.rotate(-time);

			g.translate(-600,-500);
			if(planet == Planet.EARTH){
				g.drawImage(ImageLoader.loadImage(getClass(), "/textures/backgrounds/background_default.png"), 0, 0, 1200, 700, null);
			}
			//g.setColor(new Color(8,8,8,0.3F));
			g.fillRect(0, 0, d.getWidth(), d.getHeight());

			time+=0.001;
			if(time< 2){
					time1+=0.1;
			}
			if(time>= 2){
				if(time1 >=1){
					time1-=0.1;

				}
			}
			if(time > 6){
				time = -0.1;
			}

				world.draw(g);
				gui(g);
		}
		
	}
	
	public void gui(Graphics2D g){
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(30, 40, 800, 70, true);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD,30));
		g.setColor(Color.DARK_GRAY);

		g.fill3DRect(100, 60, 200, 35, true);
		g.setColor(Color.GREEN);
		g.fillRect(102, 62, p.lives-4, 35-4);

		g.setColor(Color.BLACK);
		g.drawString(p.lives+"/"+p.maxLives, 152, 87);


		g.drawImage(p.faceTexture, 40, 40, 80, 80, null);
		
		g.drawString("X "+p.coins, 420, 90);

		g.drawImage(ImageLoader.loadImage(getClass(), "/textures/blocks/coin_1.png"), 360, 45, 60, 60, null);

		
	}
	 public static String getSubnet(String currentIP) {
	        int firstSeparator = currentIP.lastIndexOf("/");
	        int lastSeparator = currentIP.lastIndexOf(".");
	        return currentIP.substring(firstSeparator+1, lastSeparator+1);
	    }
}
