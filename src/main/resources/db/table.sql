drop table if exists out_player;
drop table if exists player cascade;
drop table if exists position_t cascade;
drop table if exists team cascade;
drop table if exists stadium cascade;

create table stadium (
    id          bigint          auto_increment,
    name        varchar(32)     not null unique,
    created_at  timestamp       not null default now(),
    primary key (id)
);

create table team (
    id          bigint          auto_increment,
    stadium_id  bigint,
    name        varchar(32)     not null unique,
    created_at  timestamp       not null default now(),
    primary key (id),
    foreign key (stadium_id) references stadium(id)
);

create table position_t (
    id              int2,
    name            varchar(16) character set `binary` collate `binary` not null unique,
    abbreviation    varchar(3)  character set `binary` collate `binary` not null unique,
    description     varchar(4)  not null unique
);

select * from position_t;

create table player (
    id          bigint          auto_increment,
    team_id     bigint,
    name        varchar(32)     not null,
    position    varchar(3)      character set `binary` collate `binary` not null,
    created_at  timestamp       not null default now(),
    primary key (id),
    foreign key(team_id) references team(id),
    foreign key (position) references position_t(abbreviation),
    constraint team_position unique (team_id, position)
);

create table out_player (
    id          bigint          auto_increment,
    player_id   bigint          not null,
    reason      varchar(16),
    created_at  timestamp       not null default now(),
    primary key (id),
    foreign key(player_id) references player(id)
);
