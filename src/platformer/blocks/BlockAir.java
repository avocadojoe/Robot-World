package platformer.blocks;

import platformer.resources.ImageLoader;

public class BlockAir extends Block{

	public BlockAir(){
		this.setName("Air");
		this.setCollideWithPlayer(false);
		this.setTexture(ImageLoader.loadImage(getClass(), "/textures/blocks/air.png"));
	}
	
}
