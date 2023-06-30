drop procedure if exists select_position_team_player;

delimiter //

create procedure select_position_team_player()
begin
    set @mysql = null;

    select @mysql := group_concat(distinct concat(
        'max(case when team_name = ''', team_name, ''' then name else ''X'' end) as "', team_name, '"')
        separator ', ') as columns
    from position_team_player;

#     select @mysql;

    set @mysql =
        concat(
            'select position, ',
            @mysql,
            ' from position_team_player '
            'group by position');

#     select @mysql;

    prepare stmt from @mysql;
    execute stmt;
    deallocate prepare stmt;
end
//

delimiter ;

call select_position_team_player;
