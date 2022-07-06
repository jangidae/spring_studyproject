create table tb_user (
        userid varchar(10) not null,
        uname varchar(50) not null,
        email varchar(100) not null unique,
        password varchar(15) not null,
        phone varchar(20) not null unique,
        address varchar(100) not null,
        join_route char(1) not null,
        point number default 0 not null,
        primary key(userid)
);


insert into tb_user values('garam0002', '김가람', 'garam0002@gmail.com', 'q456', '010-4615-4324', '서울특별시 구로구 고척동', 'H', 834);
insert into tb_user(userid, uname, email, password, phone, address, join_route)
values('dohyun0003', '김도현', 'dohyun0003@gmail.com', 'h987', '010-4688-4885', '서울특별시 영등포구 당산동', 'K');
insert into tb_user(userid, uname, email, password, phone, address, join_route)
values('choi0019', '최시우', 'choi0019@gmail.com', 'i222', '010-8591-0123', '대구광역시 달서구 대곡동', 'K');

commit;

select * from tb_user;

drop table tb_user;