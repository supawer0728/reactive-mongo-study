package com.parfait.study.reactivemongo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.parfait.study.reactivemongo.post.entity.Post;
import com.parfait.study.reactivemongo.post.entity.PostRepository;
import com.parfait.study.reactivemongo.post.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

	private final PostRepository repository;
	private final MongoTemplate mongoTemplate;

	@Override
	public void run(String... args) throws Exception {
		WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();

		Map<Long, User> users = webClient.get()
										 .uri("/users")
										 .retrieve()
										 .bodyToFlux(User.class)
										 .toStream()
										 .collect(Collectors.toMap(User::getId, Function.identity()));

		List<Post> posts = webClient.get()
									.uri("/posts")
									.retrieve()
									.bodyToFlux(Post.class)
									.map(post -> post.bindUser(users.getOrDefault(post.getUserId(), null)))
									.toStream()
									.collect(Collectors.toList());

		repository.saveAll(posts).blockLast();
		mongoTemplate.indexOps(Post.class).ensureIndex(new Index("user.username", Sort.Direction.ASC));
	}
}
