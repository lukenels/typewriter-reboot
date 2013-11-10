package typewriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ImageLoader {
	public static Texture loadTexture(String key) {
		try {
			return TextureLoader.getTexture("PNG",new FileInputStream(new File("res/"+key+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(-1);
		return null;
	}


	public static Audio loadAudio(String key) {
		try {
			return AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/"+key+".wav"));
		} catch (Exception e) {
			System.out.println("error");
		}
		return null;
	}



	
}
