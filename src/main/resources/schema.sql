create table if not exists candy (
    id bigint,
    name varchar(125),
    price decimal(12,4)
);

create table if not exists candy_delivery (
    candy_id bigint,
    delivery_id bigint
);