-- Inserindo dados na tabela Bairro
INSERT INTO bairro (nome_bairro) VALUES
                                     ('Centro'),
                                     ('Jardins'),
                                     ('Barra da Tijuca'),
                                     ('Savassi'),
                                     ('Praia do Canto');

-- Inserindo dados na tabela Logradouro
INSERT INTO logradouro (nome_logradouro, tipo_logradouro_sigla_tipo_logradouro) VALUES
                                                                                    ('Paulista', 'AV'),
                                                                                    ('Presidente Vargas', 'AV'),
                                                                                    ('Sete de Setembro', 'R'),
                                                                                    ('Bento Gonçalves', 'R'),
                                                                                    ('Riachuelo', 'R');

-- Inserindo dados na tabela Endereco
-- Assumindo que os IDs das tabelas relacionadas (bairro, cidade, logradouro) estão corretos
INSERT INTO endereco (bairro_id, cidade_id_cidade, logradouro_id, cep) VALUES
                                                                           (1, 1, 1, '01001-000'),
                                                                           (3, 2, 2, '22793-000'),
                                                                           (4, 3, 3, '30140-000'),
                                                                           (5, 4, 4, '29050-000');

-- Inserindo dados na tabela Banco
INSERT INTO banco (nome) VALUES
                             ('Banco do Brasil'),
                             ('Caixa Econômica Federal'),
                             ('Bradesco'),
                             ('Itaú'),
                             ('Santander');

-- Inserindo dados na tabela Agencia
-- Assumindo que os IDs das tabelas relacionadas (endereco e banco) estão corretos
INSERT INTO agencia (endereco_id, banco_id, complemento_endereco, nome_agencia) VALUES
                                                                                    (1, 1, 'Próximo ao centro', 'Agência Central São Paulo'),
                                                                                    (2, 2, 'Próxima ao Shopping', 'Agência Barra da Tijuca'),
                                                                                    (3, 3, 'Próxima à Praça da Liberdade', 'Agência Belo Horizonte'),
                                                                                    (4, 4, 'Próximo à Catedral', 'Agência Vitória Centro');
