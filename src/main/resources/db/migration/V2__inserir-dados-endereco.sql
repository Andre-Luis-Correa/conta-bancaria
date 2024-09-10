-- Inserindo dados na tabela UnidadeFederativa
INSERT INTO unidade_federativa (siglauf, nomeuf) VALUES
                                                     ('SP', 'São Paulo'),
                                                     ('RJ', 'Rio de Janeiro'),
                                                     ('MG', 'Minas Gerais'),
                                                     ('ES', 'Espírito Santo');

-- Inserindo dados na tabela Cidade
INSERT INTO cidade (nome_cidade, unidade_federativa_siglauf) VALUES
                                                                 ('São Paulo', 'SP'),
                                                                 ('Rio de Janeiro', 'RJ'),
                                                                 ('Belo Horizonte', 'MG'),
                                                                 ('Vitória', 'ES');

-- Inserindo dados na tabela TipoLogradouro
INSERT INTO tipo_logradouro (sigla_tipo_logradouro, nome_tipo_logradouro) VALUES
                                                                              ('R', 'Rua'),
                                                                              ('AV', 'Avenida'),
                                                                              ('TRV', 'Travessa'),
                                                                              ('AL', 'Alameda');

-- Inserindo dados na tabela Logradouro
INSERT INTO logradouro (nome_logradouro, tipo_logradouro_sigla_tipo_logradouro) VALUES
                                                                                    ('Paulista', 'AV'),
                                                                                    ('Presidente Vargas', 'AV'),
                                                                                    ('Sete de Setembro', 'R'),
                                                                                    ('Bento Gonçalves', 'R');