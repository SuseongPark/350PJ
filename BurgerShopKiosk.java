/* MIS350 Project
   Group 2
   12/7/2022
   Suseong Park, DongJoo Chong
*/

import java.util.Scanner;
import java.util.Calendar;

public class BurgerShopKiosk { // main method
   public static void main(String [] args) {
      Scanner input = new Scanner(System.in);
      String placeOption;
      
      // Let users select place to eat (To go or For here)
      System.out.println("1. To go\n2. For here");
      System.out.print("Please select: ");
      if(input.nextInt() == 1)
         placeOption = "To go";
         else
            placeOption = "For here";
      
      // Display burger menus
      String[] burgerOption = {"Cheese Burger", "Hamburger", "Chicken Sandwich"};
      double[] burgerPrice = {15.99, 13.99, 14.99};
      
      System.out.println();      
      System.out.println("|\t\t\t\tMenu\t\t\t\t\t|\t Price \t|");
      System.out.println("-------------------------------------------");
      for(int i = 0; i < burgerOption.length; i++) {
         System.out.printf("|\t\t%d. %-16s\t\t|\t%5.2f $\t|\n", i + 1, burgerOption[i], burgerPrice[i]);
      }
      System.out.print("Please select the menu: ");
      int burgerOptionInt = input.nextInt();
      int option1 = (burgerOptionInt - 1);
      String userBurgerOption = burgerOption[option1]; 
           
      // Additional Toppings
      String[] additionalToppings = {"Chicken Patty", "Beef Patty", "Cheese", "Lettuce", "Jalapeno", "Tomato", "Grilled Onion", "Bacon"};
      int[] selectedIngredients = new int[10];
      int size1 = selectedIngredients.length;
      String[] chosenIngredients = new String[size1];
      
      System.out.println();
      System.out.println("|\t\tAdditional Toppings\t\t|");
      System.out.println("-------------------------------");
      for(int i = 0; i < 10; i++) {
         for(int j = 0; j < additionalToppings.length; j++) {
         System.out.printf("|\t\t%d. %-16s\t\t|\n", j + 1, additionalToppings[j]);         
         }
      
         System.out.print("Please select additional toppings: ");      
         selectedIngredients[i] = input.nextInt();
      
         System.out.println("\nAnything else?\n1. Yes\n2. No");
         int option2 = input.nextInt();
         if(option2 != 1)
            break;
      }
      for(int i = 0; i < chosenIngredients.length; i++) {      
         chosenIngredients[i] = customizeIngredients(selectedIngredients[i]);
      }
      int size2 = chosenIngredients.length;
      
      // Side Menus      
      String[] sideList = {"Hashbrown", "Salad", "No"};
      double[] sidePrice = {2.99, 4.99, 0.0};
      int[] selectedSides = new int[3];
      String[] chosenSides = new String[selectedSides.length];
      double[] chosenSidesPrice = new double[3];
      
      System.out.println();
      System.out.println("|\t\t  Side Menu\t\t\t|\t Price \t|");      
      System.out.println("-------------------------------------");
      for(int j = 0; j < 3; j++) {      
         for(int i = 0; i < sideList.length; i++) {
            System.out.printf("|\t\t%d. %-10s\t\t|\t%5.2f $\t|\n", i + 1, sideList[i], sidePrice[i]);         
         }
         System.out.print("Please select side menu: ");
         selectedSides[j] = input.nextInt();
         System.out.println("\nAnything else?\n1. Yes\n2. No");
         int add2 = input.nextInt();
         if(add2 != 1)
            break;
      }
      for(int i = 0; i < chosenSides.length; i++) {      
         chosenSides[i] = customizeSide(selectedSides[i]);
      }
      
      for(int i = 0; i < chosenSidesPrice.length; i++) {      
         chosenSidesPrice[i] = customizeSidesPrice(selectedSides[i]);
      }

   // Choose size of fries and coke      
      System.out.println("\n|\t  Fries & Coke \t|\t Price \t|");      
      System.out.println("----------------------------------");      
      System.out.println("|\t\t1. Small \t\t|\t 0   $ \t|\n|\t\t2. Medium\t\t|\t 0.5 $ \t|\n|\t\t3. Large \t\t|\t 1.0 $ \t|\n|\t\t4. No\t\t\t\t|\t 0   $ \t|");
      System.out.print("Please select Fries & Coke size: ");
      int fbSizeOption = input.nextInt();
          
   // Order Summary   
      System.out.printf("\nOrder Summary\n---------------------------------------------------------\n%s \nBurger : %s\nIngredients : \n", placeOption, userBurgerOption);
      for(int i = 0; i < size2; i++) {
         System.out.printf("  %s", chosenIngredients[i]);
      }
      
      System.out.printf("\nSide : \n");
      for(int i = 0; i < chosenSides.length; i++) {
         System.out.printf("  %s", chosenSides[i]);
      }
      System.out.printf("\nFries & Coke : %s\n",customizeFC(fbSizeOption));
      System.out.println("---------------------------------------------------------\nPlease verify your order\n1. Proceed to next step\n2. Cancel my order");
      int verify = input.nextInt();
      if(verify == 2) {
         System.out.println("Please order again :)");
         System.exit(0);
      }
                  
   // Calculate bill amount      
      double burgerPriceNum = burgerPrice[option1];
      double sidePriceNum = 0.0;
      for(int i = 0; i < chosenSidesPrice.length; i++) {      
         sidePriceNum += chosenSidesPrice[i];
      }   
      double fncPriceNum = 0.0;
      if(fbSizeOption == 2)
         fncPriceNum = 0.5;
         else if(fbSizeOption == 3) 
            fncPriceNum = 1.0;
      
      double billAmt = burgerPriceNum + sidePriceNum + fncPriceNum;

   // Call tip option method           
      double tip = tipOption(billAmt);

   // Create reward system object
      double rwdPoint = billAmt * 0.1;
      double deductPoint = 0;
      
      RewardSystem[] rewardMembers = new RewardSystem[10];
   
      rewardMembers[0] = new RewardSystem(1234, 35, "Suseong Park");
      rewardMembers[1] = new RewardSystem(4567, 0, "Dongjoo Chong"); 
      rewardMembers[2] = new RewardSystem(7890, 15, "Lucie Li");
      rewardMembers[3] = new RewardSystem(1359, 25, "Jain Juhee");
      
      int membersPointer = 4;
   
   // Ask users reward point or not(Take user input)
      System.out.print("Do you want to get reward point?\n1. Yes\n2. No \n");
      int rewardChoice = input.nextInt();
      
   
   // Call methods from RewardSystem
      if(rewardChoice == 1) {
         int returnedIndex = searchMembers(rewardMembers, membersPointer);
         
         if(returnedIndex == -1) {
            System.out.println("Sorry you are not a member");
         } else {
            rewardMembers[returnedIndex].checkPoint();
            System.out.printf("\n1. Use Points\n2. Earn Points\n");
            int optionPoint = input.nextInt();
            
            if(optionPoint == 1) {
               System.out.printf("How much do you want to use?\n");
               deductPoint = input.nextDouble(); 
         
               rewardMembers[returnedIndex].usePoint(deductPoint);
               System.out.println();
            } else if(optionPoint == 2) {
               rewardMembers[returnedIndex].checkPoint();          
               System.out.println();
               rewardMembers[returnedIndex].earnPoint(rwdPoint);
               System.out.println(); 
            }    
         }
      }
   
   // Ask users if they need receipt or not      
      System.out.println("\nDo you need receipt??\n1. Yes\n2. No");
      int receiptCheck = input.nextInt();
   
   // Call the receipt printing method (final total price including tax and tip) - for those who want receipt      
      if(receiptCheck == 1) {
         Calendar now = Calendar.getInstance(); 
         int day = now.get(Calendar.DAY_OF_MONTH), hour = now.get(Calendar.HOUR), sec = now.get(Calendar.SECOND); 

         System.out.println("\nThanks for your order!");
         System.out.printf("Order number:\t\t  %d%d%d\n", day, hour, sec);
         System.out.println("---------------------------------------------------------");         
         System.out.printf("Order Summary\n---------------------------------------------------------\n%s \nBurger : %s\nIngredients : \n", placeOption, userBurgerOption);
         for(int i = 0; i < size2; i++) {
            System.out.printf("  %s", chosenIngredients[i]);
         }
         System.out.printf("\nSide : \n");
         
         for(int i = 0; i < chosenSides.length; i++) {
            System.out.printf("  %s", chosenSides[i]);
         }
         
         System.out.printf("\nFries & Coke : %s\n---------------------------------------------------------",customizeFC(fbSizeOption));
         receipt(billAmt, tip, deductPoint);
         }
   // Call simplified receipt method (order number) - for those who do not want receipt         
         else if (receiptCheck == 2) {
            simpleReceipt(billAmt, tip);
         }
   } // end main method

