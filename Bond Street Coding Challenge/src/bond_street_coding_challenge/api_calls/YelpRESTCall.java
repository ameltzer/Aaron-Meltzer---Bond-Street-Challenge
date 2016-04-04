/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond_street_coding_challenge.api_calls;

import bond_street_coding_challenge.NumericConversion.ExistanceConverter;
import bond_street_coding_challenge.NumericConversion.NumericConverter;
import bond_street_coding_challenge.NumericConversion.StraightConverter;
import bond_street_coding_challenge.model.PValueFactor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Aaron
 */
public class YelpRESTCall extends RESTCall{
    
    public static final String CONSUMER_KEY = "u0IbBK-lQKrBSDTAcbUFaQ";
    public static final String CONSUMER_SECRET="YPZqPktnHD-Qgdjp0IDvUobuf2k";
    public static final String TOKEN="bGud6wqt_VUZ433F-kYBRuYl2o2JvXzA";
    public static final String TOKEN_SECRET="2hlIGhAoAoL5_wwAaHwSKXKizMk";
    
    public static final String SOURCE="Yelp";
        
    public YelpRESTCall(){
        apiCallService = new RESTCallService(ApiProvider.class, CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
    }
    
    @Override
    public Map<String,PValueFactor> pingSource(String businessID) throws ParseException{
        String businessPage = "https://api.yelp.com/v2/business/";
        //we only care about the first object int he array since a unique id is being passed in.
        JSONObject bodyResult = (JSONObject)apiCallService.requestJSONInfo(businessPage+businessID).get(0);

        if(bodyResult.containsKey("error")){
            System.out.println("The following error occured: "+ ((Map)bodyResult.get("error")).get("text"));
            return null;
        }
        
        Map<String,PValueFactor> yelpFactors = apiCallService.convertToMap(this.buildKeyToConverter(), bodyResult, 
                this.buildExpectedValue());
        return yelpFactors;
    }
    
    public Map<String, NumericConverter> buildKeyToConverter(){
        Map<String, NumericConverter> keyToConverter;
        keyToConverter = new HashMap<>();
        keyToConverter.put("review_count", new StraightConverter());
        keyToConverter.put("display_phone", new ExistanceConverter());
        return keyToConverter;
    }
    
    public Map<String, Long> buildExpectedValue(){
        Map<String, Long> expectedValue = new HashMap<>();
        expectedValue.put("review_count", new Long(100));
        expectedValue.put("display_phone", new Long(1));
        return expectedValue;
    }
}