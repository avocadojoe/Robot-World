package platformer.blocks;

import java.awt.Image;

import platformer.resources.ImageLoader;
import platformer.start.Start;

public class BlockCoin extends Block{

	public Image[] images = {ImageLoader.loadImage(getClass(), "/textures/blocks/coin_1.png"),ImageLoader.loadImage(getClass(), "/textures/blocks/coin_2.png"),ImageLoader.loadImage(getClass(), "/textures/blocks/coin_3.png"),ImageLoader.loadImage(getClass(), "/textures/blocks/coin_4.png"),ImageLoader.loadImage(getClass(), "/textures/blocks/coin_3.png"),ImageLoader.loadImage(getClass(), "/textures/blocks/coin_2.png")};
	public BlockCoin(){
		this.setName("Coin");
		this.setTexture(ImageLoader.loadImage(getClass(), "/textures/blocks/coin_1.png"));
		this.setCollideWithPlayer(false);
		this.setId('O');
	}
	
	public void onUpdate(int x,int y){
		this.setTexture(images[(int) Math.round(Start.instance.frame)]);
	
		
	}
}
