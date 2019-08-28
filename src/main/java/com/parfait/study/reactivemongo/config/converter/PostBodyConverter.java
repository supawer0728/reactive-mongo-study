package com.parfait.study.reactivemongo.config.converter;

import org.springframework.core.convert.converter.Converter;

import com.parfait.study.reactivemongo.post.entity.Post;

public class PostBodyConverter implements Converter<String, Post.Body> {
	@Override
	public Post.Body convert(String source) {
		Post.Body body = new Post.Body();
		body.setValue(source);
		return body;
	}
}
