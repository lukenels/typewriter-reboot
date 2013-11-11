package typewriter;

import java.util.Random;


public class Prop {
	String name;

	public Prop() {
		Random r = new Random();
		String[] names = {"Crowbar", "Tire Iron", "Sedan", "Station Wagon", "Truck", "Briefcase", "Potted Plant", "Letter Opener", "Breadknife","Chair","Table","Desk"};
		this.name = names[r.nextInt(names.length)];
	}

	public String toString() {return name;}

	public String getDesc() {
		return "";
	}

}