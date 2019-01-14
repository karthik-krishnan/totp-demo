package in.karthiks.totp.domain;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import org.springframework.stereotype.Component;

@Component
public class Generation {

    public int generate(String userId) {
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().setWindowSize(1).build();
        GoogleAuthenticator gAuth = new GoogleAuthenticator(config);
        return gAuth.getTotpPasswordOfUser(userId);
    }
}
