package in.karthiks.totp;

import in.karthiks.totp.domain.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @Autowired
    private Validation validation;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public boolean validate(@RequestParam("email") String email, @RequestParam("otp") String otp) {
        boolean result = validation.validate(email, Integer.valueOf(otp));
        return result;
    }

}
