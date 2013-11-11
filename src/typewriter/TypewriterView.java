package typewriter;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.opengl.*;
import org.newdawn.slick.openal.Audio;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
public class TypewriterView {


	LetterGrid grid;

	Texture paperTex;

	Audio keyStrokeSound;
	Audio returnSound;
	
	public TypewriterView() {
		try {
			Display.setDisplayMode(new DisplayMode(896, 736));
			Display.setTitle("Game");
			Display.create();
			Display.setVSyncEnabled(true);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();  
			glOrtho(0,896,736,0,1,-1);
			glMatrixMode(GL_MODELVIEW);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 

			grid = new LetterGrid();
			keyStrokeSound = ImageLoader.loadAudio("key");
			returnSound = ImageLoader.loadAudio("return");
			paperTex = ImageLoader.loadTexture("paper");


			} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	

	int count = 0;

	//170 advances before reset

	//down to 1642

	//51.3125 pixels per



	public boolean update() {
		if (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			glLoadIdentity();
			

			glEnable(GL_TEXTURE_2D);
			paperTex.bind();

			glBegin(GL_QUADS);



			glTexCoord2f(0f, 0.4008789063f+(grid.offset*0.01252746582f));
			glVertex2f(0f, 736f);

			glTexCoord2f(0.4880371094f, 0.4008789063f+(grid.offset*0.01252746582f));
			glVertex2f(896f, 736f);

			glTexCoord2f(0.4880371094f, 0f+(grid.offset*0.01252746582f));
			glVertex2f(896f, 0f);

			glTexCoord2f(0f,0f+(grid.offset*0.01252746582f));
			glVertex2f(0f, 0f);


			glEnd();


			glDisable(GL_TEXTURE_2D);

		    grid.render();

		    
            
            

			Display.update();
			Display.sync(60);
		} else {
			Display.destroy();
			System.exit(0);
		}
		

		if(count == 2){
			count = 0;
			return true;
		}  else {
			count++;
			return false;
		}
	}

	public void printCharacter(char c) {
		keyStrokeSound.playAsSoundEffect(1.0f,1.0f,false);
		grid.typeCharacter(c);
	}

	public void deleteLastCharacter() {
		
		if (grid.deleteCharacter()) keyStrokeSound.playAsSoundEffect(1.0f,1.0f,false);
	}

	public void carriageReturn() {
		returnSound.playAsSoundEffect(1.0f,100.0f,false);
		grid.moveUp();
		
	}

}