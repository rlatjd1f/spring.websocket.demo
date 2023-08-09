package ksr930.spring.websocket.demo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String from;
    private String text;
}
