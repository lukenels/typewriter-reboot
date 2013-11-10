package typewriter;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.*;
public class World {


	Location hub;

	Player player;


	Set<Actor> actors;


	public World() {



		player = new Player();
		
		
		hub = new Location();
		player.location = hub;
		hub.name = "mainstreet";
		generateLocations(hub, 1);
		for(Actor a : actors) {
			a.setUpFeelings(actors);
		}
		reflexifyLocations(hub);

	}

	public void generateLocations(Location hub, int level) {

		Random rd = new Random();

		if(rd.nextInt(2) == 0) actors.add(new Actor()); 

		Set<Location> locations = new HashSet<Location>();
		locations.add(new Location());
		locations.add(new Location());
		locations.add(new Location());
		for(Location l : locations) {


			if(rd.nextInt(level) == 0) {

				generateLocations(l, level+1);
				l.name = NameGenerator.getStreetName();
			} else {
				l.accessible = new HashSet<Location>();
				l.name = NameGenerator.getBuildingName();
			}
			
		}

		hub.accessible = locations;
	}

	public Set<Actor> actorsInLocation(Location l) {
		Set<Actor> result = new HashSet<Actor>();
		for(Actor a : actors) {
			if(a.location == l) result.add(a);
		}
		return result;
	}

	public void reflexifyLocations(Location hub) {
		if(hub.accessible.isEmpty()) {
			return;
		} else {
			for(Location l : hub.accessible) {
				reflexifyLocations(l);
				l.accessible.add(hub);
			}
		}
	}

	public String processCommand(String[] command) {
		if(command.length==0) throw new IllegalArgumentException();

		switch(command[0]) {

			case "help":
				return "Possible commands are [goto, whereami, pathsfromhere, whatishere, drop, pickup, examine]";

			case "goto": 
				if(command.length == 1) throw new IllegalArgumentException();
				for(Location l : player.location.accessible) {
					if(l.name.equals(command[1])) {
						player.location = l;
						return "You Walked to " + l.name;
					}
				}
				return command[1] + " was not found.";



			case "peoplehere":
				return actorsInLocation(player.location).toString();


			case "whereami":
				return player.location.name;

			case "ask":
				if(command.length <= 2) throw new IllegalArgumentException();
				for (Actor a : actorsInLocation(player.location)) {
					if(a.name.equals(command[1])) {
						for(Actor b : actors) {
							if(b.name.equals(command[2])) {
								return a.getRelationship(b);
							}
						}
						return command[2]+" does not exist";
					}
				}
				return command[1]+" not found here.";


			case "pathsfromhere":
				return player.location.accessible.toString();

			case "whatishere":
				return player.location.props.toString();

			case "inventory":
				return player.inventory.toString();

			case "examine":
				if(command.length == 1) throw new IllegalArgumentException();
				for(Prop p : player.location.props) {
					if(p.name.equals(command[1])) {
						return p.getDesc();
					}
				}
				for(Prop p : player.inventory) {
					if(p.name.equals(command[1])) {
						return p.getDesc();
					}
				}
				return command[1]+" was not found.";

			case "drop":
				if(command.length == 1) throw new IllegalArgumentException();
				{
					Iterator<Prop> iterr = player.inventory.iterator();
					while(iterr.hasNext()) {
						Prop p = iterr.next();
						if(p.name.equals(command[1])) {
							player.location.props.add(p);
							iterr.remove();
							return "You dropped "+command[1];
						}
					}
					return "You do not have "+command[1];
				}

			case "pickup":
				if(command.length == 1) throw new IllegalArgumentException();
				{
					Iterator<Prop> itr = player.location.props.iterator();
					while(itr.hasNext()) {
						Prop p = itr.next();
						if(p.name.equals(command[1])) {
							player.inventory.add(p);
							itr.remove();
							return "You picked up "+command[1];
						}
					}
					return command[1]+" was not found here.";
				}
		}

		throw new IllegalArgumentException();
	}

}