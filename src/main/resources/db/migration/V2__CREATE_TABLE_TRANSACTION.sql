create table cajueiro.transactions (
    id bigserial,
    account_id varchar(255) not null,
    amount numeric(15, 2) not null,
    merchant varchar(255) not null,
    mcc varchar(4) not null,

    constraint pk_transaction primary key (id)
);
