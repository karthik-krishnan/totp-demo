package in.karthiks.totp.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "mailgun")
@Configuration
public class MailgunConfig {
    private String apiKey;
    private String apiEndpoint;
    private String senderEmail;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public String getSenderEmail() {
        return senderEmail;
    }
}