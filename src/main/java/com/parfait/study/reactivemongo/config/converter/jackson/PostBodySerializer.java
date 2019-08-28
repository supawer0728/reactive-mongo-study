package com.parfait.study.reactivemongo.config.converter.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.parfait.study.reactivemongo.post.entity.Post;

public class PostBodySerializer extends StdSerializer<Post.Body> {

	protected PostBodySerializer() {
		super(Post.Body.class);
	}

	@Override
	public void serialize(Post.Body value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.getValue());
	}
}
