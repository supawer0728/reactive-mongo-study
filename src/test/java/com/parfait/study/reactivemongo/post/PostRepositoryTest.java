package com.parfait.study.reactivemongo.post;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.parfait.study.reactivemongo.post.entity.Post;
import com.parfait.study.reactivemongo.post.entity.PostRepository;

@DataMongoTest
@RunWith(SpringRunner.class)
public class PostRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(PostRepositoryTest.class);

	@Autowired
	private PostRepository postRepository;

	@Before
	public void setUp() throws Exception {
		WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
		List<Post> posts = webClient.get()
									.uri("/posts")
									.retrieve()
									.bodyToFlux(Post.class)
									.toStream()
									.collect(Collectors.toList());

		postRepository.saveAll(posts);
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Test
	public void findOne() {

		Long expectedId = 1L;

		// when
		Post actual = postRepository.findById(expectedId).block();

		// then
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getId(), is(expectedId));

		log.info("actual : {}", actual);
	}
}
