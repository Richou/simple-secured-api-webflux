package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.security.configuration.properties.KeyPairProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyPairHandler {

    private final static Logger logger = LoggerFactory.getLogger(KeyPairProperties.class);

    private final KeyPairProperties keyPairProperties;

    public KeyPairHandler(KeyPairProperties keyPairProperties) {
        this.keyPairProperties = keyPairProperties;
    }

    public KeyPair loadKeyPair() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(logger.isDebugEnabled()) {
            logger.debug("Trying to read public key : " + keyPairProperties.getPublicKey());
        }
        // Read Public Key.
        File filePublicKey = new File(keyPairProperties.getPublicKey());
        FileInputStream fis = new FileInputStream(keyPairProperties.getPublicKey());
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();

        if(logger.isDebugEnabled()) {
            logger.debug("Trying to read private key : " + keyPairProperties.getPrivateKey());
        }
        // Read Private Key.
        File filePrivateKey = new File(keyPairProperties.getPrivateKey());
        fis = new FileInputStream(keyPairProperties.getPrivateKey());
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();

        if(logger.isDebugEnabled()) {
            logger.debug("Trying to generate keypair");
        }
        // Generate KeyPair.
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        if(logger.isDebugEnabled()) {
            logger.debug("Public key generated");
        }

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        if(logger.isDebugEnabled()) {
            logger.debug("Private key generated");
        }

        return new KeyPair(publicKey, privateKey);
    }
}
