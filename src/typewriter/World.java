package typewriter;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.*;
public class World {

	boolean murderOccurred = false;
	Location hub;

	Location heaven;
	Location hell;

	Player player;

	boolean firstTurn;

	boolean gameEnded = false;


	Set<Actor> actors;


	public World() {
		firstTurn = true;

		actors = new HashSet<Actor>();

		player = new Player();

		heaven = new Location();
		hell = new Location();
		heaven.name = "heaven";
		hell.name = "hell";
		heaven.accessible = new HashSet<Location>();
		heaven.accessible.add(hell);
		hell.accessible = new HashSet<Location>();
		hell.accessible.add(heaven);
		
		hub = new Location();
		player.location = hub;
		hub.name = "Main Street";
		generateLocations(hub, 1);
		for(Actor a : actors) {
			a.setUpFeelings(actors);
		}
		reflexifyLocations(hub);

	}

	public void generateLocations(Location hub, int level) {

		Random rd = new Random();

		Actor act = new Actor(this	);
		actors.add(act);
		act.location = hub;

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

	public String processCommand(String command, String params) {

		String buffer = "";

		if (firstTurn) {
			buffer += "   You are Private Eye Clint Denton.\n   In a few hours, a murder will occur somewhere in the city.\n   Your job is to track down the murderer\n   and bring them to justice.\n";
			firstTurn = false;
			for(Actor actr : actors) {
				String s = actr.update();
				if(!s.equals("")) {
					buffer += s+"\n";
				}
			}
		} else {

			if(command == "") throw new IllegalArgumentException("0 command");

			switch(command) {

				case "help":
					buffer += "Possible commands are 'goto', 'whereami', 'paths', 'look'";
					break;
				case "goto": 
					if(params == "") throw new IllegalArgumentException("1 goto");
					boolean foundTarget = false;
					for(Location l : player.location.accessible) {
						if(l.name.equals(params)) {
							foundTarget = true;
							player.location = l;
							buffer +=  "You walk to " + l.name + ".";
						}
					}
					if (!foundTarget){buffer += params + " isn't a valid location.";}

					break;
				case "whereami":
					buffer +=  "You are at "+player.location.name+".";
					break;
				case "ask":
					String[] names = params.split(" ");
					if(names.length < 2) throw new IllegalArgumentException();
					boolean foundTarget1 = false;
					boolean foundTarget2 = false;
					for (Actor a : actorsInLocation(player.location)) {
						if(a.name.equals(names[0])) {
							foundTarget1 = true;
							for(Actor b : actors) {
								if(b.name.equals(names[1])) {
									foundTarget2 = true;
									buffer +=  a.talkAbout(b);
								}
							}
							if (!foundTarget2)
								buffer +=  names[1]+" does not exist";
						}
					}
					if (!foundTarget1)
						buffer +=  names[0]+" not found here.";

					break;
				case "paths":
					buffer += "From here you can go to:";
					for (Location l : player.location.accessible)
						buffer += "\n   -"+l.name;
					break;
				case "look":
					buffer += "People nearby:";
					boolean noActors = true;
					for (Actor a : actorsInLocation(player.location)){
						buffer +=  "\n   -"+a.name;
						noActors = false;
					}
					if (noActors)
						buffer += "\n   -None";

					buffer += "\nItems nearby:";
					boolean noProps = true;
					for (Prop p : player.location.props){
						buffer += "\n   -"+p.name;
						noProps = false;
					}
					if (noProps)
						buffer += "\n   -None";
					break;
				case "accuse":
					boolean success = false;
					for (Actor a : actors) {
						if (a.murderer && a.name == params) {
							success = true;
						}
					}
					if (success) {
						buffer += "\nYou accuse "+params+" of cold-blooded murder!";
						buffer += "\nThey were the murderer!";
						buffer += "\nCongratulations on putting a menace behind bars.";
					} else {
						buffer += "\nYou accuse "+params+" of cold-blooded murder!";
						buffer += "\nUnfortunately, they were innocent.";
						buffer += "\nThanks to your incompetence, a menace to";
						buffer += "\nsociety still walks our streets.";
					}
					gameEnded = true;
				/*
				case "inventory":
					buffer +=  player.inventory.toString();
					break;
				case "examine":
					if(command.length == 1) throw new IllegalArgumentException();
					for(Prop p : player.location.props) {
						if(p.name.equals(command[1])) {
							buffer +=  p.getDesc();
						}
					}
					break;
					for(Prop p : player.inventory) {
						if(p.name.equals(command[1])) {
							buffer +=  p.getDesc();
						}
					}
					buffer +=  command[1]+" was not found.";
					break;
				case "drop":
					if(command.length == 1) throw new IllegalArgumentException();
					{
						Iterator<Prop> iterr = player.inventory.iterator();
						while(iterr.hasNext()) {
							Prop p = iterr.next();
							if(p.name.equals(command[1])) {
								player.location.props.add(p);
								iterr.remove();
								buffer +=  "You dropped "+command[1];
							}
						}
						buffer +=  "You do not have "+command[1];
					}
					break;
				case "pickup":
					if(command.length == 1) throw new IllegalArgumentException();
					{
						Iterator<Prop> itr = player.location.props.iterator();
						while(itr.hasNext()) {
							Prop p = itr.next();
							if(p.name.equals(command[1])) {
								player.inventory.add(p);
								itr.remove();
								buffer +=  "You picked up "+command[1];
							}
						}
						buffer +=  command[1]+" was not found here.";
					}
				*/
			}
		}
		buffer += "\n";
		for(Actor actr : actors) {
			String s = actr.update();
			if(!s.equals("")) {
				buffer += s+"\n";
			}
		}

		return buffer+"\n > ";
	}

}