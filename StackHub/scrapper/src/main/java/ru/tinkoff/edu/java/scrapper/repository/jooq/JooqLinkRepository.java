package ru.tinkoff.edu.java.scrapper.repository.jooq;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.linkparser.ExternalParser;
import ru.tinkoff.edu.java.scrapper.domain.jooq.Tables;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

@Repository
@RequiredArgsConstructor
public class JooqLinkRepository implements LinkRepository {

    private final DSLContext dslContext;

    @Override
    public void add(String url, Timestamp updatedAt) {
        var result = new ExternalParser(url).parse();
        if (result == null) {
            throw new IllegalArgumentException();
        }
        if (findByUrl(url).isPresent()) {
            return;
        }
        dslContext.insertInto(Tables.LINK, Tables.LINK.URL, Tables.LINK.UPDATED_AT)
            .values(url, updatedAt.toLocalDateTime()).execute();
    }

    @Override
    public void remove(String url) {
        dslContext.deleteFrom(Tables.LINK).where(Tables.LINK.URL.eq(url)).execute();
    }

    @Override
    public List<Link> findAllLinks() {
        return dslContext.selectFrom(Tables.LINK).fetchInto(Link.class);
    }

    @Override
    public Optional<Link> findById(long id) {
        var row = dslContext.selectFrom(Tables.LINK).where(Tables.LINK.ID.eq(id)).fetchSingle();
        return Optional.ofNullable(new Link()
            .setUrl(URI.create(row.getUrl()))
            .setId(row.getId())
            .setUpdatedAt(Timestamp.valueOf(row.getUpdatedAt())));
    }

    @Override
    public Optional<Link> findByUrl(String url) {
        var row = dslContext.selectFrom(Tables.LINK).where(Tables.LINK.URL.eq(url)).fetchSingle();
        return Optional.ofNullable(new Link()
            .setUrl(URI.create(row.getUrl()))
            .setId(row.getId())
            .setUpdatedAt(Timestamp.valueOf(row.getUpdatedAt())));
    }

    @Override
    public void setNewUpdateTime(long id, Timestamp updatedAt) {
        dslContext.update(Tables.LINK).set(Tables.LINK.UPDATED_AT, updatedAt.toLocalDateTime())
            .where(Tables.LINK.ID.eq(id)).execute();
    }
}
