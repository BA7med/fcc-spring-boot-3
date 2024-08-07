package dev.danvega.runnerz;

import dev.danvega.runnerz.run.JdbcRunRepository;
import dev.danvega.runnerz.run.Location;
import dev.danvega.runnerz.run.Run;
import dev.danvega.runnerz.user.User;
import dev.danvega.runnerz.user.UserHttpClient;
import dev.danvega.runnerz.user.UserRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner runner (UserRestClient client, JdbcRunRepository runRepository) {
		return args -> {
			List<User> users = client.findAll();
			System.out.println(users);
//			Run run = new Run(12, "Noon Run", LocalDateTime.of(2024, 2, 20, 6, 5), LocalDateTime.of(2024, 2, 20, 10, 27), 24, Location.INDOOR);
//			runRepository.create(run);
		};
	}
	@Bean
	UserHttpClient userHttpClient() {
	    RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
	    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
	    return factory.createClient(UserHttpClient.class);
	}
}
