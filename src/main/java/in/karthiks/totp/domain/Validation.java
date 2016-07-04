package in.karthiks.totp.domain;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public boolean validate(String userId, int otp) {
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().setWindowSize(1).build();
        GoogleAuthenticator gAuth = new GoogleAuthenticator(config);
        return gAuth.authorizeUser(userId, otp);
    }
}
