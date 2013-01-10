package com.czg.common;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

/**
 * @author _Fnyexx
 *	
 * 加密、解密核心 DES,DESede,Blowfish,md5
 */
public class CrptsCore {
	
    private static final Logger logger = Logger.getLogger(CrptsCore.class);
    
    /** 定义 加密算法,可用 DES,DESede,Blowfish- 默认DES*/
	 private String  Algorithm ="DES";
	    
	 public CrptsCore(){}
	 
	 public CrptsCore(String Algorithm){
		 this.Algorithm = Algorithm;
	 }
	 
	 
	   /** 生成密钥,随机生成
	    * @return byte[] 返回生成的密钥
	    */
	    public String createSecretKey() {
	    	
			KeyGenerator keygen = null;
			
			try {
				keygen = KeyGenerator.getInstance(Algorithm);
			} catch (Exception e) {
				logger.error("NoSuchAlgorithmException!");
			}
			SecretKey deskey = keygen.generateKey();
			
			return Utils.byte2hex(deskey.getEncoded());
		}
	    
	    
	    /**
		 * 将指定的数据根据提供的密钥进行加密
		 * @param input 需要加密的数据
		 * @param key 密钥
		 * @return String 加密后的数据
		 * @throws Exception
		 */
	    public String encryptData(String input, String key) {
	    	
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(Utils.stringToByte(key.replace(":", "")), Algorithm);
			Cipher c1 = null;
			byte[] cipherByte = null;
					
			try {
				c1 = Cipher.getInstance(Algorithm);
				c1.init(Cipher.ENCRYPT_MODE, deskey);
				cipherByte = c1.doFinal(input.getBytes());
			} catch (Exception e) {
				logger.error("error!");
			} 
			
			return Utils.byte2hex(cipherByte);
		} 
	    
	    /**
		 * 将给定的已加密的数据通过指定的密钥进行解密
		 * @param input 待解密的数据
		 * @param key 密钥
		 * @return byte[] 解密后的数据
	     * @throws UnsupportedEncodingException 
		 * @throws Exception
		 */
	    public String decryptData(String input, String key) {
	    	
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(Utils.stringToByte(key.replace(":", "")), Algorithm);
			Cipher c1 = null;
			byte[] clearByte = null;
			String decryptStr = null;
			
			try {
				c1 = Cipher.getInstance(Algorithm);
				c1.init(Cipher.DECRYPT_MODE, deskey);
				clearByte = c1.doFinal(Utils.stringToByte(input.replace(":", "")));
				decryptStr = new String(clearByte,"utf-8");
			} catch (Exception e) {
				logger.error("error");
			}
			
			return decryptStr;
		} 
	    
		/**
		 *  对密码进行md5 32位加密(非对称加密，不能解密，适用于密码匹配)
		 * @param :       String 原始密码
		 * @return:       String 密码密文
		 */
		public String md5Encrypt(String pwd){
			
			MessageDigest md = null;
			
			try {
				md = MessageDigest.getInstance("md5");
			} catch (Exception e) {
				logger.error("NoSuchAlgorithmException!");
			}
			md.update(pwd.getBytes());
			byte[] result = md.digest();
			return Utils.byteToString(result);
		}
	    
			}
