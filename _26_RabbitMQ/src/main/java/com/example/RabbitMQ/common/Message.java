package com.example.RabbitMQ.common;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    private Long index;
    private String content;

    public static Message of(Long index, String content) {
        return new Message(index, content);
    }

}
