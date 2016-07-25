//Java Packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.Insets;
//import java.util.ArrayList;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class BMICalcFrame extends JFrame
{
   private static final int ACTION_KEY_PAD_COLUMNS = 1;
   private static final int ACTION_KEY_PAD_ROWS = 4;
   private static final BMIcalculator calc = new BMIcalculator();
   private static final int DECIMAL_BUTTON_MAX_COUNT = 1;
   private static final int DECIMAL_BUTTON_MIN_COUNT = 0;
   private static final int DISPLAY_SCREEN_COLUMNS = 40;
   private static final int DISPLAY_SCREEN_ROWS = 6;
   private static final int ENTER_BUTTON_MAX_COUNT = 2;
   private static final int ENTER_BUTTON_MIN_COUNT = 0;
   private static final int FRAME_HEIGHT = 350;
   private static final int FRAME_WIDTH = 270;
   private static final int NUMERIC_KEY_PAD_COLUMNS = 3;
   private static final int NUMERIC_KEY_PAD_ROWS = 4;
   
   
   private static double height;
   private static double weight;
   private static int decimal_button_count;
   private static int enter_button_count;
   private static JButton decimal_button;
   private static JButton delete_button;
   private static JButton enter_button;
   private static JButton no_0_button;
   private static JButton no_00_button;
   private static JButton no_1_button;
   private static JButton no_2_button;
   private static JButton no_3_button;
   private static JButton no_4_button;
   private static JButton no_5_button;
   private static JButton no_6_button;
   private static JButton no_7_button;
   private static JButton no_8_button;
   private static JButton no_9_button;
   private static JButton redo_button;
   private static JButton undo_button;
   //private static ArrayList buttonList;
   private static JLabel display_text;
   private static JMenu settings_menu;
   private static JMenu mode_menu;
   private static JMenuBar BMI_calc_menu_bar;
   private static JMenuItem metric_mode_item;
   private static JMenuItem imperial_mode_item;
   private static JPanel action_keypad;
   //private static JPanel display_screen_panel;
   private static JPanel numeric_keypad;
   private static JScrollPane display_screen_scroll_pane;
   private static JTextArea display_screen;
   private static JTextArea hidden_text_area;
               
   public BMICalcFrame()
   {
      //setting the title and dimensions of this frame
      this.setTitle("A Simple BMI Calculator");
      this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      
      //setting the default mode of the BMIcalculator
      this.setDefaultMode();
      
      //Process of creating the interface...
      
      //create the BMICalc's menu bar
      this.createBMICalcMenuBar();
      
      //create the BMICalc's menu(s)
      JMenu menu1 = this.createMenu(settings_menu, "Settings");
      JMenu menu2 = this.createMenu(mode_menu, "Mode");
      
      //add menu1(settings_menu) to the menu bar
      BMI_calc_menu_bar.add(menu1);
      
      //add menu2(mode_menu) to menu1(settings_menu)
      menu1.add(menu2);
           
      //create the menu items for the BMICalc menu(s) created above
      this.createMenuItem(menu2, metric_mode_item, "Metric mode", new MetricModeItemListener());
      this.createMenuItem(menu2, imperial_mode_item, "Imperial Mode", new ImperialModeItemListener());
      
      //create the BMICalc's display screen and add it to the 
      //north of the frame using the BorderLayout class
      this.add(this.createDisplayScreenScrollPane(), BorderLayout.NORTH);
      
      //creating the hidden_text_area which keeps a copy of all user inputs
      this.createHiddenTextArea();
      
      //create the BMICalc's display text
      this.createDisplayText();
      
      //create the numeric keypad and add it to the centre
      //of the frame using the BorderLayout class
      this.add(this.createNumericKeypad(), BorderLayout.CENTER);
      
      //create the action keypad and add it to the east of the 
      //frame using the BorderLayout class
      this.add(this.createActionKeypad(), BorderLayout.EAST);
      
      //intialise all the buttons for the BMICalc and 
      //add them to their required panels using the 
      //GridLayout class i.e. numeric keypad and action keypad
      
      //Creating a button which would be used as a platform
      //for creating all the buttons of the BMI Calculator
      JButton custom_button = null; //assigning the button to null
      
      //initialising the numeric keypad buttons....
      no_1_button = this.createButton(custom_button, "1", new No1ButtonListener());
      no_2_button = this.createButton(custom_button, "2", new No2ButtonListener());
      no_3_button = this.createButton(custom_button, "3", new No3ButtonListener());
      no_4_button = this.createButton(custom_button, "4", new No4ButtonListener());
      no_5_button = this.createButton(custom_button, "5", new No5ButtonListener());
      no_6_button = this.createButton(custom_button, "6", new No6ButtonListener());
      no_7_button = this.createButton(custom_button, "7", new No7ButtonListener());
      no_8_button = this.createButton(custom_button, "8", new No8ButtonListener());
      no_9_button = this.createButton(custom_button, "9", new No9ButtonListener());
      no_0_button = this.createButton(custom_button, "0", new No0ButtonListener());
      no_00_button = this.createButton(custom_button, "00", new No00ButtonListener());
      decimal_button = this.createButton(custom_button, ".", new DecimalButtonListener());
      
      //initialising the action_keypad buttons...
      enter_button = this.createButton(custom_button, "  enter  ", new EnterButtonListener());
      undo_button = this.createButton(custom_button, "  un-do  ", new UndoButtonListener());
      redo_button = this.createButton(custom_button, "  re-do  ", new RedoButtonListener());
      delete_button = this.createButton(custom_button, "  C  ", new DeleteButtonListener());
      
      //adding the intialised numeric buttons to the numeric
      //keypad
      numeric_keypad.add(no_1_button);
      numeric_keypad.add(no_2_button);
      numeric_keypad.add(no_3_button);
      numeric_keypad.add(no_4_button);
      numeric_keypad.add(no_5_button);
      numeric_keypad.add(no_6_button);
      numeric_keypad.add(no_7_button);
      numeric_keypad.add(no_8_button);
      numeric_keypad.add(no_9_button);
      numeric_keypad.add(no_0_button);
      numeric_keypad.add(no_00_button);
      numeric_keypad.add(decimal_button);

      //adding the initialised action buttons to the action
      //keypad
      action_keypad.add(delete_button);
      action_keypad.add(enter_button);
      action_keypad.add(undo_button);
      action_keypad.add(redo_button); 
      
            
      //creating and instance of the BMIcalculator class
      //which contains the logic of the BMICalculator
      //application
      //calc = new BMIcalculator();
      
      //setting the enter_button_count to zero
      enter_button_count = ENTER_BUTTON_MIN_COUNT;  
      
      //setting the decimal_button_count to zero
      decimal_button_count = DECIMAL_BUTTON_MIN_COUNT;
      
      //intialising both the weight and height attributes to
      //zero
      weight = 0.0;
      height = 0.0;
   }
   
   public JButton createButton(JButton aButton, String button_name, ActionListener listener)
   {
      aButton = new JButton(button_name);
      aButton.setFont(new Font("Dialog", Font.PLAIN, 12));
      aButton.addActionListener(listener);
      
      return aButton;
   }
   
   /*public JPanel createDisplayScreenPanel()
   {
      display_screen_panel = new JPanel();
      //display_screen.setLayout(new BorderLayout());
      display_screen_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      display_screen_panel.setBackground(Color.GRAY);
      display_screen_panel.add(this.createDisplayScreen());
      
      
      return display_screen_panel;
   }*/
   
   public JScrollPane createDisplayScreenScrollPane()
   {
      display_screen_scroll_pane = new JScrollPane(this.createDisplayScreen());
      
      return display_screen_scroll_pane;
   }
   
   public JTextArea createDisplayScreen()
   {
      display_screen = new JTextArea(DISPLAY_SCREEN_ROWS, DISPLAY_SCREEN_COLUMNS);
      display_screen.setEditable(false);
      display_screen.setBorder(new LineBorder(Color.BLACK));
      display_screen.setBackground(Color.LIGHT_GRAY.brighter());      
      //display_screen.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //create an empty border between
                                                                               //the border of the text area and text
                                                                               //for better readability
      display_screen.setBorder(new BevelBorder(BevelBorder.LOWERED));
      //display_screen.setMargin(new Insets(20, 20, 20, 20));
      
      return display_screen;
   }
   
   public void createHiddenTextArea()
   {
      hidden_text_area = new JTextArea(DISPLAY_SCREEN_ROWS, DISPLAY_SCREEN_COLUMNS);
      hidden_text_area.setEditable(false);
      
   }
   
   public JPanel createNumericKeypad()
   {
      numeric_keypad = new JPanel();
      
      numeric_keypad.setBorder(new TitledBorder(new EtchedBorder(), "Numeric Keypad"));
      numeric_keypad.setLayout(new GridLayout(NUMERIC_KEY_PAD_ROWS, NUMERIC_KEY_PAD_COLUMNS));
      numeric_keypad.setBackground(Color.GRAY);
      
      return numeric_keypad;
   }
   
   public JPanel createActionKeypad()
   {
      action_keypad = new JPanel();
      
      action_keypad.setBorder(new TitledBorder(new EtchedBorder(), "Action Keypad"));
      action_keypad.setLayout(new GridLayout(ACTION_KEY_PAD_ROWS, ACTION_KEY_PAD_COLUMNS));
      action_keypad.setBackground(Color.GRAY);
      
      return action_keypad;
   }
   
   public void createBMICalcMenuBar()
   {
      BMI_calc_menu_bar = new JMenuBar();
      
      BMI_calc_menu_bar.setBackground(Color.DARK_GRAY);
      BMI_calc_menu_bar.setForeground(Color.BLUE);
      
      this.setJMenuBar(BMI_calc_menu_bar);
   }
   
   public JMenu createMenu(JMenu aMenu, String menu_name)
   {
      aMenu = new JMenu(menu_name);
      aMenu.setBackground(Color.LIGHT_GRAY);
      aMenu.setForeground(Color.BLUE);
      aMenu.setBorder(new LineBorder(Color.BLACK));
            
      return aMenu;
   }
   
   public void createMenuItem(JMenu aMenu, JMenuItem anItem, String item_name, ActionListener listener)
   {
      anItem = new JMenuItem(item_name);
      anItem.setBackground(Color.LIGHT_GRAY);
      anItem.setForeground(Color.BLUE);
      anItem.setBorder(new LineBorder(Color.BLACK));
      anItem.addActionListener(listener);
      
      aMenu.add(anItem);
   }
   
   public void createDisplayText()
   {
      display_text = new JLabel("");
      display_text.setForeground(Color.RED);
      
      if(calc.isMetric() == true)
      {
         this.metricModePrompt1();
      }
      
      else
      {
         this.imperialModePrompt1();
      }
 
   }
   
   /*private void addToButtonList(JButton aButton) throws NullPointerException
   {
      if(aButton == null)
      {
         throw new NullPointerException();
      }
      
      else
      {
         buttonList.add(aButton);
      }
   }*/
     
   class No1ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_1_button.getText());
         hidden_text_area.append(no_1_button.getText());
      }  
   }
   
   class No2ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_2_button.getText());
         hidden_text_area.append(no_2_button.getText());
      }  
   }
   
   class No3ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_3_button.getText());
         hidden_text_area.append(no_3_button.getText());
      }  
   }
   
   class No4ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_4_button.getText());
         hidden_text_area.append(no_4_button.getText());
      }  
   }
   
   class No5ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_5_button.getText());
         hidden_text_area.append(no_5_button.getText());
      }  
   }
   
   class No6ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_6_button.getText());
         hidden_text_area.append(no_6_button.getText());
      }  
   }
   
   class No7ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_7_button.getText());
         hidden_text_area.append(no_7_button.getText());
      }  
   }
   
   class No8ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_8_button.getText());
         hidden_text_area.append(no_8_button.getText());
      }  
   }
   
   class No9ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_9_button.getText());
         hidden_text_area.append(no_9_button.getText());
      }  
   }
   
   class No0ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_0_button.getText());
         hidden_text_area.append(no_0_button.getText());
      }  
   }
   
   class No00ButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         display_screen.append(no_00_button.getText());
         hidden_text_area.append(no_00_button.getText());
      }  
   }
   
   class DecimalButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         if(decimal_button_count >= DECIMAL_BUTTON_MAX_COUNT)
         {
            //do nothing
         }
         
         else
         {
            display_screen.append(decimal_button.getText());
            hidden_text_area.append(decimal_button.getText());
            decimal_button_count++;
         }
      }  
   }
   
   class EnterButtonListener implements ActionListener
   {      
      public void actionPerformed(ActionEvent event)
      {
         if(hidden_text_area.getText().equals(""))
         {
            //do notthing
         }
         
         else
         {
            if((enter_button_count == ENTER_BUTTON_MIN_COUNT) && (calc.isMetric() == true))
            {
               display_screen.setText(null);
               weight = Double.parseDouble(hidden_text_area.getText());
               hidden_text_area.setText(null);
               enter_button_count++;
               metricModePrompt2();
               decimal_button_count = DECIMAL_BUTTON_MIN_COUNT;
            }
         
            else if(((enter_button_count > ENTER_BUTTON_MIN_COUNT) && (enter_button_count < ENTER_BUTTON_MAX_COUNT)) && (calc.isMetric() == true))
            {
               display_screen.setText(null);
               height = Double.parseDouble(hidden_text_area.getText());
               hidden_text_area.setText(null);
               enter_button_count++;
               calc.setWtHt(weight, height);
               display_screen.append("Absolute BMI: " + calc.getBMI() + "\n\n");
               display_screen.append("Ideal weight: " + calc.getIdealWt() + "\n\n");
               display_screen.append("BMI Verdict: " + calc.getRange());            
            }
         
            else if((enter_button_count == ENTER_BUTTON_MIN_COUNT) && (calc.isMetric() == false))
            {
               display_screen.setText(null);
               weight = Double.parseDouble(hidden_text_area.getText());
               hidden_text_area.setText(null);
               enter_button_count++;
               imperialModePrompt2();
               decimal_button_count = DECIMAL_BUTTON_MIN_COUNT;
            }
         
            else if(((enter_button_count > ENTER_BUTTON_MIN_COUNT) && (enter_button_count < ENTER_BUTTON_MAX_COUNT)) && (calc.isMetric() == false))
            {
               display_screen.setText(null);
               height = Double.parseDouble(hidden_text_area.getText());
               hidden_text_area.setText(null);
               enter_button_count++;
               calc.setWtHt(weight, height);
               display_screen.append("Absolute BMI: " + calc.getBMI() + "\n\n");
               display_screen.append("Ideal weight: " + calc.getIdealWt() + "\n\n");
               display_screen.append("BMI Verdict: " + calc.getRange());
            }
          }

            
         
      }  
   }
   
   class UndoButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         if((calc.isMetric() == true) && ((enter_button_count > ENTER_BUTTON_MIN_COUNT) && (enter_button_count < ENTER_BUTTON_MAX_COUNT)))
         {
            stepBackOnce();
            metricModePrompt1();
         }
         
         else if((calc.isMetric() == true) && (enter_button_count == ENTER_BUTTON_MAX_COUNT)) 
         {
            stepBackOnce();
            metricModePrompt2();
         }
         
         else if((calc.isMetric() == false) && ((enter_button_count > ENTER_BUTTON_MIN_COUNT) && (enter_button_count < ENTER_BUTTON_MAX_COUNT)))
         {
            stepBackOnce();
            imperialModePrompt1();
         }
         
         else if((calc.isMetric() == false) && (enter_button_count == ENTER_BUTTON_MAX_COUNT)) 
         {
            stepBackOnce();
            imperialModePrompt2();
         }


      }  
      
   }
   
   class RedoButtonListener implements ActionListener
   {        
      public void actionPerformed(ActionEvent event)
      {
         startAgain();
         if(calc.isMetric() == true)
         {
            metricModePrompt1();
         }
         
         else
         {
            imperialModePrompt1();
         }
      }  
   } 
   
   class DeleteButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         if(hidden_text_area.getText().equals(""))
         {
            //do nothing
         }
         
         else
         {
            String container1 = display_screen.getText().substring(0, (display_screen.getText().length() -1));
            display_screen.setText(container1);
         
            String container2 = hidden_text_area.getText().substring(0, (hidden_text_area.getText().length() -1));
            hidden_text_area.setText(container2);
            decimal_button_count = DECIMAL_BUTTON_MIN_COUNT;
         }
      }
   }
   
   class MetricModeItemListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         setDefaultMode();
         startAgain();
         metricModePrompt1();
         
      }
   }  
   
    class ImperialModeItemListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         calc.setUK();
         startAgain();
         imperialModePrompt1();
      }
   }
   
   public void setDefaultMode()
   {
      calc.setMetric();
   }  
     
   public void metricModePrompt1()
   {
      display_text.setText("Please enter a weight in kilograms...\n\n");
      display_screen.append(display_text.getText());
   }
   
   public void metricModePrompt2()
   {
      display_text.setText("Please enter a height in metres...\n\n");
      display_screen.append(display_text.getText());
   }
   
   public void imperialModePrompt1()
   {
      display_text.setText("Please enter a weight in pounds...\n\n");
      display_screen.append(display_text.getText());
   }
   
   public void imperialModePrompt2()
   {
      display_text.setText("Please enter a height in inches...\n\n");
      display_screen.append(display_text.getText());
   }
   
   
   public void startAgain()
   {
      enter_button_count = ENTER_BUTTON_MIN_COUNT;
      decimal_button_count = DECIMAL_BUTTON_MIN_COUNT;
      display_screen.setText(null);
      hidden_text_area.setText(null);
      weight = 0.0;
      height = 0.0; 
   }
   
   public void stepBackOnce()
   {
      enter_button_count--;
      display_screen.setText(null);
      hidden_text_area.setText(null);      
   }

}

