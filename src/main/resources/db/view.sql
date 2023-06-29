drop view if exists position_team_player;

create view position_team_player
as select pos.description as position, pl.name as name, team.name as team_name
   from player as pl, position_t as pos, team
   where pl.position = pos.abbreviation and pl.team_id = team.id
   order by team_name, pos.id;

select * from position_team_player;