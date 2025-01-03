package config;


import java.io.FileInputStream;
import java.lang.module.Configuration;
import java.util.Properties;

public class Config {

    private static final String EXCEL_DATA = "excel.Data";
    ClassLoader classLoader;
    private Properties properties;
    public Config(String filePath){
       this.classLoader = getClass().getClassLoader();
        System.out.println(classLoader.getResource(filePath));
    }


}

