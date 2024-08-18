create schema if not exists cajueiro;

create table cajueiro.accounts (
    id bigserial,
    food_balance numeric(15, 2) not null,
    meal_balance numeric(15, 2) not null,
    cash_balance numeric(15, 2) not null,

    constraint pk_account primary key (id)
);
