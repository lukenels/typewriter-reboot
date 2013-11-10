package typewriter;
import java.util.Random;
public class NameGenerator {
	
	public static String getStreetName() {


		String[] streetnames1 = new String[9];
		streetnames1[0] = "101st_";
		streetnames1[1] = "92nd_";
		streetnames1[2] = "63rd_";
		streetnames1[3] = "414th_";
		streetnames1[4] = "231st_";
		streetnames1[5] = "12nd_";
		streetnames1[6] = "303rd_";
		streetnames1[7] = "458th_";
		streetnames1[8] = "37th_";


		String[] streetnames2 = new String[9];
		streetnames2[0] = "Parkton_";
		streetnames2[1] = "Berryhill_";
		streetnames2[2] = "Marbury_";
		streetnames2[3] = "Stirling_";
		streetnames2[4] = "Ferris_";
		streetnames2[5] = "Darton_";
		streetnames2[6] = "Milbern_";
		streetnames2[7] = "Forest_";
		streetnames2[8] = "Pine_";


		String[] streetnames3 = new String[2];
		streetnames3[0] = "Avenue";
		streetnames3[1] = "Street";


		String[] streetnames4 = new String[4];
		streetnames4[0] = "Cove";
		streetnames4[1] = "Ridge";
		streetnames4[2] = "Road";
		streetnames4[3] = "Heights";

		Random rd = new Random();

		if(rd.nextInt(2) == 0) {
			String front = streetnames1[rd.nextInt(9)];
			String back = streetnames3[rd.nextInt(2)];
			return front+back;
		} else {
			String front = streetnames2[rd.nextInt(9)];
			String back = streetnames4[rd.nextInt(4)];
			return front+back;
			}
	}
		public static String getBuildingName() {

		Random rd = new Random();
		String[] buildingName1 = new String[9];
		buildingName1[0] = "Perkin_";
		buildingName1[1] = "Mendern_";
		buildingName1[2] = "Kimbern_";
		buildingName1[3] = "Stocklen_";
		buildingName1[4] = "Jenfer_";
		buildingName1[5] = "Darmon_";
		buildingName1[6] = "Neal_";
		buildingName1[7] = "Harvens_";
		buildingName1[8] = "Owin_";


		String[] buildingName2 = new String[5];
		buildingName2[0] = "Bank";
		buildingName2[1] = "Offiecs";
		buildingName2[2] = "Apartments";
		buildingName2[3] = "Mills";
		buildingName2[4] = "General_Store";


			String front = buildingName1[rd.nextInt(9)];
			String back = buildingName3[rd.nextInt(5)];
			return front+back;
}





  
