package pl.polsl.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.polsl.controller.DeliveryRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@ComponentScan("pl.polsl")
@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories(basePackageClasses = DeliveryRepository.class)
public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Application.class, args);
    }
}
