package com.parfait.study.reactivemongo.config.converter.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.parfait.study.reactivemongo.post.entity.Post;

public class PostBodyDeserializer extends StdDeserializer<Post.Body> {

	protected PostBodyDeserializer() {
		super(Post.Body.class);
	}

	@Override
	public Post.Body deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Post.Body body = new Post.Body();
		body.setValue(p.getValueAsString());
		return body;
	}
}
