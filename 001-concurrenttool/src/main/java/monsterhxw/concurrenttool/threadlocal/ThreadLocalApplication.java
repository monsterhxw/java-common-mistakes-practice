package monsterhxw.concurrenttool.threadlocal;

import monsterhxw.concurrenttool.common.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ThreadLocalApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource("/tomcat/tomcat.properties");
        SpringApplication.run(ThreadLocalApplication.class, args);
    }
}