create table products (
                          id                      bigserial primary key,
                          title                   varchar(100),
                          category                varchar(100),
                          price                   bigint,
                          created_at              timestamp default current_timestamp,
                          updated_at              timestamp default current_timestamp
);