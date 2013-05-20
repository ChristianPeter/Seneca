/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author murdoc
 */
public class EncryptionUtils {
    
    
    
    public synchronized static String encryptPassword(String passwordStr, String saltStr) throws IOException, NoSuchAlgorithmException{
        // first convert salt string to byte[]
        byte[] salt = Base64.decodeBase64(saltStr.getBytes());
        // get the digest and setup salt
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	digest.reset();
	digest.update(salt);
        
        // digest password step 1
        byte[] passwd = digest.digest(passwordStr.getBytes("UTF-8"));
        
        // iterate 100 times
        for (int i = 0 ; i < 100; i++){
            digest.reset();
            passwd = digest.digest(passwd);
        }
        
        // and finally convert the byte[] back to string        
        return new String(Base64.encodeBase64(passwd));
    }
}
