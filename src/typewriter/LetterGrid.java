package typewriter;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
public strictfp class LetterGrid {
	
	LetterBlock[][] data = new LetterBlock[32][64];

	Texture letters;

	int offset = 0;

	public LetterGrid() {
		letters = ImageLoader.loadTexture("font");

		for(int i = 0; i < 32; i++) {
			for(int j = 0; j < 64; j++) {
				data[i][j] = new LetterBlock(letters, (char) 0);
			}
		}
	}

	public void render() {
		for(int i = 0; i < 32; i++) {
			for(int j = 0; j < 64; j++) {
				glPushMatrix();
				glTranslatef(j*14.0f, i*23.0f, 0.0f);
				//System.out.println(i+","+j);
				data[i][j].render();
				glPopMatrix();
			}
		}
	}

	public int cursorx = 0;
	public int cursory = 31;

	public void typeCharacter(char c) {
		if(cursorx == 64) moveUp();
		data[cursory][cursorx] = new LetterBlock(letters, c);
		cursorx++;
		System.out.println(c);
	}

	public boolean deleteCharacter() {
		if(cursorx == 0) return false;
		cursorx--;
		data[cursory][cursorx] = new LetterBlock(letters, (char) 0);
		return true;
	}

	public void moveUp() {
		for(int i = 0; i < 31; i++) {
			for(int j = 0; j < 64; j++) {
				data[i][j] = data[i+1][j];
			}
		}
		for(int j = 0; j < 64; j++) {
			data[31][j] = new LetterBlock(letters, (char) 0);
		}
		cursorx = 0;
		offset++;
		if(offset == 38) offset = 0;
	}



	
	//14 px by 23 px;
	//16 letters across each
	// 224 pixels wide
	// 368 pixels tall


	public static float[] bindTextureNums(char c) {

		float[] result = new float[4];
		int id = (int) c;
		int column = id/16;
		int row = id%16;

		int pixx = 28 * column;
		int pixy = 46 * row;




		result[0] = ((float) pixx) / 1024f;
		result[1] = ((float) pixy) / 1024f;

		pixx += 28;
		pixy += 46;


		result[2] = ((float) pixx) / 1024f;
		result[3] = ((float) pixy) / 1024f;

 		return result;

		//{ratiox, ratioy, ratioxend, ratioyend}
	}

}