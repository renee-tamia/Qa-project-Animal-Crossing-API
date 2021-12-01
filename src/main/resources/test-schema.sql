drop table if exists ac_characters CASCADE;
create table ac_characters 
(
	id integer auto_increment, 
	birthday varchar(255), 
	catchphrase varchar(255), 
	hobbies varchar(255), 
	name varchar(255), 
	personality varchar(255), 
	species varchar(255), 
	primary key (id)
);