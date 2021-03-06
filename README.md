# review-point

## 개발환경
- Spring Boot
- Java 8
- JPA
- MySQL 5.7

## DDL
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


## API

- 포인트 적립
```http
# 기본 점수 적립
PUT /api/points/{userId}/increment
# 기본 점수 차감
PUT /api/points/{userId}/decrement
# 보너스 점수 적립
PUT /api/points/{userId}/increment/bonus
# 보너스 점수 차감
PUT /api/points/{userId}/decrement/bonus

# RequestBody
{
    "amount": 1,
    "contentType": "REVIEW",
    "contentId": "240a0658-dc5f-4878-9381-ebb7b2667772",
}

# ResponseBody
{
    "point": 123,
    "bonus": 45
}
```

- 포인트 조회
```http
GET /api/points/{userId}

# ResponseBody
{
    "point": 123,
    "bonus": 45
}
```

## 기타
제출한 코드는 동작 테스트가 되지 않았고 컴파일만 됩니다. 참고 부탁드립니다.
