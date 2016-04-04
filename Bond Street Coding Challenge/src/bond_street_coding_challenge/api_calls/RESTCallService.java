package bond_street_coding_challenge.api_calls;

import bond_street_coding_challenge.NumericConversion.NumericConverter;
import bond_street_coding_challenge.model.PValueFactor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 *
 * Service to use the source's REST service authenticating with OAuth
 */
public class RESTCallService {
     private OAuthService queryService;
     private Token accessToken;
     
     public RESTCallService(Class<? extends DefaultApi10a> providerClass, String CONSUMER_KEY, String CONSUMER_SECRET, String TOKEN, String TOKEN_SECRET){
         queryService = new ServiceBuilder().provider(providerClass).apiKey(CONSUMER_KEY).apiSecret(CONSUMER_SECRET).build();
         accessToken = new Token(TOKEN, TOKEN_SECRET);
     }
     
     public JSONArray requestJSONInfo(String url) throws ParseException{
        OAuthRequest request = new OAuthRequest(Verb.GET, url);
        queryService.signRequest(accessToken, request);
        Response response = request.send();
        String body = response.getBody();
        
        JSONParser parser = new JSONParser();
        Object bodyResult =  parser.parse(body);
        //the more generic case returns an array of JSON Objects, but we don't know
        //if JSONArray or JSONObject will be returned. If the latter, put it in an array
        //callign class can decide what it wants to do with the array
        JSONArray result;
        if(bodyResult instanceof JSONArray){
            result = (JSONArray)bodyResult;
        }
        else {
            result = new JSONArray();
            result.add(bodyResult);
        }
        return result;
     }
     
     /*
        use map defining which converter to use with which field, and convert the input into a map that can be
        used to calculate the p value
     */
     public Map<String, PValueFactor> convertToMap(Map<String, NumericConverter> keyToConverter, JSONObject bodyResult,Map<String,Long> expectedValue){
      Map<String, PValueFactor> factors = new HashMap<>();
      for(String key : keyToConverter.keySet()){
            Long result = keyToConverter.get(key).convertToNumber(bodyResult.get(key));
            if(result!=null){
                factors.put(key, new PValueFactor(expectedValue.get(key), result));
            }
        }
      return factors;
     }
}
