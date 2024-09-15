-- Inserindo dados na tabela TipoTransacao
INSERT INTO tipo_transacao (codigo_tipo_transacao, nome_tipo_transacao, descricao_padrao_transacao)
VALUES
    ('PIX_REC', 'PIX Recebido', 'CREDITO'),
    ('PIX_ENV', 'PIX Enviado', 'DEBITO'),
    ('DEP', 'Depósito', 'CREDITO'),
    ('SAQ', 'Saque', 'DEBITO'),
    ('TRANSF', 'Transferência Bancária', 'DEBITO'),
    ('PGTO_BOL', 'Pagamento de Boleto', 'DEBITO'),
    ('REC_CRED', 'Recebimento de Crédito', 'CREDITO');
