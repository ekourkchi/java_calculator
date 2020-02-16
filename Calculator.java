import java.util.*;

/*****************************************************************/
/**
  @Class: Calculator
  @Author: Ehsan Kourkchi
  @Date: October 2013
  @Goal: Calculating the infix arithmatic expressions with parentheses
*/


public class Calculator {


Stack<Integer> operatorStack = new Stack();
Stack<Double> operandStack = new Stack();
String expression;

  /*****************************************************************/
  /*****************************************************************/
  /*****************************************************************/

  /** Cunstructor ....
      @expression: input espression that would be calculated
      This method gets the experssion and manupulate it in order to be ready fo calculations
      It removes estra spaces in expression and make sure to be one space between operators and operands
      this method also takes care of all "plus" and "minus" signs. It takes care of '+' and '-' using some extra prantheses 
      this way it takes care of negative and positive numbers, even if user enter several of them in a sequence
  */
  public Calculator(String expression) {
      
      String s = "", s0="";
      char temp, last='\0', lastop='\0';
      int i;
      boolean operatorIndicator = false;
      
      
      /** removing extra spaces*/
      for ( i=0; i< expression.length(); i++ ) { 
        temp = expression.charAt(i);
            if (last != ' ' || temp != ' ') {
               s += temp; 
               last = temp;
            }
      } /** end of for */
      
    
      if (s != "") {
      
      
      
      
      /** Seprating the operators with single spaces
          In this program, '(' and ')' are considered as operators, however they don't do any operation
          All plus and minus signs are wrapped up with parentheses and actual operators
      */
      for ( i=0; i< s.length(); i++ ) { 
        temp = s.charAt(i);
        
	    if (temp == '/' || temp == '*' || temp == '(' || temp == ')') {  
		s0 = s0 + " " + temp + " ";
		operatorIndicator = true;
		lastop = temp;
	    } 
	    else if (temp == '-' && operatorIndicator == true && lastop!=')' && lastop!='/')  
		s0 = s0 + " ( -1. ) * ";
	    else if (temp == '-' && operatorIndicator == true && lastop!=')' && lastop=='/')  
		s0 = s0 + " ( -1. ) / ";
	    else if (temp == '-' && operatorIndicator == true && lastop==')')  
		s0 = s0 + " + ( -1. ) * ";
	    else if (temp == '-' && operatorIndicator == false && lastop!='\0')  {
		s0 = s0 + " + ( -1. ) * ";
		operatorIndicator = true;
	    }
	    else if (temp == '-' && operatorIndicator == false && lastop=='\0')  {
		s0 = s0 + " ( -1. ) * ";
		operatorIndicator = true;
	    }

	    else if (temp == '+' && operatorIndicator == true && lastop!=')' && lastop!='/')  
		s0 = s0 + " ( +1. ) * ";
	    else if (temp == '+' && operatorIndicator == true && lastop!=')' && lastop=='/')  
		s0 = s0 + " ( +1. ) / ";
	    else if (temp == '+' && operatorIndicator == true && lastop==')')  
		s0 = s0 + " + ( +1. ) * ";
	    else if (temp == '+' && operatorIndicator == false && lastop!='\0')  {
		s0 = s0 + " + ( +1. ) * ";
		operatorIndicator = true;
	    }
	    else if (temp == '+' && operatorIndicator == false && lastop=='\0')  {
		s0 = s0 + " ( +1. ) * ";
		operatorIndicator = true;
	    }
		  
	    else {
	      s0 = s0 + temp;
		  
		  if (temp != ' ') { 
		      operatorIndicator = false; 
		      lastop=' ';
		  }
	    } /** end of big if */
      } /** end for */

      
      last='\0';
      s = "";
      
      
      /** again taking care of spaces 
      */
      
      if (s0.charAt(0)==' ')  
         i=1; 
      else  i=0;
      for ( ; i< s0.length(); i++ ) { 
          temp = s0.charAt(i);
          if (last != ' ' || temp != ' ') {
             s += temp; 
             last = temp;
          }
      }
      
     
      s0 = "";
      if (s.charAt(0)==' ')  
         i=1; 
      else 
         i=0;
      for (  ; i< s.length(); i++ ) 
         if (i != s.length()-1 || s.charAt(i) != ' ') 
             s0 += s.charAt(i);
      
      } /** end of if statement for s!="" */
      
      
   this.expression = s0;  // setting the main expression. It is now ready for calculation process
  }

  /*****************************************************************/
  /*****************************************************************/
  /*****************************************************************/

