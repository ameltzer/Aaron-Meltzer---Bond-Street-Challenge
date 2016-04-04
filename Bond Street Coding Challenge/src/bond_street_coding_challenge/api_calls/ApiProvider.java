
package bond_street_coding_challenge.api_calls;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/*
    Required concerete class to use OAuth. Does not add any functionality.
*/
public class ApiProvider extends DefaultApi10a{

        
        @Override
        public String getRequestTokenEndpoint() {
            return null;
        }

        @Override
        public String getAccessTokenEndpoint() {
            return null;
        }

        @Override
        public String getAuthorizationUrl(Token token) {
            return null;
        }
    
}
