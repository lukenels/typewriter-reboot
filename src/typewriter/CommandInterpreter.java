package typewriter;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
public class CommandInterpreter {
	
	private Map<Set<String>, String> synonyms;

	public CommandInterpreter(File data) throws FileNotFoundException {
		synonyms = new HashMap<Set<String>, String>();
		Scanner scan = new Scanner(data);

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			Set<String> words = new HashSet<String>();
			for (String s : line.split("[:]")[1].split("[,]")) {
				words.add(s.trim());
			}
			synonyms.put(words, line.split("[:]")[0]);
		}
	}

	public String[] interpret(String command) {
		String[] words = command.split("[ \t]+");
		for (int i = 0; i < words.length; i++) {
			for(Set<String> s : synonyms.keySet()) {
				if(s.contains(words[i])) {
					words[i] = synonyms.get(s);
					break;
				}
			}
		}
		return words;
	}

}