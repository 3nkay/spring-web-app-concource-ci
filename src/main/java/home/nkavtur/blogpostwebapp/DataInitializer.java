package home.nkavtur.blogpostwebapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationListener<ApplicationStartedEvent> {

    private final BlogRepository blogRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        blogRepository.deleteAll()
                .thenMany(Flux.just(
                                new Blog(null, "Spring web Flux", List.of("Spring", "Reactive")),
                                new Blog(null, "Kubernetes", List.of("Microservices", "Deployments", "Istio")),
                                new Blog(null, "R2DBC", List.of("Reactive", "JDBC", "Postgres"))
                        )
                        .flatMap(blogRepository::save)
                )
                .thenMany(blogRepository.findAll())
                .subscribe(b -> log.info("Successfully saved {}", b));

    }



}
