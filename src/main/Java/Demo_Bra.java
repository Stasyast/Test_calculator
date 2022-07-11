import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Demo_Bra {

    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);

        int bra_arr_length =100;
        int[] op_bra = new int[bra_arr_length];
        String[] bra_str = new String[bra_arr_length];
        int bra_ind = 0;
        int unp_bra = 0;
        boolean bra_ok = true;
        int digits = 0;

        System.out.println("Введите выражение: ");
        String inp_str = inp.nextLine();

        if (!inp_str.matches("[0-9.()+\\-*/]+")) {
            System.out.println("Вы ввели неверный символ") ;
            return;
        }
        for (int i = 0; i < inp_str.length(); i++) {
            if (Character.isDigit(inp_str.charAt(i))) {
                digits++;
            }
            if ( inp_str.charAt(i) == '(' )  {
                op_bra[unp_bra] = i;
                unp_bra++;
            } else {
                if ( inp_str.charAt(i) == ')' )  {
                    unp_bra--;
                    if ((unp_bra < 0) || (op_bra[unp_bra] == (i-1))) {
                        bra_ok = false;
                        break;
                    } else {
                        bra_str[bra_ind] = inp_str.substring(op_bra[unp_bra]+1, i);
                        bra_ind++;
                    }
                }
            }
        }
        System.out.println("Количество цифр: " + digits);
        String numbers_str = inp_str.replaceAll("[^0-9.]+", " ");
        int numbers =numbers_str.trim().split(" ").length;
        System.out.println("Количество чисел: " + numbers);


        if ((bra_ok) && (unp_bra == 0)) {
                Calculator calculator = new Calculator();
                double answer = calculator.decide(inp_str);
                System.out.println("Ответ: " + answer);
            WriteToFile.writeFile(inp_str + " = " + Double.toString(answer));

        }

        else
        {
            System.out.println("Неверное количество скобок");
        }
    }
}