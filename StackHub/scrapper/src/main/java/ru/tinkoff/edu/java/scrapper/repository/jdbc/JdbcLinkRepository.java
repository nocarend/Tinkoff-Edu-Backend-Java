package ru.tinkoff.edu.java.scrapper.repository.jdbc;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.linkparser.ExternalParser;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkRowMapper;

@Repository
@RequiredArgsConstructor
public class JdbcLinkRepository implements LinkRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LinkRowMapper linkRowMapper;

    @Override
    @Transactional
    public void add(String url, Timestamp updatedAt) {
        var result = new ExternalParser(url).parse();
        if (result == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> params = new HashMap<>(
            Map.of("url", url, "updated_at", updatedAt));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        if (findByUrl(url).isPresent()) {
            return;
        }
        jdbcTemplate.update("insert into link(url, updated_at) values (:url, :updated_at)",
            paramSource);
    }

    @Override
    @Transactional
    public void remove(String url) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", url));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("delete from link where url = :url", paramSource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findAllLinks() {
        return jdbcTemplate.query("select * from link", new HashMap<>(),
            linkRowMapper).stream().filter(Objects::nonNull).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Link> findById(long id) {
        Map<String, Object> params = new HashMap<>(
            Map.of("id", id));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from link where id = :id",
            paramSource, linkRowMapper));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Link> findByUrl(String url) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", url));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        List<Link> result;
        return Optional.ofNullable((result =
            jdbcTemplate.query("select * from link where url = :url",
                paramSource, linkRowMapper)).size() > 0 ? result.get(0) : null);
    }

    @Override
    @Transactional
    public void setNewUpdateTime(long id, Timestamp updatedAt) {
        Map<String, Object> params = new HashMap<>(
            Map.of("id", id, "updated_at", updatedAt));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update(
            "update link set updated_at = :updated_at where id = :id", paramSource);
    }
}
