package ru.tinkoff.edu.java.scrapper.service;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import ru.tinkoff.edu.java.scrapper.model.Link;

public interface LinkService {
    Link add(URI url);
    Link remove(URI url);
    Link getLinkById(long id);
    Link getLinkByUrl(URI url);
    List<Link> findAll(Timestamp timeBeforeUpdates);

}
