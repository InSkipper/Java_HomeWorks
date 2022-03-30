package com.example.firsthomework;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.attribute.FileTime;

@SpringBootApplication
public class FirstHomeworkApplication {
    private static MyClass myClass;

    public FirstHomeworkApplication(MyClass myClass) {
        FirstHomeworkApplication.myClass = myClass;
    }

    public static void main(String[] args) {
        SpringApplication.run(FirstHomeworkApplication.class, args);

        System.out.println(myClass.getText());
    }
}
