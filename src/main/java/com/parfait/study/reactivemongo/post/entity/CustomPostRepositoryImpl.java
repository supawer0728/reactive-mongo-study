package com.parfait.study.reactivemongo.post.entity;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository {

	private final ReactiveMongoTemplate template;

	@Override
	public Flux<Post> findByUserId(Long userId) {
		return template.find(new Query(where("user.id").is(userId)), Post.class);
	}

	@Override
	public Mono<Post> findOneByUserId(Long userId) {
		return template.findOne(new Query(where("user.id").is(userId)), Post.class);
	}

	@Override
	public Flux<Post> findAll(Pageable pageable) {
		Query query = new Query().skip(pageable.getOffset()).limit(pageable.getPageSize());

		return template.find(query, Post.class);
	}
}
