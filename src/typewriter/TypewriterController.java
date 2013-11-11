
package typewriter;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import org.lwjgl.input.Keyboard;
import java.util.Scanner;
import java.io.File;
public class TypewriterController {
	
	private TypewriterGameModel model;

	private TypewriterView view;

	private Queue<TypewriterEvent> queue;

	public static void main(String[] args) throws Exception {
		  TypewriterController c = new TypewriterController();
		  while(true) c.update();
		
		// TypeStream s = new TypeStream();
		// System.setOut(s);

		// Scanner scan = new Scanner(new File(new Scanner(System.in).nextLine()));

		// while(scan.hasNextLine()) {
		// 	System.out.print(scan.nextLine());
		// 	System.out.println();
		// }

		// while(true) s.update();
		
	}

	public TypewriterController() {

		model = new TypewriterGameModel();
		view = new TypewriterView();
		//try {Keyboard.create();} catch (Exception e) {System.exit(-3);}
		queue = new ArrayBlockingQueue<TypewriterEvent>(512);
	}

	private String inputBuffer = "";
	private String outputBuffer = "";

	private boolean inputMode = true;

	private boolean firstTurn = true;


	int bsQuota = 0;



	public void update() {

		boolean ready = view.update();
		if (firstTurn) {
			inputMode = false;
			firstTurn = false;
			model.processLine("");
		}
		if(inputMode && !model.world.gameEnded) {
			while(Keyboard.next() && Keyboard.getEventKeyState()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
					inputMode = false;
					System.out.println(inputBuffer);
					model.processLine(inputBuffer);
					queue.add(new TypewriterEvent(TypewriterEvent.Type.NEW_LINE));
					break;
				} else if (Keyboard.getEventKey() == Keyboard.KEY_BACK && bsQuota > 0) {
					bsQuota = Math.max(bsQuota-1, 0);
					inputBuffer = inputBuffer.substring(0,inputBuffer.length()-1);
					queue.add(new TypewriterEvent(TypewriterEvent.Type.DELETE_CHAR));
				} else if ((int)Keyboard.getEventCharacter() >= 32 && (int)Keyboard.getEventCharacter() <= 126){
					inputBuffer += Keyboard.getEventCharacter();
					queue.add(new TypewriterEvent(TypewriterEvent.Type.PRINT_CHAR, Keyboard.getEventCharacter()));
					bsQuota++;
				}
			}
		}

		if(!inputMode) {
			inputBuffer = "";

			outputBuffer = model.getText();

			for(char c : outputBuffer.toCharArray()) {
				if(c != '\n') {
					queue.add(new TypewriterEvent(TypewriterEvent.Type.PRINT_CHAR, c));
				} else {
					queue.add(new TypewriterEvent(TypewriterEvent.Type.NEW_LINE));
				}
			}
			//queue.add(new TypewriterEvent(TypewriterEvent.Type.NEW_LINE));
			outputBuffer = "";

			inputMode = true;
			bsQuota = 0;
		}

		if(ready) {
			TypewriterEvent event = queue.poll();
			if(event != null) {
				event.execute(view);

			}
		}

	}

	public static class TypewriterEvent {

		private Type type;

		private char character;

		

		public static enum Type {
			NEW_LINE, PRINT_CHAR, DELETE_CHAR;
		}

		public TypewriterEvent(Type t){
			type = t;
		}

		public TypewriterEvent(Type t, char c) {
			this(t);
			character = c;
		}

		public void execute(TypewriterView view) {
			switch (type) {
				case NEW_LINE: view.carriageReturn(); break;
				case PRINT_CHAR: view.printCharacter(character); break;
				case DELETE_CHAR: view.deleteLastCharacter(); break;
			}
		}
	}

}