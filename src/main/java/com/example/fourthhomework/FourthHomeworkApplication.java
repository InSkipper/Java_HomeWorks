package com.example.fourthhomework;

import com.example.fourthhomework.beans.FirstBean;
import com.example.fourthhomework.beans.SecondBean;
import com.example.fourthhomework.beans.ThirdBean;
import com.example.fourthhomework.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MyConfig.class)
public class FourthHomeworkApplication {
    public static MyConfig myConfig;
    public static FirstBean firstBean;
    public static SecondBean secondBean;
    public static ThirdBean thirdBean;


    public FourthHomeworkApplication(MyConfig myConfig, FirstBean firstBean, SecondBean secondBean, ThirdBean thirdBean) {
        FourthHomeworkApplication.myConfig = myConfig;
        FourthHomeworkApplication.firstBean = firstBean;
        FourthHomeworkApplication.secondBean = secondBean;
        FourthHomeworkApplication.thirdBean = thirdBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(FourthHomeworkApplication.class, args);

        System.out.println(myConfig.getList());
        System.out.println(myConfig.getEnvVariable());
    }

}
