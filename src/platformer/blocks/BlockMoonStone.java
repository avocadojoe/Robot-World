package platformer.blocks;

import platformer.resources.ImageLoader;

public class BlockMoonStone extends Block{

	public BlockMoonStone(){
			this.setName("MoonStone");
			this.setId('|');
			this.setTexture(ImageLoader.loadImage(getClass(), "/textures/blocks/moonstone.png"));
	}
	
}
