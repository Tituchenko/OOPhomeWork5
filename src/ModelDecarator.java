import java.util.ArrayList;

public abstract class ModelDecarator implements Model {
    protected Model decoratedModel;
    public ModelDecarator (Model decoratedModel){
        this.decoratedModel=decoratedModel;
    }
    public void addAprovedOperation(char aprovedOperation){
        decoratedModel.addAprovedOperation(aprovedOperation);
    }
    public String check(String s) {
        return decoratedModel.check(s);
    }
    public double calc(ArrayList<Double> num, ArrayList<Character> operation) throws Exception{

        return decoratedModel.calc( num, operation);
    }

}
