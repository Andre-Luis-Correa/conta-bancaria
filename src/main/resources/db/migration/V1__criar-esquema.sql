-- Criação de tabelas
CREATE TABLE agencia (
                         banco_id BIGINT,
                         endereco_id BIGINT,
                         codigo_agencia VARCHAR(255) NOT NULL,
                         complemento_endereco VARCHAR(255),
                         nome_agencia VARCHAR(255),
                         numero_endereco VARCHAR(255),
                         PRIMARY KEY (codigo_agencia)
) ENGINE=InnoDB;

CREATE TABLE bairro (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nome_bairro VARCHAR(255),
                        PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE banco (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       nome VARCHAR(255),
                       PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE cidade (
                        id_cidade BIGINT NOT NULL AUTO_INCREMENT,
                        nome_cidade VARCHAR(255),
                        unidade_federativa_siglauf VARCHAR(255),
                        PRIMARY KEY (id_cidade)
) ENGINE=InnoDB;

CREATE TABLE cliente (
                         endereco_id BIGINT,
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         complemento_endereco VARCHAR(255),
                         cpf_cliente VARCHAR(255),
                         nome_cliente VARCHAR(255),
                         numero_endereco VARCHAR(255),
                         PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE conta_bancaria (
                                data_abertura_conta_bancaria DATE,
                                saldo_atua_conta_bancaria FLOAT(53),
                                cliente_id BIGINT,
                                agencia_codigo_agencia VARCHAR(255),
                                numero_conta_bancaria VARCHAR(255) NOT NULL,
                                tipo_conta_bancaria_sigla_tipo_conta_bancaria VARCHAR(255),
                                PRIMARY KEY (numero_conta_bancaria)
) ENGINE=InnoDB;

CREATE TABLE ddd (
                     numeroddd VARCHAR(3),
                     PRIMARY KEY (numeroddd)
) ENGINE=InnoDB;

CREATE TABLE ddi (
                     numeroddi VARCHAR(3),
                     PRIMARY KEY (numeroddi)
) ENGINE=InnoDB;

CREATE TABLE email (
                       cliente_id BIGINT,
                       email VARCHAR(255) NOT NULL,
                       PRIMARY KEY (email)
) ENGINE=InnoDB;

CREATE TABLE endereco (
                          bairro_id BIGINT,
                          cidade_id_cidade BIGINT,
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          logradouro_id BIGINT,
                          cep VARCHAR(255),
                          PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE logradouro (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            nome_logradouro VARCHAR(255),
                            tipo_logradouro_sigla_tipo_logradouro VARCHAR(255),
                            PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE telefone (
                          cliente_id BIGINT,
                          numero_telefone VARCHAR(255),
                          numeroddd_numeroddd VARCHAR(3),
                          numeroddi_numeroddi VARCHAR(3),
                          PRIMARY KEY (numero_telefone)
) ENGINE=InnoDB;

CREATE TABLE tipo_conta_bancaria (
                                     nome_tipo_conta_bancaria VARCHAR(255),
                                     sigla_tipo_conta_bancaria VARCHAR(255) NOT NULL,
                                     PRIMARY KEY (sigla_tipo_conta_bancaria)
) ENGINE=InnoDB;

CREATE TABLE tipo_logradouro (
                                 nome_tipo_logradouro VARCHAR(255),
                                 sigla_tipo_logradouro VARCHAR(255) NOT NULL,
                                 PRIMARY KEY (sigla_tipo_logradouro)
) ENGINE=InnoDB;

CREATE TABLE tipo_transacao (
                                codigo_tipo_transacao VARCHAR(255) NOT NULL,
                                nome_tipo_transacao VARCHAR(255),
                                descricao_padrao_transacao ENUM ('CREDITO', 'DEBITO'),
                                PRIMARY KEY (codigo_tipo_transacao)
) ENGINE=InnoDB;

CREATE TABLE transacao (
                           data_transacao DATE,
                           valor_transacao FLOAT(53),
                           codigo_transacao VARCHAR(255) NOT NULL,
                           conta_bancaria_numero_conta_bancaria VARCHAR(255),
                           observacao VARCHAR(255),
                           tipo_transacao_codigo_tipo_transacao VARCHAR(255),
                           PRIMARY KEY (codigo_transacao)
) ENGINE=InnoDB;

CREATE TABLE unidade_federativa (
                                    nomeuf VARCHAR(255),
                                    siglauf VARCHAR(255) NOT NULL,
                                    PRIMARY KEY (siglauf)
) ENGINE=InnoDB;

-- Adicionando Constraints
ALTER TABLE agencia
    ADD CONSTRAINT UKa6wc1pf3fom4s2p5o96co7i5f UNIQUE (endereco_id),
    ADD CONSTRAINT FKitd0jcpl1a6fqcn5acemi1e6l FOREIGN KEY (banco_id) REFERENCES banco (id),
    ADD CONSTRAINT FKgtow2yt2utgj9pjani4gyi56w FOREIGN KEY (endereco_id) REFERENCES endereco (id);

ALTER TABLE cidade
    ADD CONSTRAINT FKv3kuc0hjn2j0368gjj8v00wv FOREIGN KEY (unidade_federativa_siglauf) REFERENCES unidade_federativa (siglauf);

ALTER TABLE cliente
    ADD CONSTRAINT FK64nr9yt889by5lufr1boo5i4s FOREIGN KEY (endereco_id) REFERENCES endereco (id);

ALTER TABLE conta_bancaria
    ADD CONSTRAINT FKcbead9c9624920qw6kss1gs1u FOREIGN KEY (agencia_codigo_agencia) REFERENCES agencia (codigo_agencia),
    ADD CONSTRAINT FK3wsnqgpls15ru9qbikgvfymvd FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    ADD CONSTRAINT FK6dcodi40uvn09ljqpxy3a1onp FOREIGN KEY (tipo_conta_bancaria_sigla_tipo_conta_bancaria) REFERENCES tipo_conta_bancaria (sigla_tipo_conta_bancaria);

ALTER TABLE email
    ADD CONSTRAINT FKmv73jqkep03fr08gsysyrdp6u FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE endereco
    ADD CONSTRAINT FK301jhunwxfhwk7bdftpq6eeud FOREIGN KEY (bairro_id) REFERENCES bairro (id),
    ADD CONSTRAINT FKg3q76ev7ibcl94ta06sfbb3kg FOREIGN KEY (cidade_id_cidade) REFERENCES cidade (id_cidade),
    ADD CONSTRAINT FKfqhmmh8wqua4ly9mbrat8tnnq FOREIGN KEY (logradouro_id) REFERENCES logradouro (id);

ALTER TABLE logradouro
    ADD CONSTRAINT FKah6d2fljbmu846oq9ylmqyol6 FOREIGN KEY (tipo_logradouro_sigla_tipo_logradouro) REFERENCES tipo_logradouro (sigla_tipo_logradouro);

ALTER TABLE telefone
    ADD CONSTRAINT FK8aafha0njkoyoe3kvrwsy3g8u FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    ADD CONSTRAINT FK8wjuagb6tv5rlye75awcvf28f FOREIGN KEY (numeroddd_numeroddd) REFERENCES ddd (numeroddd),
    ADD CONSTRAINT FKq4skmp5flso4qltmsnfjgcqka FOREIGN KEY (numeroddi_numeroddi) REFERENCES ddi (numeroddi);

ALTER TABLE transacao
    ADD CONSTRAINT FKjq6lxgqrph63jbl9wx4dd0ktu FOREIGN KEY (conta_bancaria_numero_conta_bancaria) REFERENCES conta_bancaria (numero_conta_bancaria),
    ADD CONSTRAINT FKd42ae25bdqmo38voayffne7t4 FOREIGN KEY (tipo_transacao_codigo_tipo_transacao) REFERENCES tipo_transacao (codigo_tipo_transacao);
