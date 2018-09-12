package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunningLocationServiceApplication {  //入口
    public static void main (String[] args) {
        SpringApplication.run(RunningLocationServiceApplication.class, args);  //固定写法
    }
}

