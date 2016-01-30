package platformer.blocks;

import java.awt.Image;

public class Block {

	private Image texture;
	
	private boolean collision = true;
	
	private char id;
	
	private String name = "";
	
	private boolean liquid = false;
	
	public Block(){
	}

	public boolean canCollideWithPlayer() {
		return collision;
	}

	public void setCollideWithPlayer(boolean collision) {
		this.collision = collision;
	}

	public boolean isLiquid() {
		return liquid;
	}

	public void setLiquid(boolean liquid) {
		this.liquid = liquid;
	}

	public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getTexture() {
		return texture;
	}

	public void setTexture(Image texture) {
		this.texture = texture;
	}

	public void onUpdate(int x, int y){
		
	}
	
}
