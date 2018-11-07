package home.nkavtur.blogpostwebapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@Component
@RequiredArgsConstructor
public class BlogHandler {

    private final BlogService blogService;

    public Mono<ServerResponse> findAll(ServerRequest r) {
        return ServerResponse.ok().body(blogService.findAll(), Blog.class);
    }

    public Mono<ServerResponse> byId(ServerRequest r) {
        return ServerResponse.ok().body(blogService.findById(id(r)), Blog.class);
    }

    public Mono<ServerResponse> save(ServerRequest r) {
        return Mono.from(r.bodyToFlux(Blog.class).flatMap(blogService::save))
                .flatMap(b -> ServerResponse
                            .created(fromPath("/blog/{id}").buildAndExpand(Map.of("id", b.getId())).toUri())
                            .build()
                );
    }

    public Mono<ServerResponse> deleteById(ServerRequest r) {
        return ServerResponse.ok().body(blogService.delete(id(r)), Blog.class);
    }

    public Mono<ServerResponse> updateById(ServerRequest r) {
        return ServerResponse.ok().body(r.bodyToMono(Blog.class)
                .flatMap(b -> blogService.update(b.getId(), b)), Blog.class);
    }

    private String id(ServerRequest serverRequest) {
        return serverRequest.pathVariable("id");
    }


}
