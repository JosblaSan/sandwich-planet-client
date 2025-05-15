package cl.josbla.sandwichplanet.security.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "cl.josbla.sandwichplanet.security.client",
    "cl.josbla.sandwichplanet.client"
})
@EnableJpaRepositories(basePackages = "cl.josbla.sandwichplanet.client.repository")  // Ajusta si tienes repositorio en subpaquete
@EntityScan(basePackages = "cl.josbla.sandwichplanet.client.models")
public class ClientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientAppApplication.class, args);
	}

}
