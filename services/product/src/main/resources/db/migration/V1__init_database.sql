create table if not exists category
(
    id integer not null primary key,
    description varchar(255),
    name varchar(255) not null
);

create table if not exists product
(
    id integer not null primary key,
    name varchar(255),
    description varchar(255),
    price numeric(20, 2),
    available_quantity double precision not null,
    category_id integer constraint fk_category
        references category(id)
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
