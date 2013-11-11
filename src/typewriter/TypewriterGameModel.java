
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
			String[] splitCommand = text.split(" ");
			String command = splitCommand[0];
			String params = "";
			for (int i = 1; i < splitCommand.length; i++)
				params += splitCommand[i]+" ";
			params = params.trim();
			buffer = world.processCommand(command, params);
		} catch (IllegalArgumentException exc) {
			exc.printStackTrace();
		}


	}

	public String getText() {
		return buffer;
	}



}