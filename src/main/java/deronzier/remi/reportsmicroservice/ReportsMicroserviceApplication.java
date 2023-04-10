package deronzier.remi.reportsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The main class of the application.
 * 
 * @author Remi Deronzier
 */
@SpringBootApplication
@EnableFeignClients("deronzier.remi.reportsmicroservice")
@EnableSwagger2
public class ReportsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsMicroserviceApplication.class, args);
	}

}
