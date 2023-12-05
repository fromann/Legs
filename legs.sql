create database legs
    default character set utf8mb3
    default collate utf8mb3_unicode_ci;

create table orders
(
    id       varchar(50) not null
        primary key,
    task     varchar(50) null,
    sender   varchar(50) null,
    receiver varchar(50) null,
    date     date        null,
    state    int         null comment '订单',
    price    varchar(50) null
);

create table user
(
    username varchar(50) not null
        primary key,
    password varchar(50) null,
    name     varchar(50) null,
    phone    varchar(50) null comment '用户表',
    type     int         null
);

use legs;

insert into legs.orders (id, task, sender, receiver, date, state, price)
values  ('0a8ea92b-eca8-428f-a429-6ddfd0082b2b', '买一朵花', 'sender', 'receiver', '2023-11-10', 1, '100'),
        ('384a5361-b006-4e4d-9e37-4936fea2e921', '考试', 'sender', 'receiver', '2023-11-10', 1, '50'),
        ('4125b79b-9dc2-490c-83e2-844fde24362c', '学习', 'sender', '', '2023-11-10', 1, '1000'),
        ('4216e05d-4c45-4a69-b7f6-3b5fac9ef53f', '谈恋爱', 'sender', '2108710210', '2023-11-10', 1, '100'),
        ('82e6762e-2792-420d-b810-1251b83a36a1', '金铲铲', 'sender', '', '2023-11-10', 0, '200'),
        ('cbc63d2d-fd60-4cf8-b014-41d9800022c3', '谈恋爱', 'sender', '', '2023-11-10', 0, '100'),

insert into legs.user (username, password, name, phone, type)
values  ('admin', 'admin', '管理员', '11111', 0),
        ('receiver', 'receiver', '跑腿小哥', '121231', 2),
        ('sender', 'sender', '普通用户', '12312313', 1),