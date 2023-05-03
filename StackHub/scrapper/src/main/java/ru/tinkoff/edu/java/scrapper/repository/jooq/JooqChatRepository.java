package ru.tinkoff.edu.java.scrapper.repository.jooq;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.Tables;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;

@Repository
@RequiredArgsConstructor
public class JooqChatRepository implements ChatRepository {

    private final DSLContext dslContext;

    @Override
    public void removeByChatId(long chatId) {
        dslContext.deleteFrom(Tables.CHAT).where(Tables.CHAT.TRACK_ID.eq(chatId)).execute();
    }

    @Override
    public void add(long chatId, long linkId) {
        dslContext.insertInto(Tables.CHAT, Tables.CHAT.CHAT_ID, Tables.CHAT.LINK_ID)
            .values(chatId, linkId).execute();
    }

    @Override
    public void removeByTrackId(long trackId) {
        dslContext.deleteFrom(Tables.CHAT).where(Tables.CHAT.TRACK_ID.eq(trackId)).execute();
    }

    @Override
    public List<Chat> findAllChats() {
        return dslContext.selectFrom(Tables.CHAT).fetchInto(Chat.class);
    }

    @Override
    public List<Chat> findByChatId(long chatId) {
        return dslContext.selectFrom(Tables.CHAT).where(Tables.CHAT.CHAT_ID.eq(chatId))
            .fetchInto(Chat.class);
    }
}
