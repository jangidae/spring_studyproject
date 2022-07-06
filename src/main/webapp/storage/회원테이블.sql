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


insert into tb_user values('garam0002', '�谡��', 'garam0002@gmail.com', 'q456', '010-4615-4324', '����Ư���� ���α� ��ô��', 'H', 834);
insert into tb_user(userid, uname, email, password, phone, address, join_route)
values('dohyun0003', '�赵��', 'dohyun0003@gmail.com', 'h987', '010-4688-4885', '����Ư���� �������� ��굿', 'K');
insert into tb_user(userid, uname, email, password, phone, address, join_route)
values('choi0019', '�ֽÿ�', 'choi0019@gmail.com', 'i222', '010-8591-0123', '�뱸������ �޼��� ��', 'K');

commit;

select * from tb_user;

drop table tb_user;