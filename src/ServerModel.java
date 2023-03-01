import java.util.ArrayList;

public class ServerModel {
    public static double calc(ArrayList<Double> num, ArrayList<Character> operation) throws Exception{
        double a,b;
        if (operation.contains('*') || operation.contains('/')){
            for (int i=0;i<operation.size();i++){
                if (operation.get(i)=='*' || operation.get(i)=='/'){
                   a=num.get(i);
                   b=num.get(i+1);
                   if (operation.get(i)=='*'){
                       num.set(i,a*b);
                   } else {
                       num.set(i,a/b);
                       if (b==0){
                           throw new Exception ("Деление на ноль!");
                       }
                   }
                   num.remove(i+1);
                   operation.remove(i);
                   i--;
                }
            }
        }
        if (operation.contains('+') || operation.contains('-')){
            for (int i=0;i<operation.size();i++){
                if (operation.get(i)=='+' || operation.get(i)=='-'){
                    a=num.get(i);
                    b=num.get(i+1);
                    if (operation.get(i)=='+'){
                        num.set(i,a+b);
                    } else {
                        num.set(i,a-b);
                    }
                    num.remove(i+1);
                    operation.remove(i);
                    i--;
                }
            }
        }
        return num.get(0);
    }
}
