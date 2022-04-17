package io.Hamza.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import io.Hamza.server.enumeration.Status;
import io.Hamza.server.model.Server;
import io.Hamza.server.repo.ServerRepo;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner run(ServerRepo serverRepo) {
	// return args -> {
	// serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB",
	// "http://localhost:8080/server/image/server1.png", "personal pc",
	// Status.SERVER_UP));
	// serverRepo.save(new Server((Long) null, "192.168.1.52", "Windows", "16 GB",
	// "http://localhost:8080/server/image/server2.png", "personal pc",
	// Status.SERVER_UP));
	// serverRepo.save(new Server((Long) null, "192.168.1.30", "MAC", "16 GB",
	// "http://localhost:8080/server/image/server4.png", "personal pc",
	// Status.SERVER_UP));
	// serverRepo.save(new Server((Long) null, "192.168.1.24", "Windows Linux", "32
	// GB",
	// "http://localhost:8080/server/image/server1.png", "personal pc",
	// Status.SERVER_UP));
	// serverRepo.save(new Server((Long) null, "192.168.1.99", "MS 2008 Linux", "8
	// GB",
	// "http://localhost:8080/server/image/server3.png", "Mail server",
	// Status.SERVER_DOWN));
	// };

	// }

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration
				.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
						"Access-Control-Allow-Origin", "Access-Control-Allow-Origin",
						"Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
