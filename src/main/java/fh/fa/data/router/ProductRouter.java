package fh.fa.data.router;

import fh.fa.data.model.Product;
import fh.fa.data.repository.ProductReactiveRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class ProductRouter {

    @Bean
    RouterFunction<ServerResponse> productRoutes(final ProductReactiveRepository productReactiveRepository) {
        return RouterFunctions.route()
                              .GET("/products", request -> ServerResponse.ok().contentType(APPLICATION_JSON).body(productReactiveRepository.findAll(), Product.class))
                              .POST("/products", request -> ServerResponse.status(HttpStatus.CREATED)
                                                                          .contentType(APPLICATION_JSON)
                                                                          .body(productReactiveRepository.save(request.bodyToMono(Product.class)),
                                                                                Product.class))

                              .GET("/products/{id}", request -> ServerResponse.ok()
                                                                              .contentType(APPLICATION_JSON)
                                                                              .body(productReactiveRepository.findById(Mono.just(Long.valueOf(request.pathVariable("id")))),
                                                                                    Product.class))
                              .PUT("/products/{id}", request -> ServerResponse.ok()
                                                                              .contentType(APPLICATION_JSON)
                                                                              .body(productReactiveRepository.update(
                                                                                      Mono.just(Long.valueOf(request.pathVariable("id"))),
                                                                                      request.bodyToMono(Product.class)),
                                                                                    Product.class))
                              .DELETE("/products/{id}", request -> ServerResponse.ok()
                                                                                 .body(productReactiveRepository.delete(Mono.just(Long.valueOf(request.pathVariable("id")))),
                                                                                       Void.class))

                              .POST("/products/bulk", request -> ServerResponse.status(HttpStatus.CREATED)
                                                                               .contentType(APPLICATION_JSON)
                                                                               .body(productReactiveRepository.save(request.bodyToFlux(Product.class)),
                                                                                     Product.class))
                              .build();
    }
}
