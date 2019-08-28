package com.parfait.study.reactivemongo.post;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.parfait.study.reactivemongo.post.entity.Post;
import com.parfait.study.reactivemongo.post.entity.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostRepository postRepository;

	// 페이징 처리
	@GetMapping
	public Flux<Post> search(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) { // @PageableDefault Pageable pageable

		Pageable pageable = PageRequest.of(page, size);
		return postRepository.findAll(pageable);
	}

	// simple repository method created by spring data
	@GetMapping("/{id}")
	public Mono<Post> getPost(@PathVariable Long id) {
		return postRepository.findById(id).doOnSuccess(post -> log.info("post : {}", post));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Mono<Post> create(@RequestBody Post post) {
		return postRepository.save(post);
	}

	@PutMapping("/{id}")
	public Mono<Post> replace(@PathVariable Long id, @RequestBody Post post) {
		return postRepository.save(post);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable Long id) {
		return postRepository.deleteById(id);
	}

	// query methods implemented by spring data
	@GetMapping(params =  "username")
	public Flux<Post> findByUsername(@RequestParam String username, @PageableDefault Pageable pageable) {
		return postRepository.findAllByUser_Username(username, pageable);
	}

	// custom repository by reactiveMongoTemplate
	@GetMapping(params = {"userId", "single=true"})
	public Mono<Post> findOneByUserId(@RequestParam Long userId) {
		return postRepository.findOneByUserId(userId);
	}
	@GetMapping(params = "userId")
	public Flux<Post> findByUserId(@RequestParam Long userId) {
		return postRepository.findByUserId(userId);
	}
}
