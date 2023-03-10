import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerController {
    private static Model model;

    public static double calcFormula(String s) throws Exception{
        while (s.contains("(")){
            int startBracket=s.lastIndexOf("(");
            int endBracket=s.indexOf(")",startBracket);
            s=s.substring(0,startBracket)+calcFormula(s.substring(startBracket+1,endBracket))+
                    s.substring(endBracket+1,s.length());
        }
        int startIndexNum=0;
        ArrayList<Double> num = new ArrayList<Double>();
        ArrayList<Character> operation= new ArrayList<Character>();
        char symbol;
        for (int i=0;i<s.length();i++){
            if (!Character.isDigit(s.charAt(i)) && !(s.charAt(i)=='.')){
                num.add(Double.parseDouble(s.substring(startIndexNum, i)));
                operation.add(s.charAt(i));
                i++;
                startIndexNum=i;


            }
            if (i==s.length()-1){
                num.add(Double.parseDouble(s.substring(startIndexNum, i+1)));
            }
        }
        double result;
        try {
            result=model.calc(num,operation);
        } catch (Exception e){
            throw e;
        }
        return result;

    }
    public static String calc (String s){

        model= new PowDecorator(new ServerModel());
        String error=model.check(s);
        Logger logger = Logger.getAnonymousLogger();

        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("server.log",true);
            fileHandler.setFormatter(formatter);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);
        if (error.equals("Ok")){
            logger.log(Level.INFO, "???????????? ?????? ("+s+")");

            String result;
            try{
                result=s+"="+format.format(calcFormula(s)).replace(',','.');
            } catch (Exception e){
                logger.log(Level.WARNING, "("+s+")"+e.getMessage());
                fileHandler.close();
                return e.getMessage();
            }
            logger.log(Level.INFO, "???????????????????? ("+s+") ?????????????????? ("+result+")");
            fileHandler.close();
            return result;
        } else {
            logger.log(Level.WARNING, "("+s+")"+error);
            fileHandler.close();
            return error;
        }
    }



}
