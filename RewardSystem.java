/* MIS350 Project
   Group 2
   12/7/2022
   Suseong Park, DongJoo Chong
*/

public class RewardSystem {
   private int phoneNum;
   private double earnedPoint;
   private String memberName;   
   
   public RewardSystem() {
   }
   
   public RewardSystem(int phoneNum, double earnedPoint, String memberName) {
      this.phoneNum = phoneNum;
      this.earnedPoint = earnedPoint;
      this.memberName = memberName;
   }
   
   public int getPhoneNum() {
      return phoneNum;
   }
   
   public double earnedPoint() {
      return earnedPoint;
   }
   
   public void usePoint(double usePoint) {
      if(earnedPoint >= usePoint) {         
         earnedPoint -= usePoint;
         System.out.printf("You have used the point! Your current point is %.2f", earnedPoint);
      } else if(usePoint > earnedPoint) {
         System.out.println("You do not have enough point");
      }
   }
   
   public void earnPoint(double earnPoint) {
         earnedPoint += earnPoint;
         System.out.printf("You have earned the point! Your current point is %.2f", earnedPoint);
      }      
   
   
   public void checkPoint() {
      System.out.printf("Hello, %s! Your current point is: %.2f", memberName, earnedPoint);   
   }
   
}