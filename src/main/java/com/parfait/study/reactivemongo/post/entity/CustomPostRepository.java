package com.parfait.study.reactivemongo.post.entity;

import org.springframework.data.domain.Pageable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomPostRepository {

	Mono<Post> findOneByUserId(Long userId);
	Flux<Post> findAll(Pageable pageable);

	Flux<Post> findByUserId(Long userId);
}

