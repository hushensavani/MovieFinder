package com.hushensavani.moviefinder.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

/**
 * Created on: 15-January-2017
 *
 * @author <a href="mailto:husen.savani1@gmail.com">Hushen Savani</a>
 *
 * @description This utility is responsible for loading application configurations.
 *
 */
public class ApplicationConfigUtil {

    private static ApplicationConfigUtil applicationUtil = null;
    private ResourceBundle resourceBundle = null;
    private Enumeration resourceBundleEnum = null;
    private Hashtable applicationHT = null;

    /**
     * Private constructor of this class in order to make this class Singleton.
     */
    private ApplicationConfigUtil(){};

    /**
     * This method generates Singleton Instance of this class.
     * @return ApplicationConfigUtil - this class.
     */
    public static ApplicationConfigUtil getInstance(){

        if(applicationUtil == null){
            applicationUtil = new ApplicationConfigUtil();
        }
        return applicationUtil;
    }

    /**
     * This method loads all the application configuration properties.
     * @param resourcePath - Path of application configuration properties file.
     * @return Hashtable - Contains all the application configuration properties.
     */
    public Hashtable getConfigHT(String resourcePath) {

        try{
            if(applicationHT==null) {

                this.applicationHT = new Hashtable();
                this.resourceBundle = ResourceBundle.getBundle(resourcePath);
                this.resourceBundleEnum = resourceBundle.getKeys();

                while(resourceBundleEnum.hasMoreElements()){
                    String resounceBundleKey = (String)resourceBundleEnum.nextElement();
                    String resounceBundleValue = resourceBundle.getString(resounceBundleKey);
                    applicationHT.put(resounceBundleKey.trim(),resounceBundleValue.trim());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return new Hashtable(applicationHT);
    }
}
