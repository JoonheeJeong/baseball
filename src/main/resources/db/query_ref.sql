select * from team;
select id, name from team order by id;

select distinct position from player;
select position, name from player;
select position, name from player where team_id = 1;

select pos.description as position, pl.name
from player as pl, position_t as pos
where pl.position = pos.abbreviation
order by pos.id;

select pos.description as position,
       group_concat(IFNULL(pl.name, 'X') order by team_id) as name_for_each_team
from player as pl, position_t as pos
where pl.position = pos.abbreviation
group by pos.id;

#     투수: max( 김선우, X, X, X )
# 포수 max(양의지, 유강남, 최재훈)
# 포수 max(x, x, x)

select position,
       max(case when team_name = '두산 베어스' then name else 'X' end) as "두산 베어스",
       max(case when team_name = '패스트캠퍼스 백엔드 부트캠프 5기' then name else 'X' end) as "패스트캠퍼스 백엔드 부트캠프 5기",
       max(case when team_name = '한화 이글스' then name else 'X' end) as "한화 이글스"
from position_team_player
group by position;

with combined_teams as (
    select player.position, player.name, team.name as team_name
    from player
             join team on player.team_id = team.id
)
select
    position,
    max(case when team_name = '한화 이글스' then name end) as "한화 이글스",
    max(case when team_name = '두산 베어스' then name end) as "두산 베어스",
    max(case when team_name = '롯데 자이언츠' then name end) as "롯데 자이언츠"
from combined_teams
group by position;

