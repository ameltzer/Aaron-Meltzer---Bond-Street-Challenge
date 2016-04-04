/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond_street_coding_challenge.api_calls;

import bond_street_coding_challenge.NumericConversion.NumericConverter;
import bond_street_coding_challenge.NumericConversion.StraightConverter;
import bond_street_coding_challenge.model.PValueFactor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * RESTCall class for twitter
 */
public class TwitterRESTCall extends RESTCall{
    public static final String CONSUMER_KEY = "ivcG9cNVyAZnlYszQAipYusKA";
    public static final String CONSUMER_SECRET="l6WriJH1vLL18BozRLxABuuy1qwII5CuqVu0ZmY2FRGTU70sRX";
    public static final String TOKEN="3100479951-6AIVXn3Fdm8ptOu2dctNG3toKH0XGlQJsk5Rj76";
    public static final String TOKEN_SECRET="vwAbWRmierxzfJ25zz6hd6b2jp2Le2QGZmza3fIKffFaN";
    
    public static final String SOURCE="Twitter";
            
    public TwitterRESTCall(){
        apiCallService = new RESTCallService(ApiProvider.class, CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
    }

    @Override
    public Map<String, PValueFactor> pingSource(String businessID) throws ParseException {
        String requestPage="https://api.twitter.com/1.1/users/lookup.json?screen_name=";
        //we only care about the first object int he array since a unique id is being passed in.
        JSONObject bodyResult = (JSONObject)apiCallService.requestJSONInfo(requestPage+businessID).get(0);
        if(bodyResult.containsKey("error")){
            System.out.println("The following error occured: "+ ((Map)bodyResult.get("error")).get("text"));
            return null;
        }
        Map<String,PValueFactor> twitterFactors = apiCallService.convertToMap(this.buildKeyToConverter(), bodyResult, 
                this.buildExpectedValue());
        return twitterFactors;
    }
     public Map<String, NumericConverter> buildKeyToConverter(){
        Map<String, NumericConverter> keyToConverter;
        keyToConverter = new HashMap<>();
        keyToConverter.put("followers_count", new StraightConverter());
        return keyToConverter;
    }
    
    public Map<String, Long> buildExpectedValue(){
        Map<String, Long> expectedValue = new HashMap<>();
        expectedValue.put("followers_count", new Long(1000));
        return expectedValue;
    }
}