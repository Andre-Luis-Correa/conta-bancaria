-- Inserir clientes
INSERT INTO cliente (endereco_id, id, complemento_endereco, cpf_cliente, nome_cliente, numero_endereco) VALUES
                                                                                                            (1, 1, 'Apto 101', '12345678901', 'João Silva', '100'),
                                                                                                            (2, 2, 'Casa', '23456789012', 'Maria Oliveira', '200'),
                                                                                                            (3, 3, 'Bloco B', '34567890123', 'Carlos Souza', '300');

-- Inserir contas bancárias para João Silva (Cliente 1)
INSERT INTO conta_bancaria (numero_conta_bancaria, agencia_codigo_agencia, cliente_id, tipo_conta_bancaria_sigla_tipo_conta_bancaria, data_abertura_conta_bancaria, saldo_atua_conta_bancaria) VALUES
                                                                                                                                                                                                   ('12345-6', '1709-x', 1, 'CC', '2024-01-01', 1000.00),
                                                                                                                                                                                                   ('12345-7', '1709-x', 1, 'CP', '2024-01-10', 2000.00);

-- Inserir contas bancárias para Maria Oliveira (Cliente 2)
INSERT INTO conta_bancaria (numero_conta_bancaria, agencia_codigo_agencia, cliente_id, tipo_conta_bancaria_sigla_tipo_conta_bancaria, data_abertura_conta_bancaria, saldo_atua_conta_bancaria) VALUES
                                                                                                                                                                                                   ('23456-7', '1710-x', 2, 'CC', '2024-01-05', 1500.00),
                                                                                                                                                                                                   ('23456-8', '1710-x', 2, 'PJ', '2024-01-20', 3000.00);

-- Inserir contas bancárias para Carlos Souza (Cliente 3)
INSERT INTO conta_bancaria (numero_conta_bancaria, agencia_codigo_agencia, cliente_id, tipo_conta_bancaria_sigla_tipo_conta_bancaria, data_abertura_conta_bancaria, saldo_atua_conta_bancaria) VALUES
                                                                                                                                                                                                   ('34567-8', '1711-x', 3, 'CC', '2024-01-15', 1800.00),
                                                                                                                                                                                                   ('34567-9', '1711-x', 3, 'CP', '2024-01-25', 2500.00);

-- Transações para as contas bancárias de João Silva (Cliente 1)
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T001', '2024-02-01', '12345-6', 'Recebimento PIX', 'PIX_REC', 300.00),
                                                                                                                                                                      ('T002', '2024-02-02', '12345-6', 'Depósito em conta', 'DEP', 500.00),
                                                                                                                                                                      ('T003', '2024-02-03', '12345-6', 'Saque realizado', 'SAQ', -200.00),
                                                                                                                                                                      ('T004', '2024-02-05', '12345-7', 'Transferência recebida', 'TRANSF', 600.00),
                                                                                                                                                                      ('T005', '2024-02-06', '12345-7', 'Pagamento de Boleto', 'PGTO_BOL', -100.00);

-- Atualizar saldo das contas bancárias de João Silva
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 300.00 WHERE numero_conta_bancaria = '12345-6';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 500.00 WHERE numero_conta_bancaria = '12345-6';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '12345-6';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 600.00 WHERE numero_conta_bancaria = '12345-7';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 100.00 WHERE numero_conta_bancaria = '12345-7';

-- Transações para as contas bancárias de Maria Oliveira (Cliente 2)
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T006', '2024-02-01', '23456-7', 'PIX enviado', 'PIX_ENV', -150.00),
                                                                                                                                                                      ('T007', '2024-02-02', '23456-7', 'Saque realizado', 'SAQ', -200.00),
                                                                                                                                                                      ('T008', '2024-02-03', '23456-8', 'Recebimento de Crédito', 'REC_CRED', 700.00),
                                                                                                                                                                      ('T009', '2024-02-04', '23456-8', 'Pagamento de Boleto', 'PGTO_BOL', -300.00);

-- Atualizar saldo das contas bancárias de Maria Oliveira
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 150.00 WHERE numero_conta_bancaria = '23456-7';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '23456-7';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 700.00 WHERE numero_conta_bancaria = '23456-8';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 300.00 WHERE numero_conta_bancaria = '23456-8';

-- Transações para as contas bancárias de Carlos Souza (Cliente 3)
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T010', '2024-02-01', '34567-8', 'PIX recebido', 'PIX_REC', 400.00),
                                                                                                                                                                      ('T011', '2024-02-02', '34567-8', 'Depósito em conta', 'DEP', 200.00),
                                                                                                                                                                      ('T012', '2024-02-03', '34567-9', 'Saque em caixa eletrônico', 'SAQ', -500.00),
                                                                                                                                                                      ('T013', '2024-02-04', '34567-9', 'Transferência realizada', 'TRANSF', -200.00);

-- Atualizar saldo das contas bancárias de Carlos Souza
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 400.00 WHERE numero_conta_bancaria = '34567-8';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 200.00 WHERE numero_conta_bancaria = '34567-8';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 500.00 WHERE numero_conta_bancaria = '34567-9';
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '34567-9';