package in.karthiks.totp;

import com.warrenstrange.googleauth.ICredentialRepository;

import java.util.HashMap;
import java.util.List;

import static java.lang.String.format;

public class UserRepository implements ICredentialRepository {
    private static HashMap<String, User> keyStore = new HashMap();
    public String getSecretKey(String userId) {
        if(keyStore.containsKey(userId)) {
            return keyStore.get(userId).getKey();
        } else {
            throw new RuntimeException(format("Key for ID:<%s> not found", userId));
        }
    }

    public void saveUserCredentials(String id, String key, int verificationCode, List<Integer> scratchCodes) {
        keyStore.put(id, new User(key, verificationCode, scratchCodes));
    }
}

class User {
    public String getKey() {
        return key;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public List<Integer> getScratchCodes() {
        return scratchCodes;
    }

    private final String key;
    private final int verificationCode;
    private final List<Integer> scratchCodes;

    public User(String key, int verificationCode, List<Integer> scratchCodes) {
        this.key = key;
        this.verificationCode = verificationCode;
        this.scratchCodes = scratchCodes;
    }
}
