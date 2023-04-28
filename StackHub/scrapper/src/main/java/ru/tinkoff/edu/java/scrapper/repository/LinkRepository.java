package ru.tinkoff.edu.java.scrapper.repository;


import java.net.URI;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repository.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.repository.dto.LinkRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListLinksRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkListRowMapper;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkRowMapper;

@Repository
@RequiredArgsConstructor
public class LinkRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LinkListRowMapper linkListRowMapper;
    private final LinkRowMapper linkRowMapper;

    @Transactional
    public void add(AddLinkRequest linkRequest) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", linkRequest.url().toString(), "updated_at", linkRequest.updatedAt()));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        if (!findByUrl(linkRequest.url()).isEmpty()) {
            return;
        }
        jdbcTemplate.update("insert into link(url, updated_at) values (:url, :updated_at)",
            paramSource);
    }

    @Transactional
    public void remove(URI url) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", url.toString()));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update("delete from link where url = :url", paramSource);
    }

    public ListLinksRepositoryResponse findAll() {
        return jdbcTemplate.queryForObject("select * from link", new HashMap<>(),
            linkListRowMapper);
    }


    public List<LinkRepositoryResponse> findById(long id) {
        Map<String, Object> params = new HashMap<>(
            Map.of("id", id));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return jdbcTemplate.query("select * from link where id = :id",
            paramSource, linkRowMapper);
    }

    @Transactional
    public List<LinkRepositoryResponse> findByUrl(URI url) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", url.toString()));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return jdbcTemplate.query("select * from link where url = :url",
            paramSource, linkRowMapper);
    }

    public void setNewUpdateTime(long id) {
        Map<String, Object> params = new HashMap<>(
            Map.of("id", id, "updated_at", new Timestamp(System.currentTimeMillis())));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbcTemplate.update(
            "update link set updated_at = :updated_at where id = :id", paramSource);
    }
}
