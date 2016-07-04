package in.karthiks.totp;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.StreamDataBodyPart;
import in.karthiks.totp.domain.Registration;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static java.lang.String.format;


@RestController
public class RegistrationController {

    @Autowired
    private Registration registration;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("email") String email) {
        String key = registration.register(email);
        String otpURL = getOtpAuthTotpURL("karthiks.in", email, key);
        byte[] qrCodeImageInBytes = getQRCodeInBytes(otpURL);
        ClientResponse clientResponse = sendRegistrationEmail(email, otpURL, qrCodeImageInBytes);
        System.out.println("Send Email Response :" + clientResponse);
        return "Registration successful.  Please check your email for more information.";
    }

    private byte[] getQRCodeInBytes(String data) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        net.glxn.qrgen.javase.QRCode.from(data).writeTo(stream);
        return stream.toByteArray();
    }

    public static String getOtpAuthTotpURL(String issuer, String email, String key) {
        URIBuilder uri = (new URIBuilder()).setScheme("otpauth").setHost("totp").setPath("/" + issuer + ":" + email).setParameter("secret", key);
        if(issuer != null) {
            if(issuer.contains(":")) {
                throw new IllegalArgumentException("Issuer cannot contain the \':\' character.");
            }
            uri.setParameter("issuer", issuer);
        }
        return uri.toString();
    }

    public static ClientResponse sendRegistrationEmail(String email, String otpURL, byte[] qrCodeImageInBytes) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-97efc1da72d50ef8427ed8baa6fab411"));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/sandbox123f94378c844c9f8e5d2e6458acbba0.mailgun.org/messages");
        FormDataMultiPart formData = new FormDataMultiPart();
        formData.field("from", "Administrator<mailgun@sandbox123f94378c844c9f8e5d2e6458acbba0.mailgun.org>");
        formData.field("to", email);
        formData.field("subject", "Your Soft Token");
        formData.field("html", "Please scan the QR Code on any OTP generating application.<br/>Alternatively, you can use the following URL for generating the OTP.<br/>");
        formData.field("html", format("<i>%s</i>", otpURL));
        StreamDataBodyPart bodyPart = new StreamDataBodyPart("inline",new ByteArrayInputStream(qrCodeImageInBytes),"otp.png",MediaType.APPLICATION_OCTET_STREAM_TYPE);
        formData.bodyPart(bodyPart);
        return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).
                post(ClientResponse.class, formData);
    }
}
