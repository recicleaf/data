package fh.test.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class AppRouter {

    @Bean
    RouterFunction<ServerResponse> updateEmployeeRoute() {
        return RouterFunctions.route()
                              .GET("/hello", request -> ok().syncBody("Hello World!"))
                              .GET("/hello2", request -> ok().syncBody("Hello World 2!"))
                              .build();
    }
}
