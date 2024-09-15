-- Inserindo clientes
-- Aqui estamos inserindo três clientes: João Silva, Maria Oliveira e Carlos Souza, com seus respectivos endereços.
INSERT INTO cliente (endereco_id, id, complemento_endereco, cpf_cliente, nome_cliente, numero_endereco) VALUES
                                                                                                            (1, 1, 'Apto 101', '12345678901', 'João Silva', '100'),
                                                                                                            (2, 2, 'Casa', '23456789012', 'Maria Oliveira', '200'),
                                                                                                            (3, 3, 'Bloco B', '34567890123', 'Carlos Souza', '300');

-- Inserindo contas bancárias para os clientes
-- Inserimos duas contas bancárias para cada cliente (João, Maria, e Carlos). As contas são de diferentes tipos: CC (conta corrente), CP (conta poupança), PJ (conta pessoa jurídica).
INSERT INTO conta_bancaria (numero_conta_bancaria, agencia_codigo_agencia, cliente_id, tipo_conta_bancaria_sigla_tipo_conta_bancaria, data_abertura_conta_bancaria, saldo_atua_conta_bancaria) VALUES
                                                                                                                                                                                                   ('12345-6', '1709-x', 1, 'CC', '2024-01-01', 1000.00),
                                                                                                                                                                                                   ('12345-7', '1709-x', 1, 'CP', '2024-01-10', 2000.00),
                                                                                                                                                                                                   ('23456-7', '1710-x', 2, 'CC', '2024-01-05', 1500.00),
                                                                                                                                                                                                   ('23456-8', '1710-x', 2, 'PJ', '2024-01-20', 3000.00),
                                                                                                                                                                                                   ('34567-8', '1711-x', 3, 'CC', '2024-01-15', 1800.00),
                                                                                                                                                                                                   ('34567-9', '1711-x', 3, 'CP', '2024-01-25', 2500.00);

-- Transações para as contas bancárias de João Silva
-- Transações de crédito e débito, incluindo PIX, depósitos, transferências, saques, e pagamentos de boletos. As transações são sempre positivas.
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T001', '2024-02-01', '12345-6', 'Recebimento PIX', 'PIX_REC', 300.00),
                                                                                                                                                                      ('T002', '2024-02-02', '12345-6', 'Depósito em conta', 'DEP', 500.00),
                                                                                                                                                                      ('T003', '2024-02-03', '12345-6', 'Saque realizado', 'SAQ', 200.00),
                                                                                                                                                                      ('T004', '2024-02-05', '12345-7', 'Transferência recebida', 'TRANSF', 600.00),
                                                                                                                                                                      ('T005', '2024-02-06', '12345-7', 'Pagamento de Boleto', 'PGTO_BOL', 100.00);

