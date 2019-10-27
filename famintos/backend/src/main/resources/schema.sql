CREATE TABLE restaurante ( 
   id BIGINT auto_increment NOT NULL, 
   nome VARCHAR(255) NOT NULL, 
   endereco VARCHAR(255) NOT NULL, 
   primary key(id)
);


CREATE TABLE pessoa ( 
   id BIGINT auto_increment NOT NULL, 
   nome VARCHAR(255) NOT NULL, 
   user_name VARCHAR(255) NOT NULL, 
   senha VARCHAR(255) NOT NULL, 
   primary key(id)
);


CREATE TABLE voto (
  id BIGINT auto_increment NOT NULL, 
  pessoa_id BIGINT NOT NULL,
  restaurante_id BIGINT NOT NULL,
  data DATE, 
  primary key(id)
);
