package ru.tinkoff.edu.java.scrapper.repository.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LinkRepository {

    @Id
    Long id;
    String url;
    Timestamp updatedAt;

}