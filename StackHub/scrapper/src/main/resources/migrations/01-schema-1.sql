create sequence if not exists link_seq start 1 increment 1;

create table if not exists link
(
    id  bigint primary key,
    url text unique not null
);

create table if not exists chat
(
    id      bigint not null,
    link_id bigint not null,
    foreign key (link_id) references link (id)
);