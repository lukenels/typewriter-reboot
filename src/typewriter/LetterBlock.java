package typewriter;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
public class LetterBlock {

	Texture tex;
	char c;

	public LetterBlock(Texture t, char c) {
		tex = t;
		this.c = c;
	}

	public void render() {
		float[] thing = LetterGrid.bindTextureNums(c);

		glEnable(GL_TEXTURE_2D);

		tex.bind();

		glBegin(GL_QUADS);

		//{ratiox, ratioy, ratioxend, ratioyend}
		

		glTexCoord2f(thing[0], thing[1]);
		 glVertex2f(0.0f,0.0f);

		 glTexCoord2f(thing[0], thing[3]);
		 glVertex2f(0.0f,23.0f);

		 glTexCoord2f(thing[2], thing[3]);
		  glVertex2f(14.0f,23.0f);

		 glTexCoord2f(thing[2], thing[1]);
		 glVertex2f(14.0f,0.0f);

		 

		
		  

		glEnd();


		glDisable(GL_TEXTURE_2D);
	}

}