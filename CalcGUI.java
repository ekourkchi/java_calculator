import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class CalcGUI extends JFrame implements  ActionListener, KeyListener, MouseListener {

JPanel infopanel;
JPanel inputpanel;
JPanel resultpanel;
JPanel memorypanel;
JButton exitbutton;
JLabel emptylabel;
JLabel emptylabel1;
JLabel emptylabel2;
JLabel infolabel;
JLabel answerlabel;
JLabel memorylabel;
JTextField textField; 
String answer;
String memory_calc;
String lastSt;
String undoSt;
boolean first_time;

   
   JButton lastExpression = new JButton("Last");
   JButton undo = new JButton("Undo/Edit");
   
   JButton percent = new JButton("%");
   
   JButton memory = new JButton("M+");
   JButton memory_minus = new JButton("M-");
   JButton memory_clean = new JButton("MC");
   JButton memory_retrieve = new JButton("MR");
    
   JButton ac = new JButton("AC");
   JButton openParant = new JButton("(");
   JButton closeParant = new JButton(")");
   JButton division = new JButton("/");

   JButton key7 = new JButton("7");
   JButton key8 = new JButton("8");
   JButton key9 = new JButton("9");
   JButton multiply = new JButton("\u00D7");

   JButton key4 = new JButton("4");
   JButton key5 = new JButton("5");
   JButton key6 = new JButton("6");
   JButton minus = new JButton("-");
   
   JButton key1 = new JButton("1");
   JButton key2 = new JButton("2");
   JButton key3 = new JButton("3");
   JButton plus = new JButton("+");
   
   JButton key0 = new JButton("0");
   JButton dot = new JButton(".");
   JButton ans = new JButton("Ans");
   JButton equal = new JButton("=");
   
   JButton del = new JButton("<-");
   

boolean textField_remove = false;
   
   /** constructor */
   
   public CalcGUI() {
   

   
   answer = "";
   memory_calc = "";
   lastSt = "";
   undoSt = "";
   first_time = true;
   
   exitbutton = new JButton("Exit");
   exitbutton.addActionListener(this);
  
   
   emptylabel = new JLabel();
   emptylabel1 = new JLabel();
   emptylabel2 = new JLabel();

   memorypanel = new JPanel();
   memorylabel  = new JLabel();
   
   answerlabel = new JLabel();
   
   infolabel = new JLabel();
   infolabel.setText("Copyright (C) 2013 - Ehsan Kourkchi");
   infolabel.setForeground(Color.blue);
   
   
   
   textField = new JTextField(35);
   textField.setText("enter an expression");
   textField.setForeground(Color.gray);
   textField_remove = true;
   textField.addActionListener(this);
   textField.addKeyListener(this);
   textField.addMouseListener(this);
   
   
   
   
   infopanel = new JPanel();
   infopanel.setBackground(Color.white); 
   infopanel.add(infolabel);
   //infopanel.add(exitbutton);

   
   inputpanel = new JPanel();
   inputpanel.setBackground(Color.white);   
   inputpanel.add(textField);


  
   resultpanel = new JPanel();
   resultpanel.add(answerlabel);
   
   
   
   
   JPanel buttons0 = new JPanel(new GridLayout(1, 4, 2, 2));
   JPanel buttons01 = new JPanel(new GridLayout(1, 4, 2, 2));
   JPanel buttons1 = new JPanel(new GridLayout(1, 4, 2, 2));
   JPanel buttons2 = new JPanel(new GridLayout(1, 4, 2, 2));
   JPanel buttons3 = new JPanel(new GridLayout(1, 4, 2, 2));
   JPanel buttons4 = new JPanel(new GridLayout(1, 4, 2, 2));
   JPanel buttons5 = new JPanel(new GridLayout(1, 4, 2, 2));
   
   del.addActionListener(this);
   del.setBackground(Color.ORANGE);
   

   
   buttons01.add(lastExpression); lastExpression.addActionListener(this);
   buttons01.add(undo); undo.addActionListener(this);
   buttons01.add(del);
   buttons01.add(ac); ac.addActionListener(this);
   ac.setBackground(Color.ORANGE);  
   ac.setForeground(Color.RED);  
 

   buttons0.add(memory);  memory.addActionListener(this);
   buttons0.add(memory_minus);  memory_minus.addActionListener(this);
   buttons0.add(memory_retrieve); memory_retrieve.addActionListener(this);
   buttons0.add(memory_clean); memory_clean.addActionListener(this);

   
   
   
   
   buttons1.add(openParant);  openParant.addActionListener(this); 
   buttons1.add(closeParant);closeParant.addActionListener(this); 
   buttons1.add(percent); percent.addActionListener(this); 
   buttons1.add(division); division.addActionListener(this);

   buttons2.add(key7); key7.addActionListener(this);
   buttons2.add(key8);  key8.addActionListener(this);  
   buttons2.add(key9);   key9.addActionListener(this); 
   buttons2.add(multiply); multiply.addActionListener(this);   

   buttons3.add(key4); key4.addActionListener(this);
   buttons3.add(key5); key5.addActionListener(this);  
   buttons3.add(key6);    key6.addActionListener(this);
   buttons3.add(minus); minus.addActionListener(this);
   
   buttons4.add(key1); key1.addActionListener(this);
   buttons4.add(key2);    key2.addActionListener(this);
   buttons4.add(key3);   key3.addActionListener(this); 
   buttons4.add(plus);  plus.addActionListener(this);
   
   buttons5.add(key0); key0.addActionListener(this);
   buttons5.add(dot);  dot.addActionListener(this);
   buttons5.add(ans);  ans.addActionListener(this);
   buttons5.add(equal); equal.addActionListener(this); 
   //equal.setBackground(Color.GREEN.darker());
    
 
   
   JPanel content = new JPanel(new GridLayout(11, 0));
   content.add(infopanel);
   content.add(inputpanel);
   content.add(resultpanel);
   
   
   memorypanel.add(memorylabel);
   content.add(memorypanel);
   
   content.add(buttons01);
   content.add(buttons0);
   content.add(buttons1);
   content.add(buttons2);
   content.add(buttons3);
   content.add(buttons4);
   content.add(buttons5);
   
   ImageIcon img = new ImageIcon("logo.jpg");
   
   this.setIconImage(img.getImage());
   this.getContentPane().add(content);
   
   
   ans.setEnabled(false);
   memory_clean.setEnabled(false);
   memory_retrieve.setEnabled(false);
   memory.setEnabled(false);
   memory_minus.setEnabled(false);
   ac.setEnabled(false);
   undo.setEnabled(false);
   lastExpression.setEnabled(false);
   }
   
   
   

   
   
   
   private void setCenter() {
   
    // Get the size of the screen
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 
    // Determine the new location of the window
    int w = this.getSize().width;
    int h = this.getSize().height;
    int x = (dim.width-w)*1/3;
    int y = (dim.height-h)*1/3;
 
    // Move the window
    this.setLocation(x, y);
   
   }
      
   
   
   public void removeText() {
   
      if (textField_remove == true) {
        textField.setText("");
        textField_remove = false;
        ac.setEnabled(false);
      }
   
   textField.setForeground(Color.black);
   textField.requestFocus(); 
   }
   
   
   public void addText(String st, boolean singleCursorMove){
       if (textField_remove == true)
          removeText();
          
       int i, position = textField.getCaretPosition();
       String outString = "", s = textField.getText();
       
       for ( i=0; i< position; i++ ) outString += s.charAt(i);
       outString += st;
       for ( i=position; i< s.length(); i++ ) outString += s.charAt(i);
       textField.setText(outString);
       textField.setForeground(Color.black);
       if (singleCursorMove == true) textField.setCaretPosition(position+1);
       else textField.setCaretPosition(position+st.length());
       
       textField.requestFocus(); 
       ac.setEnabled(true);
     }
   
   
   public void mouseClicked(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   
   
   public void mousePressed(MouseEvent e) {

   removeText();
   
   
     if (e.getClickCount() == 2 && !textField.getText().equals("")) {
       if (!textField.getText().equals(lastSt)) 
       {
       undoSt = textField.getText();
       undo.setEnabled(true);
       }
       textField.setText("");
       textField_remove = false;
       ac.setEnabled(false);
       
       
       textField.requestFocus();  
   
   }}

   
   
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
   
   public void keyPressed(KeyEvent e) {
   
     int key = e.getKeyCode();
     textField.setForeground(Color.black);
     
     if (first_time == true || textField_remove==true) textField.setText("");
     first_time = false;
     textField_remove=false;
     ac.setEnabled(true);
     
     if (textField.getText() == "") ac.setEnabled(false);
     
     
     if (key == KeyEvent.VK_ENTER) {
          calculate();
     }
   
   }
   
   
   
   
   
   private void calculate() {
   
      String expression = (String) textField.getText();

      Calculator myCalc = new Calculator(expression);
      
      if (!myCalc.isCalculable()) 
         {
         
         Toolkit.getDefaultToolkit().beep();
         answerlabel.setText("Please enter a valid expression.");
         answerlabel.setForeground(Color.red);
         
         if(first_time != true) {
           textField_remove = false;
           textField.setForeground(Color.red);
           }
           
         
         first_time = false;
         }
      else {
         answer = Double.toString(myCalc.calculate());
         answerlabel.setText(answer);
         answerlabel.setForeground(Color.BLUE.darker());
         ans.setEnabled(true);
         memory.setEnabled(true);
         memory_minus.setEnabled(true);
         
         lastSt = expression;
         lastExpression.setEnabled(true);
       }
   textField.requestFocus(); 
   }
   
   
   public void actionPerformed(ActionEvent event) {
         String temp;

     if (event.getSource() == equal)  
       calculate();
     
     
     
     if (event.getSource() == exitbutton)       
      System.exit(0);
     
     
     if (event.getSource() == ac && !textField.getText().equals("")) {
       if (!textField.getText().equals(lastSt)) 
       {
       undoSt = textField.getText();
       undo.setEnabled(true);
       }
       textField.setText("");
       textField_remove = false;
       ac.setEnabled(false);
       
       
       textField.requestFocus();   
     }      

     
      if (event.getSource() == lastExpression && !textField.getText().equals(lastSt)) {
      
       temp="";
       if (!textField.getText().equals("")) temp = textField.getText();
       textField.setText(lastSt);
       if (!temp.equals("")) undoSt = temp;
       //undo.setEnabled(false);
       textField_remove = false;
       ac.setEnabled(true);
       if (!undoSt.equals("")) undo.setEnabled(true);
       textField.requestFocus();   
       //lastExpression.setEnabled(false);
     }       
     
     
     if (event.getSource() == undo) {
       
       temp="";
       if (!textField.getText().equals("")) temp = textField.getText();
       textField.setText(undoSt);
       if (!temp.equals("") && !temp.equals(lastSt)) undoSt = temp;
       //undo.setEnabled(false);
       textField_remove = false;
       ac.setEnabled(true);
       textField.requestFocus(); 
       undo.setEnabled(false);
       
     }     
     
 
 
 
     
     if (event.getSource() == openParant)  addText("(", true);
     if (event.getSource() == closeParant)  addText(")", true);
     if (event.getSource() == plus)  addText("+", true);
     if (event.getSource() == minus)  addText("-", true);
     if (event.getSource() == multiply)  addText("*", true);
     if (event.getSource() == division)  addText("/", true);
     if (event.getSource() == dot)  addText(".", true);
     
     if (event.getSource() == key0)  addText("0", true);
     if (event.getSource() == key1)  addText("1", true);
     if (event.getSource() == key2)  addText("2", true);
     if (event.getSource() == key3)  addText("3", true);
     if (event.getSource() == key4)  addText("4", true);
     if (event.getSource() == key5)  addText("5", true);
     if (event.getSource() == key6)  addText("6", true);
     if (event.getSource() == key7)  addText("7", true);
     if (event.getSource() == key8)  addText("8", true);
     if (event.getSource() == key9)  addText("9", true);
     if (event.getSource() == percent)  addText("*.01", false);

     if (event.getSource() == ans)  {
        addText(answer, false);
     textField.requestFocus();   
     }
     
     if ((event.getSource() == memory || event.getSource() == memory_minus) && answer != "")  {
        //memory_calc="";
        int i;
        double new_memory=0.;
        
        if (memory_calc != "" && event.getSource() == memory)        
           new_memory = Double.parseDouble(memory_calc) + Double.parseDouble(answer);
        else if (memory_calc != "" && event.getSource() == memory_minus)      
           new_memory = Double.parseDouble(memory_calc) - Double.parseDouble(answer);
        else if (memory_calc == "" && event.getSource() == memory)    
           new_memory = Double.parseDouble(answer);
        else if  (memory_calc == "" && event.getSource() == memory_minus)  
           new_memory = -1.*Double.parseDouble(answer);
        else;
        
        //for ( i=0; i< answer.length(); i++ ) memory_calc += answer.charAt(i);
        memory_calc= Double.toString(new_memory);
        //System.out.println(memory_calc);
     memory_clean.setEnabled(true);
     memory_retrieve.setEnabled(true);
     
     memorylabel.setText("Memory: " + memory_calc);
     answerlabel.setText(answer);
     answerlabel.setForeground(Color.BLUE.darker());
     textField.requestFocus();   
     }     
     
     
     if (event.getSource() == memory_clean)  {
        memory_calc="";
        memorylabel.setText("");
     memory_clean.setEnabled(false);
     memory_retrieve.setEnabled(false);
     textField.requestFocus();   
     }      
     
     
     if (event.getSource() == memory_retrieve)  {
        
        addText(memory_calc, false);
        //System.out.println(memory_calc);
     textField.requestFocus();   
     }      
     
     
     
     if (event.getSource() == del && textField.getCaretPosition() !=0)  {
        
        
       int i, position = textField.getCaretPosition();
       String outString = "", s = textField.getText();
       
       for ( i=0; i< position-1; i++ ) outString += s.charAt(i);
           //outString += st;
       for ( i = position; i< s.length(); i++ ) outString += s.charAt(i);
       textField.setText(outString);
       textField.setForeground(Color.black);
       textField.setCaretPosition(position-1);
       
     }
     
     textField.requestFocus();
     if (textField.getText().equals("")) ac.setEnabled(false);

     
     
     
  
       
  
  
  
  
  
  
  
  
  
  
  
  }
   

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   public static void main(String [] args) {
   
   CalcGUI myWindow = new CalcGUI();
   myWindow.setTitle("Ehsan Calculator");

   myWindow.setSize(700,200);
   
   myWindow.setCenter();
   myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   myWindow.pack();
   
   
   myWindow.setResizable(false);
   myWindow.setVisible(true);
   myWindow.toFront();
   }









}