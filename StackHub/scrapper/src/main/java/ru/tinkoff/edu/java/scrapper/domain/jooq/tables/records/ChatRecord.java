/*
 * This file is generated by jOOQ.
 */

package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records;

import java.beans.ConstructorProperties;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ChatRecord extends UpdatableRecordImpl<ChatRecord> implements
    Record3<Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached ChatRecord
     */
    public ChatRecord() {
        super(Chat.CHAT);
    }

    /**
     * Create a detached, initialised ChatRecord
     */
    @ConstructorProperties({"trackId", "chatId", "linkId"})
    public ChatRecord(@NotNull Long trackId, @NotNull Long chatId, @NotNull Long linkId) {
        super(Chat.CHAT);

        setTrackId(trackId);
        setChatId(chatId);
        setLinkId(linkId);
    }

    /**
     * Create a detached, initialised ChatRecord
     */
    public ChatRecord(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.Chat value) {
        super(Chat.CHAT);

        if (value != null) {
            setTrackId(value.getTrackId());
            setChatId(value.getChatId());
            setLinkId(value.getLinkId());
        }
    }

    /**
     * Getter for <code>CHAT.TRACK_ID</code>.
     */
    @NotNull
    public Long getTrackId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>CHAT.TRACK_ID</code>.
     */
    public void setTrackId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>CHAT.CHAT_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getChatId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>CHAT.CHAT_ID</code>.
     */
    public void setChatId(@NotNull Long value) {
        set(1, value);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>CHAT.LINK_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getLinkId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>CHAT.LINK_ID</code>.
     */
    public void setLinkId(@NotNull Long value) {
        set(2, value);
    }

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    @Override
    @NotNull
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row3<Long, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Chat.CHAT.TRACK_ID;
    }

    @Override
    @NotNull
    public Field<Long> field2() {
        return Chat.CHAT.CHAT_ID;
    }

    @Override
    @NotNull
    public Field<Long> field3() {
        return Chat.CHAT.LINK_ID;
    }

    @Override
    @NotNull
    public Long component1() {
        return getTrackId();
    }

    @Override
    @NotNull
    public Long component2() {
        return getChatId();
    }

    @Override
    @NotNull
    public Long component3() {
        return getLinkId();
    }

    @Override
    @NotNull
    public Long value1() {
        return getTrackId();
    }

    @Override
    @NotNull
    public Long value2() {
        return getChatId();
    }

    @Override
    @NotNull
    public Long value3() {
        return getLinkId();
    }

    @Override
    @NotNull
    public ChatRecord value1(@NotNull Long value) {
        setTrackId(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public ChatRecord value2(@NotNull Long value) {
        setChatId(value);
        return this;
    }

    @Override
    @NotNull
    public ChatRecord value3(@NotNull Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public ChatRecord values(@NotNull Long value1, @NotNull Long value2, @NotNull Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }
}
