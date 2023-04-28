package ru.tinkoff.edu.java.scrapper.repository.mapper;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.repository.dto.LinkRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListChatsRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListLinksRepositoryResponse;

@Component
public class LinkListRowMapper implements RowMapper<ListLinksRepositoryResponse> {

    @Override
    public ListLinksRepositoryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<LinkRepositoryResponse> links = new ArrayList<>();
        while (rs.next()) {
            links.add(new LinkRepositoryResponse(
                rs.getLong("id"),
                URI.create(rs.getString("url")),
                rs.getTimestamp("updated_at")));
        }
        return new ListLinksRepositoryResponse(links);
    }
}
