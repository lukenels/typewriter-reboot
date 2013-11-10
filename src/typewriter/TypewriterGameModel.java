
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

		try {
			buffer = world.processCommand(text.split(" "));
		} catch (IllegalArgumentException exc) {
			buffer = "Command not recognized. Please try again.";
		}


	}

	public String getText() {
		return buffer;
	}



}