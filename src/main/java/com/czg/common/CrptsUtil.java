package com.czg.common;

/**
 * @author _Fnyexx
 *	
 * 加密、解密工具类 DES DESEDE BLOWFISH。MD5
 */
public class CrptsUtil {
	
    public static CrptsCore crptsCore = new CrptsCore();
    
    /** DES类加密器类型*/
    public static String DES = "DES";
    public static String DESEDE = "DESede";
    public static String BLOWFISH = "Blowfish";
    
    /** DES类加密默认KEY*/
    public static String DES_KEY = "3B:C7:5D:F4:37:BA:A8:E5";
    public static String DESEDE_KEY = "8A:80:29:8F:7C:C7:BC:45:31:3B:2F:FD:3B:38:FD:DC:3B:73:80:EA:0E:3B:8C:02";
    public static String BLOWFISH_KEY = "68:78:0F:EE:35:D3:FA:53:EC:11:77:CA:7D:69:73:F5";
    
    public static CrptsCore getCrpts(){
    	return new CrptsCore();
    }
    
    public static CrptsCore getCrpts(String Algorithm){
    	return new CrptsCore(Algorithm);
    }
    
    /**普通md5加密32位
     * @param password 明文
     * @return 密文
     */
    public static String md5Encrypt(String password){
    	CrptsCore crptsCore = getCrpts();
    	return crptsCore.md5Encrypt(password);
    }
    
    /**加盐md5加密32位
     * @param password 明文
     * @return 密文
     */
    public static String md5Encrypt(String password , String salt){
    	if(salt == null) salt="";
    	return md5Encrypt(password+salt);
    }
    
    /**md5密文比较
     * @param password 明文
     * @param salt	盐
     * @param mpassword 数据库中的密文
     * @return 是否异同
     */
    public static boolean md5Compare(String password , String salt , String mpassword){
    	
    	password = md5Encrypt(password , salt);
    	return password.equals(mpassword);
    }
    
    /**生成随机KEY
     * @return KEY-(D3:E3:AB:E3:AB:E3:AB:E3)
     */
    public static String createDESSecretKey(String type){
    	CrptsCore crptsCore = getCrpts(type);
    	return crptsCore.createSecretKey();
    }
    
    /** DES...类加密
     * @param type 加密类型-DES DESEDE BLOWFISH
     * @param key 密匙 8的倍数
     * @param text 原文
     * @return String 密文
     */
    public static String edsEncrypt(String type,String key,String text){
    	CrptsCore crptsCore = getCrpts(type);
    	return crptsCore.encryptData(text, key);
    }
    
    /** DES...类解密
     * @param type 解密类型-DES DESEDE BLOWFISH
     * @param key 密匙 8的倍数
     * @param text 密文
     * @return String 原文
     */
    public static String edsDecrypt(String type,String key,String text){
    	CrptsCore crptsCore = getCrpts(type);
    	return crptsCore.decryptData(text, key);
    }
    
}
