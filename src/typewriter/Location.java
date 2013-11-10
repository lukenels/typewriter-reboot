package typewriter;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
public class Location {
	public String name;
	public Set<Location> accessible;
	public Set<Prop> props;
	//public Set<Actor> actors;

	public Location() {
		props = new HashSet<Prop>();
		Random rd = new Random();
		while(rd.nextInt(2) != 0) {
			Prop p = new Prop();
			p.name = "Prop_" + rd.nextInt(99999);
			props.add(p);
		}
	}


	public String toString() {return name;}

}