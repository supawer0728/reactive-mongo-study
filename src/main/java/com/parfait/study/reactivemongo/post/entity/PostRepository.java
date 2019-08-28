package com.parfait.study.reactivemongo.post.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveMongoRepository<Post, Long>, CustomPostRepository {

	Flux<Post> findAllByUser_Username(String username, Pageable pageable);
}
