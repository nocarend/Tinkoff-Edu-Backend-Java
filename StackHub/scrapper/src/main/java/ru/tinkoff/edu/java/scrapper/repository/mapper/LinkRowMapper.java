package ru.tinkoff.edu.java.scrapper.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.model.Link;

@Component
public class LinkRowMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return (new Link().setId(
                rs.getLong("id"))
            .setUrl(rs.getString("url"))
            .setUpdatedAt(rs.getTimestamp("updated_at")));
    }
}
