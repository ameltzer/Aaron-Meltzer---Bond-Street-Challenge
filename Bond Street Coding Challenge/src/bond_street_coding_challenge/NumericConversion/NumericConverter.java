/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond_street_coding_challenge.NumericConversion;

/**
 *
 * @author Aaron
 * Interface to guarantee that the class implementing the interface can convert a factor extracted from
 * the JSON form the source into a number that can be used to calculate the p-value. In some cases this is simple
 * because the value is already an integer, but if we want to convert something that isn't a number but can be used
 * to check if the user will default on a bond then we can use this interface to define a converter that makes the value
 * usable. 
 */
public interface NumericConverter {
    public Long convertToNumber(Object criteria);
}
