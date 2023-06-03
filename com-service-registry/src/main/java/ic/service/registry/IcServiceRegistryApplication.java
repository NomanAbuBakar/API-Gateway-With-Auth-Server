package ic.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IcServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcServiceRegistryApplication.class, args);
	}

}
