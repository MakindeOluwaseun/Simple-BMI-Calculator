/*
* A Class that displays the BMI Calculator
*/

//Java Package
import javax.swing.JFrame;

//The BMI Calculator's frame
public class BMIFrameViewer
{
   public static void main(String[] args)
   {
      JFrame aFrame = new BMICalcFrame();
      aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      aFrame.setVisible(true);
      
   }
}
