package ru.tinkoff.edu.java.scrapper.service.jdbc;


import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final LinkRepository repository;


    @Override
    public Link getLinkById(long id) {
        var link = repository.findById(id).get(0).url();
        return new Link().setUrl(link);
    }

    @Override
    public Link getLinkByUrl(URI url) {
        var linkList = repository.findByUrl(url);
        if (linkList.isEmpty()) {
            return new Link().setUrl(null);
        }
        return new Link().setId(linkList.get(0).id()).setUrl(linkList.get(0).url());
    }

    @Override
    public Link add(URI url) {
        repository.add(new AddLinkRequest(url, null));
        return new Link().setUrl(url);
    }

    @Override
    public Link remove(URI url) {
        repository.remove(url);
        return new Link().setUrl(url);
    }

    @Override
    public List<Link> findAll(Timestamp timeBeforeUpdates) {
        return repository.findAll().links().stream().map(
                response -> new Link().setUrl(response.url()).setId(response.id())
                    .setUpdatedAt(response.updatedAt()))
            .filter(link -> link.getUpdatedAt().before(timeBeforeUpdates)).toList();
    }

    @Override
    public void setCurrentUpdateTime(List<Long> links) {
        links.forEach(repository::setNewUpdateTime);
    }

    public boolean contains(URI url) {
        return getLinkByUrl(url).getUrl() != null;
    }
}
