import java.util.ArrayList;

public class PowDecorator extends ModelDecarator{
    public PowDecorator (Model decoratedModel){
        super (decoratedModel);
        decoratedModel.addAprovedOperation('^');
    }

    @Override
    public double calc(ArrayList<Double> num, ArrayList<Character> operation) throws Exception{
        double a,b;

        if (operation.contains('^')){
            for (int i=0;i<operation.size();i++){
                if (operation.get(i)=='^'){
                    a=num.get(i);
                    b=num.get(i+1);
                    num.set(i,Math.pow(a,b));
                    num.remove(i+1);
                    operation.remove(i);
                    i--;
                }
            }
        }
        return super.calc(num, operation);
    }


}
