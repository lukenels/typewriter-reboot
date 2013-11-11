package typewriter;

import java.util.*;
import java.io.*;

public class testingNames{   
   
   public static String locationName()throws FileNotFoundException{
      Scanner firstName = new Scanner(new File("notes"+System.getProperty("file.separator")+"firstNames.txt"));
      Scanner lastName = new Scanner(new File("notes"+System.getProperty("file.separator")+"lastNames.txt"));
      Random rd = new Random();
      String[] firstNames = new String[100];
      String[] lastNames = new String[50];
      String place = "";
      for(int f=0;f<100;f++){
         firstNames[f] = firstName.next();
      }
      for(int l=0;l<50;l++){
         lastNames[l] = lastName.next();
      }
      int option = rd.nextInt(4);
      
      int clast = rd.nextInt(50);
      int clast2 = rd.nextInt(50);
      String cname = lastNames[clast] + " & " + lastNames[clast2];
      int ctype = rd.nextInt(3);
      String[] compType = {"Corp", "LLC", "Inc"};
      String companyType = compType[ctype];
      
      int rfirst = rd.nextInt(100);
      String rname = firstNames[rfirst]+"\'s ";
      String[] restType = {"Grill", "Bakery", "Pizzeria", "Steakhouse"};
      int rtype = rd.nextInt(4);
      String restaurantType = restType[rtype];    
       
      String[] initials = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
      int init = rd.nextInt(26);
      String firstInit = initials[init];
      int init2 = rd.nextInt(26);
      String secondInit = initials[init2];
      int last = rd.nextInt(50);
      String sname = lastNames[last];
      String[] schoolType = {"Primary", "Secondary", "Junior Secondary"};
      int schType = rd.nextInt(3);
      
      String[] parkClass = {"Public", "Community", "Neighborhood"};
      String[] parkType = {"Park", "Garden", "Recreational Center", "Field"};
      int pClass = rd.nextInt(3);
      int pType = rd.nextInt(4);
      int plast = rd.nextInt(5);
      
      String[] storeType = {"Jewelry", "Boutique", "Shoestore", "Groceries", "Clothing"};
      int stfirst = rd.nextInt(100);
      int stlast = rd.nextInt(50);
      int stType = rd.nextInt(5);
      String stfName = firstNames[stfirst];
      String stlName = lastNames[stlast];
      boolean fOrL = rd.nextBoolean();
    
      if(option==0){
         place = cname + " " + companyType;
      }else if(option==1){
         place = rname + restaurantType;
      }else if(option==2){
         place = firstInit + ". " + secondInit + ". " + sname + " " + schoolType[schType];
      }else if(option==3){
         place = lastNames[plast] + " " + parkClass[pClass] + " " + parkType[pType];
      }else if(option==4){
         if(fOrL){
            place = stfName + "\'s " + storeType[stType];
         }else{
            place = stlName + "\'s " + storeType[stType];
         }
      }    
      return place;
   }
   public static String streetName()throws FileNotFoundException {
      Random rd = new Random();
      Scanner lastName = new Scanner(new File("notes"+System.getProperty("file.separator")+"lastNames.txt"));
      String[] lastNames = new String[50];
      String[] suffixes = {"Ave", "St", "Blvd", "Ln", "Rd"};
      
      int[] streetNums = new int[16+1];
      String streetName = "";
      for(int i=0;i<50;i++){
         lastNames[i] = lastName.next();
      }
      for(int k=0;k<16+1;k++){
         streetNums[k] = k;
      }
      boolean numOrNot = false;
      int ratio = rd.nextInt(4)+1;
      if(ratio<=3){
         numOrNot=true;
      }
      int streetNum = rd.nextInt(16)+1;
      int sName = rd.nextInt(50);
      int sSuffix = rd.nextInt(5);
      int nSuffix = rd.nextInt(2);
      String numSuffix;
      String lName = lastNames[sName];
      if(numOrNot){
         if(streetNum==1){
            numSuffix = "st";
         }else if(streetNum==2){
            numSuffix = "nd";
         }else if(streetNum==3){
            numSuffix = "rd";
         }else{
            numSuffix = "th";
         }
         streetName = "" + (streetNums[streetNum])+ numSuffix + " " + suffixes[nSuffix];
      }else{
         streetName = lastNames[sName] + " " + suffixes[sSuffix];
      }
   return streetName;
   }
}