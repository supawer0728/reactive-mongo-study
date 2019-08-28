package com.parfait.study.reactivemongo.post.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.parfait.study.reactivemongo.config.converter.jackson.PostBodyDeserializer;
import com.parfait.study.reactivemongo.config.converter.jackson.PostBodySerializer;

import lombok.Data;

@Data
@Document
public class Post {
	@Id
	private Long id;

	@Transient
	private Long userId;
	private User user;
	private String title;

	@JsonSerialize(using = PostBodySerializer.class)
	@JsonDeserialize(using = PostBodyDeserializer.class)
	private Body body;

	public Post bindUser(User user) {
		this.user = user;
		return this;
	}

	@Data
	public static class Body {
		private String value;
	}
}