-- Atualização do saldo das contas bancárias de João Silva
-- Cada saldo é atualizado de acordo com o tipo de transação (crédito ou débito). O saldo aumenta ou diminui dependendo da operação.
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 300.00 WHERE numero_conta_bancaria = '12345-6'; -- PIX_REC (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 500.00 WHERE numero_conta_bancaria = '12345-6'; -- DEP (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '12345-6'; -- SAQ (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 600.00 WHERE numero_conta_bancaria = '12345-7'; -- TRANSF (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 100.00 WHERE numero_conta_bancaria = '12345-7'; -- PGTO_BOL (DEBITO)

-- Transações para as contas bancárias de Maria Oliveira
-- Transações semelhantes, mas com valores diferentes e incluindo tipos de transação como PIX enviado, recebimento de crédito, e saques.
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T006', '2024-02-01', '23456-7', 'PIX enviado', 'PIX_ENV', 150.00),
                                                                                                                                                                      ('T007', '2024-02-02', '23456-7', 'Saque realizado', 'SAQ', 200.00),
                                                                                                                                                                      ('T008', '2024-02-03', '23456-8', 'Recebimento de Crédito', 'REC_CRED', 700.00),
                                                                                                                                                                      ('T009', '2024-02-04', '23456-8', 'Pagamento de Boleto', 'PGTO_BOL', 300.00);

-- Atualização do saldo das contas bancárias de Maria Oliveira
-- O saldo das contas de Maria Oliveira é atualizado com base nas transações.
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 150.00 WHERE numero_conta_bancaria = '23456-7'; -- PIX_ENV (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '23456-7'; -- SAQ (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 700.00 WHERE numero_conta_bancaria = '23456-8'; -- REC_CRED (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 300.00 WHERE numero_conta_bancaria = '23456-8'; -- PGTO_BOL (DEBITO)

-- Transações para as contas bancárias de Carlos Souza
-- Inclui tipos de transação como PIX recebido, saques e transferências, todos com valores positivos.
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T010', '2024-02-01', '34567-8', 'PIX recebido', 'PIX_REC', 400.00),
                                                                                                                                                                      ('T011', '2024-02-02', '34567-8', 'Depósito em conta', 'DEP', 200.00),
                                                                                                                                                                      ('T012', '2024-02-03', '34567-9', 'Saque em caixa eletrônico', 'SAQ', 500.00),
                                                                                                                                                                      ('T013', '2024-02-04', '34567-9', 'Transferência realizada', 'TRANSF', 200.00);

-- Atualização do saldo das contas bancárias de Carlos Souza
-- As atualizações seguem o padrão de débito e crédito de acordo com as transações realizadas.
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 400.00 WHERE numero_conta_bancaria = '34567-8'; -- PIX_REC (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 200.00 WHERE numero_conta_bancaria = '34567-8'; -- DEP (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 500.00 WHERE numero_conta_bancaria = '34567-9'; -- SAQ (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '34567-9'; -- TRANSF (DEBITO)

-- Inserindo dados na tabela DDD
-- Códigos de área para São Paulo, Rio de Janeiro e Minas Gerais.
INSERT INTO ddd (numeroddd) VALUES
                                ('11'), -- São Paulo
                                ('21'), -- Rio de Janeiro
                                ('31'); -- Minas Gerais

-- Inserindo dados na tabela DDI
-- Código de país para o Brasil.
INSERT INTO ddi (numeroddi) VALUES
    ('55'); -- Brasil

-- Inserindo telefones para os clientes
-- Cada cliente tem dois telefones associados, com DDD e DDI preenchidos.
INSERT INTO telefone (cliente_id, numero_telefone, numeroddd_numeroddd, numeroddi_numeroddi) VALUES
                                                                                                 (1, '999999999', '11', '55'),  -- João Silva - Primeiro telefone
                                                                                                 (1, '988888888', '11', '55'),  -- João Silva - Segundo telefone
                                                                                                 (2, '987777777', '21', '55'),  -- Maria Oliveira - Primeiro telefone
                                                                                                 (2, '986666666', '21', '55'),  -- Maria Oliveira - Segundo telefone
                                                                                                 (3, '977777777', '31', '55'),  -- Carlos Souza - Primeiro telefone
                                                                                                 (3, '976666666', '31', '55');  -- Carlos Souza - Segundo telefone

-- Inserindo e-mails para os clientes
-- Cada cliente também tem dois endereços de e-mail associados.
INSERT INTO email (cliente_id, email) VALUES
                                          (1, 'joao.silva@example.com'),    -- João Silva - Primeiro e-mail
                                          (1, 'joao.silva2@example.com'),   -- João Silva - Segundo e-mail
                                          (2, 'maria.oliveira@example.com'),    -- Maria Oliveira - Primeiro e-mail
                                          (2, 'maria.oliveira2@example.com'),   -- Maria Oliveira - Segundo e-mail
                                          (3, 'carlos.souza@example.com'),    -- Carlos Souza - Primeiro e-mail
                                          (3, 'carlos.souza2@example.com');   -- Carlos Souza - Segundo e-mail

-- Mais transações para as contas bancárias de João Silva
-- Incluindo mais transações para João Silva, com atualizações de saldo baseadas nos tipos de transação.
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T014', '2024-02-07', '12345-6', 'Recebimento PIX', 'PIX_REC', 300.00),
                                                                                                                                                                      ('T015', '2024-02-08', '12345-6', 'Transferência enviada', 'TRANSF', 500.00),
                                                                                                                                                                      ('T016', '2024-02-09', '12345-7', 'Depósito de Salário', 'DEP', 2500.00),
                                                                                                                                                                      ('T017', '2024-02-10', '12345-7', 'Pagamento de Boleto', 'PGTO_BOL', 200.00),
                                                                                                                                                                      ('T018', '2024-02-11', '12345-6', 'Saque em Caixa Eletrônico', 'SAQ', 300.00);

-- Atualizando saldo das contas de João Silva
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 300.00 WHERE numero_conta_bancaria = '12345-6'; -- PIX_REC (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 500.00 WHERE numero_conta_bancaria = '12345-6'; -- TRANSF (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 2500.00 WHERE numero_conta_bancaria = '12345-7'; -- DEP (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 200.00 WHERE numero_conta_bancaria = '12345-7'; -- PGTO_BOL (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 300.00 WHERE numero_conta_bancaria = '12345-6'; -- SAQ (DEBITO)

-- Mais transações para as contas bancárias de Maria Oliveira
-- Mais transações para Maria Oliveira, com a respectiva atualização de saldo.
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T019', '2024-02-07', '23456-7', 'Transferência enviada', 'TRANSF', 500.00),
                                                                                                                                                                      ('T020', '2024-02-08', '23456-8', 'Recebimento de Crédito', 'REC_CRED', 2000.00),
                                                                                                                                                                      ('T021', '2024-02-09', '23456-7', 'Depósito de Conta', 'DEP', 1000.00),
                                                                                                                                                                      ('T022', '2024-02-10', '23456-8', 'Pagamento de Boleto', 'PGTO_BOL', 700.00),
                                                                                                                                                                      ('T023', '2024-02-11', '23456-7', 'Saque realizado', 'SAQ', 250.00);

-- Atualizando saldo das contas de Maria Oliveira
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 500.00 WHERE numero_conta_bancaria = '23456-7'; -- TRANSF (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 2000.00 WHERE numero_conta_bancaria = '23456-8'; -- REC_CRED (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 1000.00 WHERE numero_conta_bancaria = '23456-7'; -- DEP (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 700.00 WHERE numero_conta_bancaria = '23456-8'; -- PGTO_BOL (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 250.00 WHERE numero_conta_bancaria = '23456-7'; -- SAQ (DEBITO)

-- Mais transações para as contas bancárias de Carlos Souza
-- Últimas transações para Carlos Souza e a respectiva atualização de saldo.
INSERT INTO transacao (codigo_transacao, data_transacao, conta_bancaria_numero_conta_bancaria, observacao, tipo_transacao_codigo_tipo_transacao, valor_transacao) VALUES
                                                                                                                                                                      ('T024', '2024-02-07', '34567-8', 'Recebimento PIX', 'PIX_REC', 600.00),
                                                                                                                                                                      ('T025', '2024-02-08', '34567-9', 'Recebimento de Crédito', 'REC_CRED', 800.00),
                                                                                                                                                                      ('T026', '2024-02-09', '34567-8', 'Saque realizado', 'SAQ', 350.00),
                                                                                                                                                                      ('T027', '2024-02-10', '34567-9', 'Transferência enviada', 'TRANSF', 900.00),
                                                                                                                                                                      ('T028', '2024-02-11', '34567-8', 'Pagamento de Boleto', 'PGTO_BOL', 180.00);

-- Atualizando saldo das contas de Carlos Souza
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 600.00 WHERE numero_conta_bancaria = '34567-8'; -- PIX_REC (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria + 800.00 WHERE numero_conta_bancaria = '34567-9'; -- REC_CRED (CREDITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 350.00 WHERE numero_conta_bancaria = '34567-8'; -- SAQ (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 900.00 WHERE numero_conta_bancaria = '34567-9'; -- TRANSF (DEBITO)
UPDATE conta_bancaria SET saldo_atua_conta_bancaria = saldo_atua_conta_bancaria - 180.00 WHERE numero_conta_bancaria = '34567-8'; -- PGTO_BOL (DEBITO)