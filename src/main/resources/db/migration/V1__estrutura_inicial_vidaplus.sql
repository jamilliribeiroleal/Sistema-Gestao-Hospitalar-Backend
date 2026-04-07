-- =====================================================================
-- V1 - DATABASE SCHEMA MYSQL (COMPATÍVEL COM HIBERNATE)
-- =====================================================================
SET FOREIGN_KEY_CHECKS = 0;

-- ========================================
-- TABELAS PRINCIPAIS
-- ========================================

-- Roles
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Profissionais
CREATE TABLE profissionais (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    registro_profissional VARCHAR(100),
    especialidade VARCHAR(150),
    email VARCHAR(255),
    telefone_enc BLOB,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME
);

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    profissional_id BIGINT,
    paciente_id BIGINT,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME,

    CONSTRAINT fk_users_profissional
        FOREIGN KEY (profissional_id)
        REFERENCES profissionais(id),

    CONSTRAINT fk_users_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes(id)
);



-- Unidades
CREATE TABLE unidades (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo ENUM('HOSPITAL','CLINICA','LABORATORIO','HOMECARE') NOT NULL,
    endereco_enc BLOB,
    telefone_enc BLOB,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME
);

-- Pacientes
CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf_hash VARCHAR(64),
    cpf_enc BLOB,
    data_nascimento DATE,
    sexo ENUM('M','F','O'),
    email VARCHAR(255),
    telefone_enc BLOB,
    endereco_enc BLOB,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME
);

-- Leitos
CREATE TABLE leitos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    unidade_id BIGINT NOT NULL,
    numero VARCHAR(255) NOT NULL,
    status ENUM('LIVRE','OCUPADO','MANUTENCAO') NOT NULL DEFAULT 'LIVRE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME,
    UNIQUE KEY uq_leito_unidade_numero (unidade_id, numero),
    FOREIGN KEY (unidade_id) REFERENCES unidades(id)
);

-- Consultas
CREATE TABLE consultas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT,
    unidade_id BIGINT,
    data_hora DATETIME NOT NULL,
    tipo ENUM('PRESENCIAL','TELECONSULTA') NOT NULL DEFAULT 'PRESENCIAL',
    status ENUM('AGENDADA','CANCELADA','FINALIZADA') NOT NULL DEFAULT 'AGENDADA',
    motivo VARCHAR(512),
    link_teleconsulta VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id),
    FOREIGN KEY (unidade_id) REFERENCES unidades(id)
);
CREATE INDEX idx_consulta_data_hora ON consultas(data_hora);

-- Prontuarios
CREATE TABLE prontuarios (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT, 
    consulta_id BIGINT,
    descricao_enc BLOB NOT NULL,
    diagnostico_enc BLOB,
    observacoes_enc BLOB,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME,
    CONSTRAINT fk_prontuario_paciente
        FOREIGN KEY (paciente_id) REFERENCES pacientes(id),

    CONSTRAINT fk_prontuario_profissional
        FOREIGN KEY (profissional_id) REFERENCES profissionais(id),

    CONSTRAINT fk_prontuario_consulta
        FOREIGN KEY (consulta_id) REFERENCES consultas(id)
);


-- Receitas
CREATE TABLE receitas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    consulta_id BIGINT,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT,
    data_emissao DATE NOT NULL,
    conteudo_enc BLOB NOT NULL,
    receita_pdf_url VARCHAR(1024),
    assinatura_digital VARCHAR(1024),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (consulta_id) REFERENCES consultas(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id)
);

-- Receitas Digitais
CREATE TABLE receitas_digitais (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    consulta_id BIGINT,
    medicamentos TEXT NOT NULL,
    orientacoes TEXT,
    data_emissao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ativa BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id),
    FOREIGN KEY (consulta_id) REFERENCES consultas(id)
);

-- Teleconsultas
CREATE TABLE teleconsultas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    consulta_id BIGINT,
    link_acesso VARCHAR(255),
    status_chamada ENUM('AGENDADA','EM_ANDAMENTO','FINALIZADA','CANCELADA') NOT NULL DEFAULT 'AGENDADA',
    data_inicio DATETIME,
    data_fim DATETIME,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id),
    FOREIGN KEY (consulta_id) REFERENCES consultas(id)
);

-- AuthTokens
CREATE TABLE auth_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token_hash VARCHAR(64) NOT NULL UNIQUE,
    tipo ENUM('ACCESS','REFRESH') NOT NULL DEFAULT 'REFRESH',
    expires_at DATETIME NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- AuditLog
CREATE TABLE audit_log (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    entidade VARCHAR(255) NOT NULL,
    entidade_id BIGINT,
    acao ENUM('CREATE','READ','UPDATE','DELETE') NOT NULL,
    detalhes JSON,
    ip VARCHAR(45),
    correlation_id VARCHAR(100),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE INDEX idx_audit_entidade ON audit_log(entidade, entidade_id);
CREATE INDEX idx_audit_user ON audit_log(user_id);

-- Notificacoes
CREATE TABLE notificacoes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    consulta_id BIGINT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    lida BOOLEAN NOT NULL DEFAULT FALSE,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (consulta_id) REFERENCES consultas(id)
);
CREATE INDEX idx_notif_paciente ON notificacoes(paciente_id);
CREATE INDEX idx_notif_lida ON notificacoes(lida);

-- Internacoes
CREATE TABLE internacoes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    paciente_id BIGINT NOT NULL,
    profissional_responsavel_id BIGINT NOT NULL,
    unidade_id BIGINT NOT NULL,

    data_entrada DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alta DATETIME,

    status ENUM(
        'ADMITIDA',
        'EM_OBSERVACAO',
        'INTERNADA',
        'ALTA',
        'CANCELADA'
    ) NOT NULL DEFAULT 'ADMITIDA',

    motivo VARCHAR(500),

    CONSTRAINT fk_internacao_paciente
        FOREIGN KEY (paciente_id) REFERENCES pacientes(id),

    CONSTRAINT fk_internacao_profissional
        FOREIGN KEY (profissional_responsavel_id) REFERENCES profissionais(id),

    CONSTRAINT fk_internacao_unidade
        FOREIGN KEY (unidade_id) REFERENCES unidades(id)
);

-- Tabela de relacionamento User-Roles
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

SET FOREIGN_KEY_CHECKS = 1;
