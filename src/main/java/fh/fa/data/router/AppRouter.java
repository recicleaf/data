package fh.fa.data.router;

import fh.fa.data.model.Material;
import fh.fa.data.repository.MaterialReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class AppRouter {

    @Autowired
    private MaterialReactiveRepository materialRepository;

    @Bean
    RouterFunction<ServerResponse> updateEmployeeRoute() {
        return RouterFunctions.route()

                              .GET("/materials", request -> ServerResponse.ok().contentType(APPLICATION_JSON).body(materialRepository.findAll(), Material.class))
                              .POST("/materials", request -> ServerResponse.status(HttpStatus.CREATED)
                                                                           .contentType(APPLICATION_JSON)
                                                                           .body(materialRepository.save(request.bodyToMono(Material.class)), Material.class))

                              .GET("/materials/{id}", request -> ServerResponse.ok()
                                                                               .contentType(APPLICATION_JSON)
                                                                               .body(materialRepository.findById(Mono.just(Long.valueOf(request.pathVariable("id")))),
                                                                                     Material.class))
                              .DELETE("/materials/{id}", request -> ServerResponse.ok()
                                                                                  .body(materialRepository.delete(Mono.just(Long.valueOf(request.pathVariable("id")))),
                                                                                        Void.class))
                              .PUT("/materials/{id}", request -> ServerResponse.ok()
                                                                               .contentType(APPLICATION_JSON)
                                                                               .body(materialRepository.update(Mono.just(Long.valueOf(request.pathVariable("id"))),
                                                                                                               request.bodyToMono(Material.class)),
                                                                                     Material.class))
                              .build();
    }
}
