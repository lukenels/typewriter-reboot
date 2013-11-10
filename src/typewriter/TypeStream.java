package typewriter;
import java.io.PrintStream;
public class TypeStream extends PrintStream {
	TypewriterView view;

	public TypeStream() throws Exception {
		super((java.lang.String)"file.txt");
		view = new TypewriterView();
	}

	public void update() {
		view.update();
	}

	@Override
	public void print(String s) {
		for(char c : s.toCharArray()) {
			while(!view.update());
			view.printCharacter(c);
		}
	}

	public void println() {
		while(!view.update());
			view.carriageReturn();
	}


}