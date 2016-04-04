package bond_street_coding_challenge;

import bond_street_coding_challenge.model.PValueFactor;
import java.util.Map;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

public class PValueCalculator {
    
    /*
        Input is a Map of source key to a map. The nexted Map is a map of factor name to the factor's value.
        PValueFactor contains the expected and observed value
    */
    public double calculatePValue(Map<String,Map<String,PValueFactor>> factors){
        int degreesOfFreedom = this.getDegreesOfFreedom(factors);
        
        double[] expected = new double[degreesOfFreedom];
        long[] observed = new long[degreesOfFreedom];
        int i=0;
        
        for(String source : factors.keySet()){
            Map<String,PValueFactor> sourceFactors = factors.get(source);
            for(String key : sourceFactors.keySet()){
                PValueFactor factor = sourceFactors.get(key);
                expected[i] = factor.getExpectedValue();
                observed[i] = factor.getActualValue();
                i++;
            }
        }
        //use apache common library to calculate p-value. The function is called chiSquareTest, but it
        //returns the p-value
        ChiSquareTest pValueTest = new ChiSquareTest();
        return pValueTest.chiSquareTest(expected, observed);
    }
    
    private int getDegreesOfFreedom(Map<String,Map<String,PValueFactor>> factors){
        int degreesOfFreedom = 0;
        for(String source : factors.keySet()){
            degreesOfFreedom+= factors.get(source).size();
        }
        return degreesOfFreedom;
    }
}