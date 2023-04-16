package ru.tinkoff.edu.java.scrapper.model;

import java.net.URI;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Getter
@Setter
@Accessors(chain = true)
public class Link {

    private long id;
    private URI url;
    private Timestamp updatedAt;

}
