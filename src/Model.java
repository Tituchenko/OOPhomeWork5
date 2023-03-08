import java.util.ArrayList;

public interface Model {
    public String check(String s);
    public double calc(ArrayList<Double> num, ArrayList<Character> operation) throws Exception;

    public void addAprovedOperation(char aprovedOperation);

}
