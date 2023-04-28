package ru.tinkoff.edu.java.scrapper.repository.mapper;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.repository.dto.LinkRepositoryResponse;

@Component
public class LinkRowMapper implements RowMapper<LinkRepositoryResponse> {

    @Override
    public LinkRepositoryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.next()) {
            return (new LinkRepositoryResponse(
                rs.getLong("id"),
                URI.create(rs.getString("url")),
                rs.getTimestamp("updated_at")));
        }
        return null;
    }
}
