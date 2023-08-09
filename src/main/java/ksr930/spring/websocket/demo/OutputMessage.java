package ksr930.spring.websocket.demo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputMessage {
    private String from;
    private String text;
    private String time;
}
