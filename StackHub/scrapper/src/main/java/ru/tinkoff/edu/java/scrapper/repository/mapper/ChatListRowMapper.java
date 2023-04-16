package ru.tinkoff.edu.java.scrapper.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.repository.dto.ChatRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.repository.dto.ListChatsRepositoryResponse;

@Component
public class ChatListRowMapper implements RowMapper<ListChatsRepositoryResponse> {

    @Override
    public ListChatsRepositoryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<ChatRepositoryResponse> tracks = new ArrayList<>();
        while (rs.next()) {
            tracks.add(new ChatRepositoryResponse(
                rs.getLong("track_id"),
                rs.getLong("chat_id"),
                rs.getLong("link_id")));
        }
        return new ListChatsRepositoryResponse(tracks);
    }
}
