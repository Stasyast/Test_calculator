
import java.util.Stack;


public class Calculator {

    double decide(String nbr){
        String prepared = preparingNbr(nbr);
        String nsw = toNbr(prepared);
        return toNsw(nsw);
    }
    private String preparingNbr (String nbr){
        String  preparedNbr = new String();
        for (int sbl = 0; sbl < nbr.length(); sbl ++){
            char symbol = nbr.charAt(sbl);
            if (symbol == '-'){
                if (sbl == 0)
                    preparedNbr += '0';
                else if (nbr.charAt(sbl-1) == '(')
                    preparedNbr += '0';
                else if (nbr.charAt(sbl-1) == '+')
                    preparedNbr += '0';

                            }
            preparedNbr+=symbol;

        }
        return  preparedNbr;
    }
    private String toNbr(String Nbr) {

        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority;
        for(int i = 0; i < Nbr.length();i++){
            priority = getP(Nbr.charAt(i));

            if(priority == 0)
                current+=Nbr.charAt(i);

            if(priority == 1)
                stack.push(Nbr.charAt(i));

            if(priority >1) {
                current+=' ';
                while (!stack.empty()) {
                    if (getP(stack.peek()) >= priority) {
                        current += stack.pop();
                    }
                    else break;
                }
                stack.push(Nbr.charAt(i));
            }

            if(priority == -1){
                current+=' ';
                while(getP(stack.peek()) != 1)current+=stack.pop();
                stack.pop();
            }
        }
        while(!stack.empty())current+=stack.pop();

        return current;
    }

    private double toNsw(String nsw) {
        String operation = new String();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < nsw.length();i++){
            if (nsw.charAt(i) == ' ') continue;

            if (getP(nsw.charAt(i)) == 0){
                while ( nsw.charAt(i) != ' ' && getP(nsw.charAt(i)) == 0){
                    operation+=nsw.charAt(i++);
                    if (i == nsw.length())break;}

                stack.push(Double.parseDouble(operation));
                operation = new String();
            }


            if(getP(nsw.charAt(i))> 1){
                double a = stack.pop(), b = stack.pop();

                if(nsw.charAt(i) == '+')stack.push(b+a);
                if(nsw.charAt(i) == '-')stack.push(b-a);
                if(nsw.charAt(i) == '*')stack.push(b*a);
                if(nsw.charAt(i) == '/')stack.push(b/a);
            }
        }
        return stack.pop();
    }
    private int getP(char sbl) {
        if(sbl == '*' || sbl == '/') return 3;
        else if (sbl == '+' || sbl == '-') return 2;
        else if (sbl == '(') return 1;
        else if (sbl == ')') return  -1;
        else return 0;
    }



}