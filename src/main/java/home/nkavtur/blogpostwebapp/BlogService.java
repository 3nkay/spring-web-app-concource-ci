package home.nkavtur.blogpostwebapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Flux<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Mono<Blog> findById(String id) {
        return blogRepository.findById(id);
    }

    public Mono<Blog> save(Blog blog) {
        return blogRepository.save(blog);
    }

    public Mono<Blog> update(String id, Blog blog) {
        return blogRepository.findById(id)
                .map(b -> blog)
                .flatMap(blogRepository::save);
    }

    public Mono<Blog> delete(String id) {
        return blogRepository.findById(id)
                .flatMap(b -> blogRepository.deleteById(id).thenReturn(b));
    }
}
