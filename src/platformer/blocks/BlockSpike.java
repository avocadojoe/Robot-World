package platformer.blocks;

import platformer.resources.ImageLoader;

public class BlockSpike extends Block{

	public BlockSpike(){
		this.setName("Spike");
		this.setId('^');
		this.setTexture(ImageLoader.loadImage(getClass(), "/textures/blocks/spike.png"));
	}
	
	public void onUpdate(){
		
	}
	
}