   // Other methods   
   // Burger customize Option method {}
      public static String customizeIngredients(int g){
         String result = "";
         switch(g) {
            case 1: 
               result = "Chicken Patty"; 
               break;
            case 2: 
               result = "Beef Patty"; 
               break;
            case 3: 
               result = "Cheese"; 
               break;
            case 4: 
               result = "Lettuce"; 
               break;
            case 5: 
               result = "Jalapeno"; 
               break;
            case 6: 
               result = "Tomato"; 
               break;
            case 7: 
               result = "Grilled Onion"; 
               break;
            case 8: 
               result = "Bacon"; 
               break;
            default: 
               break;
      } 
      return result;
   } 
   
   // Side customize Option method {}   
   public static String customizeSide(int z){
      String side = "";
      switch(z) {
         case 1: 
            side = "Hashborwn"; 
            break;
         case 2: 
            side = "Salad"; 
            break;
         case 3: 
            side = "No"; 
            break;
         default: 
            break;
      } 
      return side;
   }
   
   // side price method  
   public static double customizeSidesPrice(int r){
      double price = 0.0;
      switch(r) {
         case 1: 
            price = 2.99; 
            break;
         case 2: 
            price = 4.99; 
            break;
         case 3: 
            price = 0.0; 
            break;
         default: 
            break;
      } 
      return price;
   }
   
