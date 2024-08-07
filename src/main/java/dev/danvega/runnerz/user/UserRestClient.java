package dev.danvega.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class UserRestClient {
    private final RestClient userClient;

    public UserRestClient(RestClient.Builder builder) {
        this.userClient = builder.baseUrl("https://jsonplaceholder.typicode.com/").build();
    }

    public List<User> findAll() {
        return userClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public User findById(Integer id) {
        return userClient.get()
                .uri("/users/{id}" + id)
                .retrieve()
                .body(User.class);
    }
}
