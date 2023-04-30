package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.net.URI;
import java.sql.Timestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Getter
@Setter
@Entity
@Accessors(chain = true)
@EqualsAndHashCode
public class Link {

    @Id
    private long id;
    private URI url;
    private Timestamp updatedAt;

}