package typewriter;
import java.util.Scanner;
public class TestController {
	
	TypewriterGameModel model;

	public static void main(String[] args) {
		TypewriterView v = new TypewriterView();
		
		
		 
		new TestController().loop();

	}

	public TestController() {
		model = new TypewriterGameModel();
	}

	public void loop() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			model.processLine(scan.nextLine());
			System.out.println(model.getText());
		}
	}

}