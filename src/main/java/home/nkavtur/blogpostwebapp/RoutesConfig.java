package home.nkavtur.blogpostwebapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class RoutesConfig {

    @Bean
    public RouterFunction<?> routes(BlogHandler blogHandler) {
        return RouterFunctions.route()
                .GET("/blog", blogHandler::findAll)
                .GET("/blog/{id}", blogHandler::byId)
                .DELETE("/blog/{id}", blogHandler::deleteById)
                .PUT("/blog/{id}", blogHandler::updateById)
                .POST("/blog/", blogHandler::save)
                .build();
    }

}