   // Fries and Coke size customize Option method {}
   public static String customizeFC(int y){
      String fbSize = "";
      switch (y){
         case 1: 
            fbSize = "Small";
            break;
         case 2:
            fbSize = "Medium";
            break;
         case 3:
            fbSize = "Large";
            break;
         case 4:
            fbSize = "No";
            break;
         default :
            break;
      } 
      return fbSize;
   }
   
   // tip option method {}   
   public static double tipOption(double bAmt) {
      Scanner input = new Scanner(System.in); 
         System.out.println("\n|\t  Select you Tip Amount \t|");      
         System.out.println("-------------------------------");         
         System.out.print("1. 10% tip\n2. 15% tip\n3. 18% tip\n4. 20% tip\n5. Enter a custimized tip amount\n6. No tip\nSelect an option by number, like 1, 2, etc.: ");
         int tipoption = input.nextInt();
         System.out.println();
         double tipAmt = 0;
               
         switch(tipoption) {
            case 1 : 
               tipAmt = bAmt * 10.0 / 100;
               break; 
            case 2 : 
               tipAmt = bAmt * 15.0 / 100; 
               break; 
            case 3 : 
               tipAmt = bAmt * 18.0 / 100; 
               break; 
            case 4 : 
               tipAmt = bAmt * 20.0 / 100; 
               break; 
            case 5 : 
               System.out.print("Please enter your tip %: ");
               int tPer = input.nextInt();
               tipAmt = bAmt * tPer / 100; 
               break;
            case 6 : 
               tipAmt = bAmt * 0.0 / 100; 
               break; 
            } 
         return tipAmt;
         }
         
   // receipt with order number(Use Calendar object) print method {}      
   public static void receipt(double bAmt, double tip, double point) { 
      double tipAmt = tip;
      double taxAmt = bAmt * 0.06; 
      double deductedAmt = point;
      double total = taxAmt + tipAmt + bAmt - point;

      System.out.printf("\nBill Total:\t\t\t%7.2f\n", bAmt);
      System.out.printf("Tax (6%%):\t\t\t%7.2f\n", taxAmt);
      System.out.printf("Tip Amount:\t\t\t%7.2f\n", tipAmt);
      System.out.printf("Used Point:\t\t\t-%6.2f", deductedAmt);
      System.out.println("\n---------------------------------------------------------");
      System.out.printf("Total to Pay:\t\t%7.2f", total);
   }
   
   // simplified receipt with order number(Use Calendar object) method (for those who do not want receipt) {}
   public static void simpleReceipt(double bAmt, double tip) { 
      Calendar now = Calendar.getInstance();
       
      double tipAmt = tip;
      double taxAmt = bAmt * 0.06; 
      double total = taxAmt + tipAmt + bAmt; 
      int day = now.get(Calendar.DAY_OF_MONTH), hour = now.get(Calendar.HOUR), sec = now.get(Calendar.SECOND);               
             
      System.out.println("\nThanks for your order!");
      System.out.printf("Order number:\t\t  %d%d%d\n", day, hour, sec);
      System.out.println("-------------------------");
      System.out.printf("Total to Pay:\t\t%7.2f\n", total);
   }
   
   // Method for searching members
   public static int searchMembers(RewardSystem[] memberList, int sizeMembers) {
      Scanner scan = new Scanner(System.in);
      
      System.out.printf("Enter last 4-digit of phone number if you are a member: ");
      int phNum = scan.nextInt();
      
      for(int i = 0; i < sizeMembers; i++) {
         if(memberList[i].getPhoneNum() == phNum) {
            return i;
         }
      }
      return -1;
   }      
 
}  // end BurgerShopKiosk class