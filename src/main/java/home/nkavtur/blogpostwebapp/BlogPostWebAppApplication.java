package home.nkavtur.blogpostwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class BlogPostWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPostWebAppApplication.class, args);
    }

}
