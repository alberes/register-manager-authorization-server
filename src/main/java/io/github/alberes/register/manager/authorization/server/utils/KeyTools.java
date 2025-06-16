package io.github.alberes.register.manager.authorization.server.utils;

import io.github.alberes.register.manager.authorization.server.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

@Component
@RequiredArgsConstructor
public class KeyTools {

    private final ResourceLoader resourceLoader;

    private String publicExponent = "65537";

    public PrivateKey loadPrivateKey(String fileName) {
        try {
            Resource resource = this.resourceLoader.getResource(Constants.CLASSPATH + fileName);
            File file = resource.getFile();
            String key = new String(Files.readAllBytes(file.toPath()));
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(new BigInteger(key), new BigInteger(this.publicExponent));
            return KeyFactory
                    .getInstance(Constants.RSA)
                    .generatePrivate(spec);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public PublicKey loadPublicKey(String fileName) {
        try {
            Resource resource = this.resourceLoader.getResource(Constants.CLASSPATH + fileName);
            File file = resource.getFile();
            String key = new String(Files.readAllBytes(file.toPath()));
            RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(key), new BigInteger(this.publicExponent));
            return KeyFactory
                    .getInstance(Constants.RSA)
                    .generatePublic(spec);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

}