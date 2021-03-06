import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;

/**
 * Created by Alexey Padyukov on 19.06.2017.
 */

public class Encryptor {

    public static PrivateKey getRSAKey() throws IOException {
        byte[] modulusBytes = Base64.decodeBase64("t8nC/Eth8UabQbXu8pdro3v7NqUanV8Y+g92YgT7z1xqkBLRHXZ1guml3PxrqjNX9AvOmu8R+qaKOyHfJW0PcRDLzCoIUcHNAwpDO/E5j6WAaLIv7gAjTtyr9kJB9rfJaparViJNZu3RSUYGTvVznOmXMf7LTOTMR6HP/5H1TP5n1g4+BbLmC9EhjUf2eNFqwZBqPtzybBb6jaHBRaJ0XdE3lh2OeE9/OF0BtLwiYPDKsVTxIekbNf7l/DREy+YbUOxQLceeHXrvbYLiGWecP0a7CqHGj9ZNY1oJThK3AwrSd4yHa9Wnx/GaZUNtWud1BaP9g3sVX+sRV9xtnI96dw==");
        byte[] publicExpBytes = Base64.decodeBase64("AQAB");
        BigInteger modulus = new BigInteger(1, modulusBytes);
        BigInteger publicExp = new BigInteger(1, publicExpBytes);
        RSAPrivateKeySpec keySpec=new RSAPrivateKeySpec(modulus, publicExp);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance("RSA");
            PrivateKey priv = factory.generatePrivate(keySpec);
            return priv;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] signData(byte[] data, String derKeyPath) throws IOException {
        Signature signature = null;
        try {
            signature = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            signature.initSign(get(derKeyPath));
            signature.update(data);
            return signature.sign();
        } catch (InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PrivateKey get(String filename) throws IOException {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

}
