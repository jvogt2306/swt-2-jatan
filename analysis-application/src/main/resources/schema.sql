

drop table if exists user_information;

create table user_information {
  id int auto_increment primary key
  firstName varchar(255) not null;
  lastName varchar(255) not null;
  location varchar(5) not null;
}
