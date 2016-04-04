/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bond_street_coding_challenge.api_calls;

import bond_street_coding_challenge.model.PValueFactor;
import java.util.Map;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Aaron
 * Abstract class that guarantees the extender defines an implementation of how to ping its source, and that
 * it has a reference to the RESTCallService.
 */
public abstract class RESTCall {
    protected RESTCallService apiCallService;
    public abstract Map<String,PValueFactor> pingSource(String businessID) throws ParseException;

}
