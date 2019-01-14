package in.karthiks.totp;

import in.karthiks.totp.domain.Generation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerationController {

    @Autowired
    private Generation generation;

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public int validate(@RequestParam("email") String email) {
        return generation.generate(email);
    }

}
