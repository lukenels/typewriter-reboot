
package typewriter;
import java.io.File;
import java.io.FileNotFoundException;
public class TypewriterGameModel {
	
	String buffer;

	CommandInterpreter interpreter;


	World world = new World();

	public void processLine(String text) {
		try {
			interpreter = new CommandInterpreter(new File("syn.txt.txt"));
		} catch (FileNotFoundException e) {

		}

		buffer = world.processCommand(text.split(" "));


	}

	public String getText() {
		return buffer;
	}



}