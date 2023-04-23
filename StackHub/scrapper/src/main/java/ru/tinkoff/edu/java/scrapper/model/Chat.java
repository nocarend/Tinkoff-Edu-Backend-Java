package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Chat {

    @Id
    private long trackId;
    private long chatId;
    private long linkId;

}
