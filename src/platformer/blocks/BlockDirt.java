package platformer.blocks;

import platformer.resources.ImageLoader;

public class BlockDirt extends Block{

	public BlockDirt(){
		this.setName("Grass");
		this.setId('B');
		this.setTexture(ImageLoader.loadImage(getClass(), "/textures/blocks/dirt_1.png"));
	}
	
}
