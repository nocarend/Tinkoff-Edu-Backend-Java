package ru.tinkoff.edu.java.scrapper.repository;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    private final JdbcTemplate jdbcTemplate;
    private final LinkListRowMapper linkListRowMapper;
    private final LinkRowMapper linkRowMapper;

    @Transactional
    public void add(AddLinkRequest linkRequest) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", linkRequest.url().toString(), "updated_at", linkRequest.updatedAt()));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
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
        return jdbcTemplate.queryForObject("select * from link", linkListRowMapper);
    }

    public LinkRepositoryResponse findById(long id) {
        Map<String, Object> params = new HashMap<>(
            Map.of("id", id));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return jdbcTemplate.queryForObject("select * from link where id = :id",
            new SqlParameterSource[]{paramSource}, linkRowMapper);
    }

    public LinkRepositoryResponse findByURL(URI url) {
        Map<String, Object> params = new HashMap<>(
            Map.of("url", url.toString()));
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        return jdbcTemplate.queryForObject("select * from link where url = :url",
            new SqlParameterSource[]{paramSource}, linkRowMapper);
    }
}
