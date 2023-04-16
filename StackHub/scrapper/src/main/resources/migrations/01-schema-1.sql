create sequence if not exists link_seq start 1 increment 1;
create sequence if not exists chat_seq start 1 increment 1;

create table if not exists link
(
    id         bigint primary key default nextval(link_seq),
    url        text unique not null,
    updated_at timestamp   not null
);

create table if not exists chat
(
    track_id bigint primary key default nextval(chat_seq),
    chat_id  bigint not null,
    link_id  bigint not null,
    foreign key (link_id) references link (id)
);