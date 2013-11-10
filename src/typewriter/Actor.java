package typewriter;
import java.util.Set;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
public class Actor {


	 public Map<Actor, Feeling> feelings;

	 Location location;

	 public Actor() {
	 	
	 }

	 public void setUpFeelings(Set<Actor> s) {
	 	for (Actor a : s) {
	 		feelings.put(a, getRandomFeeling());
	 	}
	 }

	String name;

	public static enum Feeling {
		LIKE, NEUTRAL, HATE;
	}

	public String getRelationship(Actor other) {
		switch(feelings.get(other)) {
			case LIKE: return name+" likes "+other.name;
			case NEUTRAL: return name+" is ambigous about "+other.name;
			case HATE: return name+" hates "+other.name;
		}
		return null;
	}

	public static Feeling getRandomFeeling() {
		Feeling[] f = Feeling.values();
		Random rd = new Random();
		return f[rd.nextInt(3)];
	}



}