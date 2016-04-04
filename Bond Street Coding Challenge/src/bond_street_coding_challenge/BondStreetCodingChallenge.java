package bond_street_coding_challenge;

import bond_street_coding_challenge.api_calls.RESTCall;
import bond_street_coding_challenge.api_calls.TwitterRESTCall;
import bond_street_coding_challenge.api_calls.YelpRESTCall;
import bond_street_coding_challenge.model.PValueFactor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.parser.ParseException;


public class BondStreetCodingChallenge {

    public static void main(String[] args) throws ParseException {
        (new BondStreetCodingChallenge()).initialize();
    }
    
    public void initialize() throws ParseException{
        /*
            maps a source's key to its corresponding REST class. Putting this in its own data structure
            allows the program to loop through the data structure. This pattern is designed to make the program
            more extensible by only requiring a new source be put in the map.
        */
        Map<String, RESTCall> apiCalls = new HashMap<>();
        /*
            maps a source's key to the id passed in by the user.
        */
        Map<String, String> sourceToId = new HashMap<>();
         
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter company's id on yelp");
        String yelpId = scanner.nextLine();
        sourceToId.put(YelpRESTCall.SOURCE, yelpId);
        apiCalls.put(YelpRESTCall.SOURCE, new YelpRESTCall());
        
        System.out.println("Enter your twitter id");
        String twitterId = scanner.nextLine();
        sourceToId.put(TwitterRESTCall.SOURCE, twitterId);
        apiCalls.put(TwitterRESTCall.SOURCE, new TwitterRESTCall());
        
        PValueCalculator calculator = new PValueCalculator();
        Map<String,Map<String,PValueFactor>> factors = this.gatherInformation(sourceToId,apiCalls);
        //if the map is empty, none of the sources succeeded in returning information
        System.out.println((factors.size()>0) ? calculator.calculatePValue(factors) : "Invalid Input");
    }
    
    public Map<String,Map<String,PValueFactor>> gatherInformation(Map<String,String> sourceToId,
             Map<String, RESTCall> apiCalls) throws ParseException{
        Map<String,Map<String,PValueFactor>> factors = new HashMap<>();
        for(String key : apiCalls.keySet()){
            Map<String,PValueFactor> sourceFactors = apiCalls.get(key).pingSource(sourceToId.get(key));
            if(sourceFactors!=null){
                factors.put(key, sourceFactors);
            }
        }
        return factors;
    }
    
}
