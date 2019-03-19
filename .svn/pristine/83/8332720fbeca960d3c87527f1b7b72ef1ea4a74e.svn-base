package com.tenpay.wxwork.salary.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.lang.RuntimeException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenpay.key.api.KeyApi;
import com.tenpay.key.api.KeyException;
import com.tenpay.key.api.KeyInfo;

public class KeyStoreService {
	static String PriKeyHeader = "-----BEGIN PRIVATE KEY-----";
	static String PriKeyFooter = "-----END PRIVATE KEY-----";
    
    /**
     * 密钥管理系统api
     */
    private static KeyApi keyApi = new KeyApi();
    
	private static final Logger LG = LoggerFactory.getLogger(KeyStoreService.class);

    private static PrivateKey loadPrivateKeyByStr(String privateKeyStr) {
        try
        {  
        	privateKeyStr = privateKeyStr.replace(PriKeyHeader,"");
        	privateKeyStr = privateKeyStr.replace(PriKeyFooter,"");
    		Base64 base64 = new Base64();
    		byte[] decoded = base64.decode(privateKeyStr.getBytes("UTF-8"));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");    
    		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
    		return keyFactory.generatePrivate(keySpec);
        }
        catch (NoSuchAlgorithmException e)
        {    
            throw new RuntimeException("无此算法");    
        }
        catch (InvalidKeySpecException e)
        {    
            throw new RuntimeException("私钥非法");    
        }
        catch (NullPointerException e)
        {    
            throw new RuntimeException("私钥数据为空");    
        } catch (UnsupportedEncodingException e) {
			throw new RuntimeException("编码格式错误");
		}
	}

    private static PublicKey loadCertPublicKey(String certString)
    {
        if (StringUtils.isBlank(certString)) {
            throw new RuntimeException("certificate info is empty.");
        }
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream stream = new ByteArrayInputStream(certString.getBytes(StandardCharsets.UTF_8.name()));
            Certificate certificate = cf.generateCertificate(stream);
            return certificate.getPublicKey();
        } catch (CertificateException e) {
            throw new RuntimeException("certificate exception");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(String.format("get bank public key error[%s] ", certString), e);
        } catch (RuntimeException e) {
            throw new RuntimeException(String.format("get bank public key error[%s] ", certString), e);
        }
    }

    /**
     * 获取明文密钥
     *
     */
    public static String getKey(String keyId,int keyVer) {
		LG.debug("key id:"+keyId);

    	try
    	{
            KeyInfo key;
    		key = keyApi.getNewestKey(keyId, keyVer);
    		return key.getValue();
    	}
    	catch (KeyException e) {
    		LG.error("getNewestkey error."+e.getErrorCode()+e.getErrorMessage());
     		throw new RuntimeException("Failed to get newest key via KeyApi.");
    	}
    	catch(RuntimeException e)
    	{
    		LG.error("Failed to get key value."+e);
    		throw new RuntimeException("Failed to get key value.");
		}
    }

    /**
     * 获取解密后的密钥
     *
     * @param privateKeyId 解密的私钥 id
     * @param privateKeyVer
     */
    public static String getDecryptedKey(String keyId, int keyVer,
                                         String privateKeyId, int privateKeyVer) {
		LG.debug("key id:"+keyId);

    	try
    	{
            KeyInfo key;
    		key = keyApi.getNewestKey(keyId, keyVer);
    		String encryptedKey = key.getValue();

            PrivateKey privateKey = getPrivateKey(privateKeyId, privateKeyVer);
            return RsaHandler.decrypt(privateKey, encryptedKey, "GBK");
    	}
    	catch (KeyException e) {
    		LG.error("getNewestkey error."+e.getErrorCode()+e.getErrorMessage());
    		throw new RuntimeException("Failed to get newest key via KeyApi.");
    	}
    	catch(RuntimeException e)
    	{
    		LG.error("Failed to get key value."+e);
    		throw new RuntimeException("Failed to get key value.");
		}
    }

    public static PublicKey getPublicKey(String bankCrtId,int bankCrtVer) {
    	KeyInfo key;
		LG.debug("certKeyId:"+bankCrtId);

    	try
    	{
    		key = keyApi.getNewestKey(bankCrtId, bankCrtVer);
    		return loadCertPublicKey(key.getValue());
    	}
    	catch (KeyException e) {
    		LG.error("getNewestkey error."+e.getErrorCode()+e.getErrorMessage());
    		throw new RuntimeException("Failed to get newest key via KeyApi.");
    	}
    	catch(RuntimeException e)
    	{
    		LG.error("Failed to get private key."+e);
    		throw new RuntimeException("Failed to get private key.");
		}
    }

    public static PrivateKey getPrivateKey(String privateKeyId,int privateKeyVer) {
    	KeyInfo key;
		LG.debug("privateKeyId:"+privateKeyId);

        PrivateKey privateKey = null;
    	try
    	{
    		key = keyApi.getNewestKey(privateKeyId, privateKeyVer);
    		privateKey = loadPrivateKeyByStr(key.getValue());
    	}
    	catch (KeyException e) {
    		LG.error("getNewestkey error."+e.getErrorCode()+e.getErrorMessage());
    		throw new RuntimeException("Failed to get private key.");
    	}
    	catch(RuntimeException e)
    	{
    		LG.error("Failed to get private key."+e);
    		throw new RuntimeException("Failed to get private key.");
		}

        return privateKey;
    }
    		
    // public static KeyInfo getNewestKey(String privateKeyId){
	// 	try
	// 	{
	// 		KeyInfo key = keyApi.getNewestKey(privateKeyId, 1);
	// 		return key;
	// 	}
	// 	catch (KeyException e) {
	// 		LG.error("getNewestkey error."+e.getErrorCode()+e.getErrorMessage());
	// 		throw new RuntimeException("Failed to get private key.");
	// 	}
    // }

//     public static PrivateKey getCftPrivateKey(KeyInfo key) throws RuntimeException{
// 		LG.debug("privateKeyId:"+key.getId());
//     	try
//     	{
//     		loadPrivateKeyByStr(key.getValue());
//     	}
//     	catch (KeyException e) {
//     		LG.error("getNewestkey error."+e.getErrorCode()+e.getErrorMessage());
//     		throw new RuntimeException("Failed to get private key.");
//     	}
//     	catch(RuntimeException e)
//     	{
//     		LG.error("Failed to get private key."+e);
//     		throw new RuntimeException("Failed to get private key.");
// 		}
//         return privateKey;
//     }

}
