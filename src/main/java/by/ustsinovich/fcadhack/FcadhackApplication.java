package by.ustsinovich.fcadhack;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class FcadhackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcadhackApplication.class, args);
    }

}
