import java.beans.Expression;

public class Num implements Expr {
    /*
    * A numeric constant literal
    *  */
    double value; // The value of the number

    public Num(double x){
        this.value = x;
    }

    public double evaluate(){
        return this.value;
    }
}
