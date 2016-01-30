package platformer.blocks;

import platformer.resources.ImageLoader;



public class BlockGrass extends Block{

	public BlockGrass(){
		this.setName("Grass");
		this.setId('A');
		this.setTexture(ImageLoader.loadImage(getClass(), "/textures/blocks/grass.png"));
	}
}
