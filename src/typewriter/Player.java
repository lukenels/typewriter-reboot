package typewriter;
import java.util.*;
public class Player {
	
	Location location;

	Set<Prop> inventory;

	public Player() {
		inventory = new HashSet<Prop>();
	}

}