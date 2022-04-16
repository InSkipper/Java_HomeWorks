package com.example.fourthhomework;

import com.example.fourthhomework.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MyConfig.class)
public class FourthHomeworkApplication {
    public static MyConfig myConfig;


    public FourthHomeworkApplication(MyConfig myConfig) {
        FourthHomeworkApplication.myConfig = myConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(FourthHomeworkApplication.class, args);

        System.out.println(myConfig.getList());
        System.out.println(myConfig.getEnvVariable());
    }

}
