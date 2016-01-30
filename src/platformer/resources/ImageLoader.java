package platformer.resources;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageLoader {

	public static Image loadImage(@SuppressWarnings("rawtypes") Class clazz, String name){
		return new ImageIcon(clazz.getResource(name)).getImage();
	}
}
