package com.ksoft.data;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public abstract class PassCodeUtil {

	private static final String APP_KEY="mUGupUruMuGuPuRu";
	private static final String ENCODING= "ISO-8859-1";
	
	private static String getAppPassCode(String userPassCode){
		String appPassCode=userPassCode;
		if(userPassCode.length()<16){
			appPassCode = appPassCode+APP_KEY.substring(0,(APP_KEY.length()-userPassCode.length()));
		}
		return appPassCode;
	}
	
	public static String encryptedData(String userkey, String userData){
		String encryptedUserData="";
	  try {
		  
           String key=getAppPassCode(userkey);
           // Create key and cipher
           Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
           Cipher cipher = Cipher.getInstance("AES");
 
           // encrypt the text
           cipher.init(Cipher.ENCRYPT_MODE, aesKey);
           byte[] encrypted = cipher.doFinal(userData.getBytes());
           encryptedUserData = new String(encrypted,ENCODING);
 
	  	}catch(Exception e) {
           e.printStackTrace();
        }
	  return encryptedUserData;
	}
	public static String decryptedData(String userkey, String encryptedData){
		String decryptedUserData="";
		try { 
           String key = getAppPassCode(userkey);
           // Create key and cipher
           Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
           Cipher cipher = Cipher.getInstance("AES");
           // decrypt the text
           cipher.init(Cipher.DECRYPT_MODE, aesKey);
           byte[] userData = cipher.doFinal(encryptedData.getBytes(ENCODING));
           decryptedUserData  = new String(userData);
           
        }catch(Exception e) {
           e.printStackTrace();
        }
		return decryptedUserData;
	}

	
	public static String encryptedPassword(String key, String userData){
		String encryptedUserData="";
	  try {
		  
           // Create key and cipher
           Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
           Cipher cipher = Cipher.getInstance("AES");
 
           // encrypt the text
           cipher.init(Cipher.ENCRYPT_MODE, aesKey);
           byte[] encrypted = cipher.doFinal(userData.getBytes());
           encryptedUserData = new String(encrypted,ENCODING);
 
	  	}catch(Exception e) {
           e.printStackTrace();
        }
	  return encryptedUserData;
	}
	public static String decryptedPassword(String key, String encryptedData){
		String decryptedUserData="";
		try { 
           // Create key and cipher
           Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
           Cipher cipher = Cipher.getInstance("AES");
           // decrypt the text
           cipher.init(Cipher.DECRYPT_MODE, aesKey);
           byte[] userData = cipher.doFinal(encryptedData.getBytes(ENCODING));
           decryptedUserData  = new String(userData);
           
        }catch(Exception e) {
           e.printStackTrace();
        }
		return decryptedUserData;
	}

}
