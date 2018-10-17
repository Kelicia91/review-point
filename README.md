# review-point

### DDL (MySQL 5.7)
```mysql
-- 테이블 생성
create table photo (
    id binary(255) not null, 
    created_at datetime not null, 
    updated_at datetime, 
    primary key (id)
)

create table place (
    id binary(255) not null, 
    created_at datetime not null, 
    updated_at datetime, 
    primary key (id)
)

create table point (
    created_at datetime not null, 
    updated_at datetime, 
    bonus integer, 
    point integer, 
    user_id binary(255) not null, 
    primary key (user_id)
)

create table point_history (
    id binary(255) not null, 
    created_at datetime not null, 
    updated_at datetime, 
    action integer, 
    amount integer, 
    content_id binary(255), 
    content_type varchar(255), 
    type varchar(255), 
    user_id binary(255), 
    primary key (id)
)

create table review (
    id binary(255) not null, 
    created_at datetime not null, 
    updated_at datetime, 
    content varchar(255) not null, 
    place_id binary(255), 
    writer_id binary(255), 
    primary key (id)
)

create table review_attached_photos (
    review_id binary(255) not null, 
    attached_photos_id binary(255) not null
)

create table user (
    id binary(255) not null, 
    created_at datetime not null, 
    updated_at datetime, 
    primary key (id)
)

-- 인덱스 생성
create index IDX_POINT_HISTORY_USER on point_history (user_id)
create index IDX_REVIEW_WRITER on review (writer_id)
create index IDX_REVIEW_PLACE on review (place_id)

-- 외래키 설정
alter table review_attached_photos add constraint UK_te4gj2gujaxcyfve0ery32bm1 unique (attached_photos_id)
alter table point add constraint fk_point_user foreign key (user_id) references user (id)
alter table point_history add constraint fk_point_history_to_user foreign key (user_id) references user (id)
alter table review add constraint fk_review_place foreign key (place_id) references place (id)
alter table review add constraint fk_review_writer foreign key (writer_id) references user (id)
alter table review_attached_photos add constraint FK9l09dnc6bc1m9a4kifttdjvcq foreign key (attached_photos_id) references photo (id)
alter table review_attached_photos add constraint FK5s8wmfnwaynx77eerdh5iqftp foreign key (review_id) references review (id)
```
