package ru.tinkoff.edu.java.scrapper.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Getter
@Setter
@Accessors(chain = true)
public class Chat {

    private long trackId;
    private long chatId;
    private long linkId;

}
