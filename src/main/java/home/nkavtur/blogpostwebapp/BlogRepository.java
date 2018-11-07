package home.nkavtur.blogpostwebapp;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

}
