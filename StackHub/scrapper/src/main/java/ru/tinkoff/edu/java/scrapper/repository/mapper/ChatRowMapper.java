package ru.tinkoff.edu.java.scrapper.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.model.Chat;

@Component
public class ChatRowMapper implements
    RowMapper<Chat> {

    @Override
    public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return (new Chat()
            .setTrackId(rs.getLong("track_id"))
            .setChatId(rs.getLong("chat_id"))
            .setLinkId(rs.getLong("link_id")));
    }
}