  /** Calculator method .. THIS is the HEART of the calculator
      
      if the experssion is calulatable returns the calculated number
      otherwise, it returns '0'
  */
  public double calculate() {
  
  int opt;
  int i = 0;
  
     if (isCalculable()) {
     
          String [] tokens = expression.split("\\s");
          
          while (i< tokens.length) {
              
                if (isOperand(tokens[i])) {
                
			if (operatorStack.empty() || operatorStack.peek() < 3) { // not '*' or '/' operators
			  operandStack.push(Double.parseDouble(tokens[i++])); 
			} else if (operatorStack.peek() == 3) { // '*' operator
			  operandStack.push(operandStack.pop()*Double.parseDouble(tokens[i++]));
			  operatorStack.pop(); 
			} else {   // '/' operator
			  operandStack.push(operandStack.pop()/Double.parseDouble(tokens[i++]));
			  operatorStack.pop(); 
			}
                }
                else if ((opt = whichOperator(tokens[i++])) > -1) {
			operatorStack.push(opt);
                } 
                else {
			

			// Taking care of everything inside the parentheses
			while ((opt = operatorStack.peek()) != 0) {
			      operandStack.push(evalOP(opt));
			      operatorStack.pop();
		        }
		      
		      operatorStack.pop();  // popping the '('
		      
		      if (!operatorStack.empty()) {
		      
		         opt = operatorStack.peek();
		         
		         if (opt == 3 || opt == 4) {   // extra calculation for '*' or '/' standing befor the opening parenthesis
			    operandStack.push(evalOP(opt));
			    operatorStack.pop(); 
		         }
		      } /** end of else part */
                }  /** end of big if */ 

          
          } /** end while */
          
          
          while (!operatorStack.empty()) {
                opt = operatorStack.pop();
                
                operandStack.push(evalOP(opt));
                
          }
     
     
          return operandStack.pop();
     }
  
  return 0;
  }
 
  
  /*****************************************************************/
  
  /** Ordering the operators in terms of their precedences
      this method returns an integer number for any given operator
  */
  private int whichOperator(String aString) {
  

   
        switch (aString.charAt(0)) {
        
           case '+' : return 1;
           case '*' : return 3;
           case '/' : return 4;
           case '(' : return 0;
           case ')' : return -1;
        }  
     
  
  return 100;
  }
  
  /*****************************************************************/
  /**  
    Doing the calculation using two operand and one operator
    It takes care of +/* operators, since minus signs has been turned into "(-1) *"
  */
  private double evalOP(int op) {
  
    double result = 0;
    double right = operandStack.pop();
    double left = operandStack.pop();
  
  
     
        switch (op) {
        
           case 1 : result = left + right;
                    break;
           case 3 : result = left * right;
                    break;
           case 4 : result = left / right;
                    break;
        }  
  
  
  return result;
  
  }
  

  /*****************************************************************/
  /** Checking aString if it is an operator
    @true: if it is an operator
    @false: if it is not an operator
  */
  
    private boolean isOperator(String aString) {
     
     if (aString.length() != 1) 
        return false; 
     else {
     
        switch (aString.charAt(0)) {
        
           case '+' : return true;
           case '-' : return true;
           case '/' : return true;
           case '*' : return true;
     
        }  
     }
  
  return false;
  }
  
  
  /*****************************************************************/
  /** Checking aString if it is an operand
    @true: if it is an operand
    @false: if it is not an operand
  */
  
   private boolean isOperand(String aString) {
     
     try {
        double d = Double.parseDouble(aString);
     }
     catch (NumberFormatException ex) {
        return false;        
     }
  
  return true;
  }
  
 
  /*****************************************************************/
  /**  Turns aString into a Double format
     @ returns 0 in the case on the exception
  */

  private static double toDouble(String aString) {
     
     double d;
     try {
        d = Double.parseDouble(aString);
     }
     catch (NumberFormatException ex) {
        return 0;        
     }
  
  return d;
  }
  
  
  /*****************************************************************/
  /**
     Check whether the input expression is calulatable.
     
     This is important, because if the inout expression is not calculable, the calculator method will be in trouble,
     Therefore, if that method, first we use this method to confirm the calculablity of the expression
  */
  
  
  public boolean isCalculable() {
  
  int i,j, last = 0, parenthesis = 0;
  String [] tokens = expression.split("\\s");
     
     j=0;
     for (i=0; i< tokens.length;  i++) {
          
          if (!(tokens[i].equals("(") || tokens[i].equals(")"))) { // does not count parnathese 

		if (j % 2 == 0) {

		    if (!isOperand(tokens[i])) return false;
		    last = 1; // is operand
		}
		else {
		    
		    if (!isOperator(tokens[i])) return false;
		    last = 2; // is operator
		}
		
		j++; 
		
          } else if (tokens[i].equals(")") && last != 4 && last !=2 ) { 
                last = 3; parenthesis--; 
          } else if (tokens[i].equals("(") && last != 1 && last !=3 ) { 
                last = 4; parenthesis++; 
          } else return false;
     }
     
     // checking for the number of openning and closing parentheses
     // checking for the last element not to be an operator i.e. j must be even
     if (j % 2 == 0 || parenthesis != 0 ) return false;
     
     
  return true;  // if everything goes well
  }


/*****************************************************************/
}  /** End of the Class */
/*****************************************************************/
