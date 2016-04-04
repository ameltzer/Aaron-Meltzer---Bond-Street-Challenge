/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond_street_coding_challenge.NumericConversion;

/**
 *
 * case in which you only want to check if value exists. Expected is 0 or 1, observed is 0 or 1.
 * Turned into Long so that they can be used to calculate the p-value
 */
public class ExistanceConverter implements NumericConverter{

    @Override
    public Long convertToNumber(Object criteria) {
        return (criteria!=null) ? new Long(1) : new Long(0);
    }
    
}
