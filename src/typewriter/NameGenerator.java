import java.util.Random;
public class NameGenerator {
	
	public static String getStreetName() {}


		String[] streetNames1 = new String[9];
		streetnames1[0] = "101st ";
		streetnames1[1] = "92nd ";
		streetnames1[2] = "63rd ";
		streetnames1[3] = "414th ";
		streetnames1[4] = "231st ";
		streetnames1[5] = "12nd ";
		streetnames1[6] = "303rd ";
		streetnames1[7] = "458th ";
		streetnames1[8] = "37th";


		String[] streetNames2 = new String[9];
		streetnames2[0] = "Parkton ";
		streetnames2[1] = "Berryhill ";
		streetnames2[2] = "Marbury ";
		streetnames2[3] = "Stirling ";
		streetnames2[4] = "Ferris ";
		streetnames2[5] = "Darton ";
		streetnames2[6] = "Milbern ";
		streetnames2[7] = "Forest ";
		streetnames2[8] = "Pine ";


		String[] streetNames3 = new String[2];
		streetnames3[0] = "Avenue";
		streetnames3[1] = "Street";


		String[] streetNames4 = new String[4];
		streetnanes4[0] = "Cove";
		streetnanes4[1] = "Ridge";
		streetnanes4[2] = "Road";
		streetnanes4[3] = "Heights";

		Random rd = new Random();

		if(rd.nextInt(2) == 0) {
			String front = streetNames1[rd.nextInt(9)];
			String back = streetnames3[rd.nextInt(2)];
			return front+back;
		} else {
			String front = streetnames2[rd.nextInt(9)];
			String back = streetnames4[rd.nextInt(4)];
			return front+back;
			}


		}
	}
}




  
