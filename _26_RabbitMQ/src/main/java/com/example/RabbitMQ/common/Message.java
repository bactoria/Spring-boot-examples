package com.example.RabbitMQ.common;

import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message implements Serializable {

	private Long index;
	private String content;
	
	public static Message of(Long index, String content) {
		return new Message(index, content);
	}

}
