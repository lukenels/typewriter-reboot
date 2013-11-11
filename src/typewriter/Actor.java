package typewriter;
import java.util.Set;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
public class Actor {



	public Map<Actor, Integer> lastSeenTime;
	public Map<Actor, Location> lastSeenPlace;
	public boolean murderer = false;

	public String talkAbout(Actor person) {
	   
	   Feeling f = this.feelings.get(person);
	   boolean hasSeen = lastSeenPlace.keySet().contains(person);
	   
	   if (personality == Feeling.NEUTRAL || personality == Feeling.LIKE) {
	      if (hasSeen) {
	         int time = lastSeenTime.get(person);
	         Location place = lastSeenPlace.get(person);
	         String response;
	         if (time < 6)
	            response = "I saw "+person.name+" just now at "+place.name+".";
	         else if (time < 12)
	            response = "I saw "+person.name+" a while ago.";
	         else
	            response = "I haven't seen"+person.name+" recently.";
	        return name+": \""+response+"\"";
	        }
	   } else {
	   	  String[] responses = {"Get out of here, gumshoe.", "Who're you looking at?", "Don't pester me with your questions."};
	   	  Random r = new Random();
	   	  int dice = r.nextInt(responses.length);
	   	  return name+": \""+responses[dice]+"\"";
	   }
	   return "";
	}

	public String update() {
	   Random r = new Random();
	   
	   for (Actor a : lastSeenTime.keySet()) {
	      lastSeenTime.put(a, lastSeenTime.get(a)+1);
	   }
	   for (Actor a : world.actorsInLocation(location)) {
	      lastSeenTime.put(a, 0);
	      lastSeenPlace.put(a, location);
	   }
	   
	   if (r.nextInt(5) == 0) {
	      int gotoIndex = r.nextInt(Math.max(1, location.accessible.size()-1));
	      Iterator<Location> gotoIter = location.accessible.iterator();
	      Location target = gotoIter.next();
	      for (int i = 0; i < gotoIndex; i++) {
	         target = gotoIter.next();
	      }
	      String message = "";
	      if (world.player.location == location)
	         message = name+" left "+location.name+".";
	      this.location = target;
	      if (world.player.location == location)
	         message = name+" entered "+location.name+".";
	      return message;
	   } else {
	      Actor victim = null;
	      for (Actor a : world.actorsInLocation(location)) {
	         if (feelings.get(a) == Feeling.HATE) {
	            victim = a;
	         }
	      }
	      Prop weapon = null;
	      if (location.props.size() > 0) {
	         weapon = location.props.iterator().next();
	      }
	      boolean opportunity = (world.actorsInLocation(location).size() == 2);
	      if (opportunity && (victim != null) && (weapon != null) && !world.murderOccurred) {
	         this.kill(victim, weapon);
	         this.murderer = true;
	      }
	   }
	   return "";
	}

	public void kill(Actor victim, Prop weapon) {
	   victim.location = world.heaven;
	   weapon.name = "Bloody "+weapon.name;
	   Prop body = new Prop();
	   body.name = "Dead body";
	   location.props.add(body);
	   this.personality = Feeling.HATE;
	}

	 public Map<Actor, Feeling> feelings;

	 Location location;

	 public Feeling personality;

	 World world;

	 public Actor(World w) {
	 	name = NameGenerator.getActorName();
	 	personality = getRandomFeeling();
	 	world = w;
	 	this.lastSeenTime = new HashMap<Actor, Integer>();
	 	this.lastSeenPlace = new HashMap<Actor, Location>();
	 }

	 public void setUpFeelings(Set<Actor> s) {
	 	feelings = new HashMap<Actor, Feeling>();
	 	for (Actor a : s) {
	 		feelings.put(a, getRandomFeeling());
	 	}
	 }

	String name;

	

	public static enum Feeling {
		LIKE, NEUTRAL, HATE;
	}

	public String toString() {return name;}

	public static Feeling getRandomFeeling() {
		Feeling[] f = Feeling.values();
		Random rd = new Random();
		return f[rd.nextInt(3)];
	}



}