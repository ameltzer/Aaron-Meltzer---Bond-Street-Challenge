/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond_street_coding_challenge.NumericConversion;

/**
 *
 * Used in case where the output is already a number
 */
public class StraightConverter implements NumericConverter{

    @Override
    public Long convertToNumber(Object criteria) {
        if(criteria instanceof Integer){
            return new Long((Integer)criteria);
        }
        if(criteria instanceof Long){
            return (Long)criteria;
        }
        return null;
    }
  
}