package com.czg.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtils
{
	
	/**
	 * 
	 * @param path
	 * @return
	 */
    public static PropertiesConfiguration getInstance(String path)
    {
    	
    	PropertiesConfiguration config=null;
    	
        try
		{
			config=new PropertiesConfiguration(path);
			config.load();
		} 
        catch (ConfigurationException e)
		{
			e.printStackTrace();
		} 
    	
    	return config;
    }
    
    
    
}
