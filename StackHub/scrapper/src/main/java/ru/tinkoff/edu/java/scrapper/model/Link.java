package ru.tinkoff.edu.java.scrapper.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.UriConverter;

@ToString
@Getter
@Setter
@Entity
@Accessors(chain = true)
@EqualsAndHashCode
@AllArgsConstructor
public class Link {

    @Id
    private long id;
    private String url;
    private Timestamp updatedAt;

    public Link() {

    }
}