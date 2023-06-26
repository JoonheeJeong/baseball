select * from stadium;
select * from team;
select * from player;

insert into stadium (name) values ('대전 한화생명 이글스파크');
insert into stadium (name) values ('잠실종합운동장');
insert into stadium (name) values ('사직 야구장');

insert into TEAM (STADIUM_ID, NAME) values ( 1, '한화 이글스' );
insert into TEAM (STADIUM_ID, NAME) values ( 2, '두산 베어스' );
insert into TEAM (STADIUM_ID, NAME) values ( 3, '롯데 자이언츠' );

insert into player (team_id, name, position) values (1, '이태양', '투수');
insert into player (team_id, name, position) values (1, '최재훈', '포수');
insert into player (team_id, name, position) values (1, '김인환', '1루수');
insert into player (team_id, name, position) values (1, '정은원', '2루수');
insert into player (team_id, name, position) values (1, '오선진', '유격수');
insert into player (team_id, name, position) values (1, '노시환', '3루수');
insert into player (team_id, name, position) values (1, '채은성', '좌익수');
insert into player (team_id, name, position) values (1, '오그레디', '중견수');
insert into player (team_id, name, position) values (1, '이진영', '우익수');
insert into player (team_id, name, position) values (2, '김선우', '투수');
insert into player (team_id, name, position) values (2, '양의지', '포수');
insert into player (team_id, name, position) values (2, '양석환', '1루수');
insert into player (team_id, name, position) values (2, '김재호', '2루수');
insert into player (team_id, name, position) values (2, '전민재', '유격수');
insert into player (team_id, name, position) values (2, '허경민', '3루수');
insert into player (team_id, name, position) values (2, '김재환', '좌익수');
insert into player (team_id, name, position) values (2, '정수빈', '중견수');
insert into player (team_id, name, position) values (2, '조수행', '우익수');
insert into player (team_id, name, position) values (3, '댄 스트레일리', '투수');
insert into player (team_id, name, position) values (3, '유강남', '포수');
insert into player (team_id, name, position) values (3, '고승민', '1루수');
insert into player (team_id, name, position) values (3, '안치홍', '2루수');
insert into player (team_id, name, position) values (3, '노진혁', '유격수');
insert into player (team_id, name, position) values (3, '한동희', '3루수');
insert into player (team_id, name, position) values (3, '황성빈', '좌익수');
insert into player (team_id, name, position) values (3, '안권수', '중견수');
insert into player (team_id, name, position) values (3, '잭 렉스', '우익수');