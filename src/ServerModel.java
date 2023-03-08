import java.util.ArrayList;

public class ServerModel implements Model {
    public ServerModel() {
        addAprovedOperation('+');
        addAprovedOperation('-');
        addAprovedOperation('*');
        addAprovedOperation('/');
    }

    public void addAprovedOperation(char aprovedOperation) {
        this.aprovedOperation.add(aprovedOperation);
    }

    private ArrayList<Character> aprovedOperation = new ArrayList<Character>();

    public double calc(ArrayList<Double> num, ArrayList<Character> operation) throws Exception{
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

    public String check(String s){
        int countBracket=0;
        for (int i=0;i<s.length();i++){
            if (Character.isDigit(s.charAt(i)) || s.charAt(i)=='.' ||
                    aprovedOperation.contains(s.charAt(i))){
                continue;
            } else if (s.charAt(i)=='('){
                countBracket+=1;
            } else if (s.charAt(i)==')'){
                countBracket-=1;
            } else {
                return "Некорреткный символ:" + s.charAt(i);
            }
        }
        if (countBracket==0){
            return "Ok";
        }
        return "Некорректные скобки";
    }
}
