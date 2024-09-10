-- Inserindo dados na tabela TipoTransacao
INSERT INTO tipo_transacao (codigo_tipo_transacao, nome_tipo_transacao) VALUES
                                                                            ('PIX_REC', 'PIX Recebido'),
                                                                            ('PIX_ENV', 'PIX Enviado'),
                                                                            ('DEP', 'Depósito'),
                                                                            ('SAQ', 'Saque'),
                                                                            ('TRANSF', 'Transferência Bancária'),
                                                                            ('PGTO_BOL', 'Pagamento de Boleto'),
                                                                            ('REC_CRED', 'Recebimento de Crédito');

-- Indicando se a transação é crédito ou débito
-- Aqui você pode assumir que transações como 'PIX Recebido' e 'Depósito' são créditos
-- e transações como 'PIX Enviado' e 'Saque' são débitos.

-- Atualizando a tabela para indicar se a transação é débito ou crédito
UPDATE tipo_transacao
SET descricao_padrao = 'Crédito'
WHERE codigo_tipo_transacao IN ('PIX_REC', 'DEP', 'REC_CRED');

UPDATE tipo_transacao
SET descricao_padrao = 'Débito'
WHERE codigo_tipo_transacao IN ('PIX_ENV', 'SAQ', 'TRANSF', 'PGTO_BOL');
