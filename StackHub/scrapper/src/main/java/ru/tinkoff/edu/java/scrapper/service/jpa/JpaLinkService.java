package ru.tinkoff.edu.java.scrapper.service.jpa;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@RequiredArgsConstructor
public class JpaLinkService implements LinkService {

    private final JpaLinkRepository repository;

    @Override
    public Link add(URI url) {
        repository.add(url.toString(), new Timestamp(System.currentTimeMillis()));
        return new Link().setUrl(url.toString());
    }

    @Override
    public Link remove(URI url) {
        repository.remove(url.toString());
        return new Link().setUrl(url.toString());
    }

    @Override
    public Link getLinkById(long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Link getLinkByUrl(URI url) {
        return repository.findByUrl(url.toString()).orElseThrow();
    }

    @Override
    public List<Link> findAll(Timestamp timeBeforeUpdates) {
        return repository.findAllLinks();
    }

    @Override
    public void setCurrentUpdateTime(List<Long> ids) {
        ids.forEach(
            link -> repository.setNewUpdateTime(link,
                new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public boolean contains(URI url) {
        return getLinkByUrl(url).getUrl() != null;
    }
}
