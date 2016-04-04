
package bond_street_coding_challenge.model;

/**
 *
 * model to store the expected and actual value of a particular field.
 */
public class PValueFactor {
    private double expectedValue;
    private long actualValue;
    
    public PValueFactor(double expectedValue, long actualValue){
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
    }
    
    public double getExpectedValue(){
        return this.expectedValue;
    }
    public long getActualValue(){
        return this.actualValue;
    }
    public double getFactorChiSquare(){
        return Math.pow(actualValue-expectedValue, 2)/expectedValue;
    }
}
