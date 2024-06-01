CREATE TABLE produtos(
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    imagem VARCHAR(255),
    desconto DECIMAL(5,2),
    status VARCHAR(50) DEFAULT 'ativo'

);