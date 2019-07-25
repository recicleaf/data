//package fh.test.helloworld.repository;
//
//import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
//import io.r2dbc.postgresql.PostgresqlConnectionFactory;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
//@Configuration
//@EnableR2dbcRepositories
//public class PersistenceConfiguration extends AbstractR2dbcConfiguration {
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return new PostgresqlConnectionFactory(
//                PostgresqlConnectionConfiguration.builder()
//                                                 .host("http://localhost")
//                                                 .port(5432)
//                                                 .database("fa")
//                                                 .username("root")
//                                                 .password("root")
//                                                 .build());
//    }
//}
