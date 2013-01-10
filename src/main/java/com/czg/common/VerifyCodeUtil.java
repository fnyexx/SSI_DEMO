package com.czg.common;

import java.util.Random;

/**
 * @author Administrator
 *	随机码生成器
 */
public class VerifyCodeUtil {

	//生成验证码所使用的随机数发生器
	private static Random verifycodeRandom = new Random();
		
	//随机码生成的取值范围
	private static final String[] verifycodeRange = { "1","2","3","4","5","6","7","8","9", 
	        "a","b","c","d","e","f","g",
	        "h","j","k","m","n","p","q","r","s","t",
	        "u","v","w","x","y"
	      };
	
	//激活码
    private static final String activityCode="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * 产生激活码
     * @param len 长度
     * @return 验证码
     */
    public static String CreateAuthStr(int len)
    {
        StringBuffer authStrBuffer = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < len; i++)
        {
       	 authStrBuffer.append( activityCode.charAt( random.nextInt( activityCode.length()) ) ); 
        }
        return authStrBuffer.toString();
    }
    
    /**
     * 产生验证码
     * @param len 长度
     * @param OnlyNum 是否仅为数字
     * @return
     */
    public static String CreateAuthStr(int len, boolean OnlyNum)
    {
        int number;
        StringBuffer checkCode=new StringBuffer();

        for (int i = 0; i < len; i++)
        {
            if (!OnlyNum)
                number = verifycodeRandom.nextInt(verifycodeRange.length);
            else
                number = verifycodeRandom.nextInt(10);

            checkCode.append(verifycodeRange[number]);
        }
        return checkCode.toString();
    }
}
