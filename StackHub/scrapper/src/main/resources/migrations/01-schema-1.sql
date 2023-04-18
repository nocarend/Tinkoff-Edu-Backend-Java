create sequence if not exists link_seq start with 1 increment by 1;
create sequence if not exists chat_seq start with 1 increment by 1;

create table if not exists link
(
    id         bigint default nextval('link_seq'),
    url        text unique not null,
    updated_at timestamp   not null,
    constraint chat_pk primary key (id)
);

create table if not exists chat
(
    track_id bigint default nextval('chat_seq'),
    chat_id  bigint not null,
    link_id  bigint not null,
    foreign key (link_id) references link (id),
    constraint track_pk primary key (track_id)
);