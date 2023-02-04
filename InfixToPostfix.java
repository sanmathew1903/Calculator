import java.math.*;
import java.util.Scanner;
import java.util.Stack;
class stacks {

    char stack1[] = new char[20];
    int t = 0;
    
    
    void push(char ch) {
        t++;
        stack1[t] = ch;
    }
    
    char pop() {
        char ch;
        ch = stack1[t];
        t--;
        return ch;
        
    }
    
    int pre(char ch) {
        switch (ch) {
            case '-':
            return 1;
            case '+':
            return 1;
            case '*':
            return 3;
            case '/':
            return 3;
            case '%':
            return 2;
            case '^':
            return 4;
            case '0':
            return 0;
        }
        return 0;
    }
    
    int postfix(String s1) 
    {
        stack1[0] = '0';
        StringBuffer str = new StringBuffer();
        char ch;
        int i;
        for (i = 0; i < s1.length(); i++) 
        {
            ch = s1.charAt(i);
            if (ch == '/' || ch == '*' || ch == '+' || ch == '-' || ch == '^' || ch == '%') {
                if (t == 0) 
                {
                    push(ch);
                } 
                else 
                {
                    if (pre(ch) > pre(stack1[t]) || ch == '(') {
                        push(ch);
                    } else {
                        while (pre(ch) <= pre(stack1[t])) {
                            char x=pop();
                            str.append(x);
                        }
                        push(ch);
                    }
                }

            } 
            else {
                str.append(ch);
            }
           
        }

        while (t >0) 
        {
            char x=pop();

            str.append(x);
        }
        
    
        //create a stack
        Stack<Integer> stack=new Stack<>();
         
        // Scan all characters one by one
        for( i=0;i<str.length();i++)
        {
            char c=str.charAt(i);
             
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(Character.isDigit(c))
            stack.push(c - '0');
             
            //  If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else
            {
                int val1 = stack.pop();
                int val2 = stack.pop();
                 
                switch(c)
                {
                    case '+':
                    stack.push(val2+val1);
                    break;
                     
                    case '-':
                    stack.push(val2- val1);
                    break;
                     
                    case '/':
                    stack.push(val2/val1);
                    break;
                     
                    case '*':
                    stack.push(val2*val1);
                    break;
                    case '^':
                    stack.push((int )(Math.pow(val2,val1)));
                    break;
                    case '%':
                    stack.push(val2%val1);
                    break;
              }
            }
        }
        return (stack.pop());
    }
     
    
    
}

public class InfixToPostfix extends stacks{
    public static void main(String[] args) throws Exception {
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter input s1ing");
        s = sc.nextLine();
        sc.close();
        stacks b = new stacks();
        
        System.out.println("Input String is " + s);
        System.out.println("Output String is");
        b.postfix(s);
        
        
        
    }
}