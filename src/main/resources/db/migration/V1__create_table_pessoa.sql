CREATE TABLE IF NOT EXISTS pessoa(
    pessoa_id serial primary key,
    nome varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    documento varchar(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    senha varchar(50) NOT NULL,
    data_cadastro DATE NOT NULL
);