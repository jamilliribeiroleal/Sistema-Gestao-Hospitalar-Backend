
-- V2 - DATABASE SEED (

SET FOREIGN_KEY_CHECKS = 0;


-- ROLES
INSERT INTO roles (id, name, description, created_at) VALUES
(1, 'ROLE_ADMIN', 'Administrador do sistema', CURDATE()),
(2, 'ROLE_PROFISSIONAL', 'Profissional de saúde', CURDATE()),
(3, 'ROLE_PACIENTE', 'Paciente do sistema', CURDATE());


-- USERS 
INSERT INTO users (
    id,
    username,
    email,
    password_hash,
    active,
    profissional_id,
    paciente_id,
    created_at,
    updated_at,
    deleted_at
) VALUES

-- ADMIN
(1,'admin','admin@gmail.com',
'$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',
TRUE,NULL,NULL,CURDATE(),NULL,NULL),

-- PROFISSIONAIS
(2,'joao.silva','joao.silva@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,1,NULL,CURDATE(),NULL,NULL),
(3,'maria.souza','maria.souza@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,2,NULL,CURDATE(),NULL,NULL),
(4,'pedro.almeida','pedro.almeida@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,3,NULL,CURDATE(),NULL,NULL),
(5,'ana.costa','ana.costa@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,4,NULL,CURDATE(),NULL,NULL),
(6,'lucas.pereira','lucas.pereira@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,5,NULL,CURDATE(),NULL,NULL),
(7,'carla.mendes','carla.mendes@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,6,NULL,CURDATE(),NULL,NULL),
(8,'rafael.lima','rafael.lima@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,7,NULL,CURDATE(),NULL,NULL),
(9,'fernanda.rocha','fernanda.rocha@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,8,NULL,CURDATE(),NULL,NULL),
(10,'bruno.gomes','bruno.gomes@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,9,NULL,CURDATE(),NULL,NULL),
(11,'juliana.nogueira','juliana.nogueira@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,10,NULL,CURDATE(),NULL,NULL),

-- PACIENTES
(12,'carlos.santos','carlos.santos@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,1,CURDATE(),NULL,NULL),
(13,'mariana.freitas','mariana.freitas@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,2,CURDATE(),NULL,NULL),
(14,'andre.ribeiro','andre.ribeiro@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,3,CURDATE(),NULL,NULL),
(15,'patricia.lopes','patricia.lopes@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,4,CURDATE(),NULL,NULL),
(16,'roberto.teixeira','roberto.teixeira@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,5,CURDATE(),NULL,NULL),
(17,'aline.barbosa','aline.barbosa@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,6,CURDATE(),NULL,NULL),
(18,'felipe.martins','felipe.martins@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,7,CURDATE(),NULL,NULL),
(19,'camila.pacheco','camila.pacheco@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,8,CURDATE(),NULL,NULL),
(20,'daniel.farias','daniel.farias@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,9,CURDATE(),NULL,NULL),
(21,'beatriz.cunha','beatriz.cunha@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,10,CURDATE(),NULL,NULL),
(22,'eduardo.batista','eduardo.batista@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,11,CURDATE(),NULL,NULL),
(23,'renata.moreira','renata.moreira@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,12,CURDATE(),NULL,NULL),
(24,'thiago.araujo','thiago.araujo@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,13,CURDATE(),NULL,NULL),
(25,'larissa.guimaraes','larissa.guimaraes@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,14,CURDATE(),NULL,NULL),
(26,'vinicius.melo','vinicius.melo@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,15,CURDATE(),NULL,NULL),
(27,'sabrina.ramos','sabrina.ramos@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,16,CURDATE(),NULL,NULL),
(28,'gustavo.paiva','gustavo.paiva@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,17,CURDATE(),NULL,NULL),
(29,'natalia.torres','natalia.torres@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,18,CURDATE(),NULL,NULL),
(30,'leonardo.machado','leonardo.machado@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,19,CURDATE(),NULL,NULL),
(31,'isabela.fonseca','isabela.fonseca@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,20,CURDATE(),NULL,NULL),
(32,'paulo.dias','paulo.dias@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,21,CURDATE(),NULL,NULL),
(33,'tatiane.neves','tatiane.neves@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,22,CURDATE(),NULL,NULL),
(34,'henrique.moraes','henrique.moraes@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,23,CURDATE(),NULL,NULL),
(35,'carolina.borges','carolina.borges@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,24,CURDATE(),NULL,NULL),
(36,'diego.pinto','diego.pinto@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,25,CURDATE(),NULL,NULL),
(37,'luana.rangel','luana.rangel@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,26,CURDATE(),NULL,NULL),
(38,'marcos.vieira','marcos.vieira@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,27,CURDATE(),NULL,NULL),
(39,'priscila.andrade','priscila.andrade@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,28,CURDATE(),NULL,NULL),
(40,'alexandre.ferraz','alexandre.ferraz@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,29,CURDATE(),NULL,NULL),
(41,'veronica.silveira','veronica.silveira@gmail.com','$2a$10$yyHK10Fe8tRt2JC/YiThmOi1XvqX7ONCeMbNeLOqkuEaKw1fDBL5O',TRUE,NULL,30,CURDATE(),NULL,NULL);

-- USER_ROLES
INSERT INTO user_roles VALUES
-- ADMIN
(1,1),

-- PROFISSIONAIS
(2,2),(3,2),(4,2),(5,2),(6,2),
(7,2),(8,2),(9,2),(10,2),(11,2),

-- PACIENTES
(12,3),(13,3),(14,3),(15,3),(16,3),
(17,3),(18,3),(19,3),(20,3),(21,3),
(22,3),(23,3),(24,3),(25,3),(26,3),
(27,3),(28,3),(29,3),(30,3),(31,3),
(32,3),(33,3),(34,3),(35,3),(36,3),
(37,3),(38,3),(39,3),(40,3),(41,3);


-- UNIDADES
INSERT INTO unidades VALUES
(1,'Hospital Central','HOSPITAL',X'01',X'01',CURDATE(),NULL,NULL),
(2,'Clínica Vida','CLINICA',X'02',X'02',CURDATE(),NULL,NULL),
(3,'Laboratório Diagnóstico','LABORATORIO',X'03',X'03',CURDATE(),NULL,NULL),
(4,'HomeCare Plus','HOMECARE',X'04',X'04',CURDATE(),NULL,NULL);

-- PROFISSIONAIS
INSERT INTO profissionais VALUES
(1,'Dr. João Silva','CRM001','Cardiologia','joao.silva@gmail.com',X'01',TRUE,CURDATE(),NULL,NULL),
(2,'Dra. Maria Souza','CRM002','Clínica Geral','maria.souza@gmail.com',X'02',TRUE,CURDATE(),NULL,NULL),
(3,'Dr. Pedro Almeida','CRM003','Ortopedia','pedro.almeida@gmail.com',X'03',TRUE,CURDATE(),NULL,NULL),
(4,'Dra. Ana Costa','CRM004','Neurologia','ana.costa@gmail.com',X'04',TRUE,CURDATE(),NULL,NULL),
(5,'Dr. Lucas Pereira','CRM005','Psiquiatria','lucas.pereira@gmail.com',X'05',TRUE,CURDATE(),NULL,NULL),
(6,'Dra. Carla Mendes','CRM006','Pediatria','carla.mendes@gmail.com',X'06',TRUE,CURDATE(),NULL,NULL),
(7,'Dr. Rafael Lima','CRM007','Dermatologia','rafael.lima@gmail.com',X'07',TRUE,CURDATE(),NULL,NULL),
(8,'Dra. Fernanda Rocha','CRM008','Ginecologia','fernanda.rocha@gmail.com',X'08',TRUE,CURDATE(),NULL,NULL),
(9,'Dr. Bruno Gomes','CRM009','Urologia','bruno.gomes@gmail.com',X'09',TRUE,CURDATE(),NULL,NULL),
(10,'Dra. Juliana Nogueira','CRM010','Endocrinologia','juliana.nogueira@gmail.com',X'0A',TRUE,CURDATE(),NULL,NULL);

-- PACIENTES
INSERT INTO pacientes VALUES
(1,'Carlos Santos',12,X'01','1990-01-10','M','carlos.santos@gmail.com',X'01',X'01',TRUE,CURDATE(),NULL,NULL),
(2,'Mariana Freitas',13,X'02','1992-02-15','F','mariana.freitas@gmail.com',X'02',X'02',TRUE,CURDATE(),NULL,NULL),
(3,'Andre Ribeiro',14,X'03','1988-03-20','M','andre.ribeiro@gmail.com',X'03',X'03',TRUE,CURDATE(),NULL,NULL),
(4,'Patricia Lopes',15,X'04','1991-04-05','F','patricia.lopes@gmail.com',X'04',X'04',TRUE,CURDATE(),NULL,NULL),
(5,'Roberto Teixeira',16,X'05','1985-05-12','M','roberto.teixeira@gmail.com',X'05',X'05',TRUE,CURDATE(),NULL,NULL),
(6,'Aline Barbosa',17,X'06','1993-06-18','F','aline.barbosa@gmail.com',X'06',X'06',TRUE,CURDATE(),NULL,NULL),
(7,'Felipe Martins',18,X'07','1989-07-22','M','felipe.martins@gmail.com',X'07',X'07',TRUE,CURDATE(),NULL,NULL),
(8,'Camila Pacheco',19,X'08','1994-08-30','F','camila.pacheco@gmail.com',X'08',X'08',TRUE,CURDATE(),NULL,NULL),
(9,'Daniel Farias',20,X'09','1987-09-09','M','daniel.farias@gmail.com',X'09',X'09',TRUE,CURDATE(),NULL,NULL),
(10,'Beatriz Cunha',21,X'0A','1995-10-14','F','beatriz.cunha@gmail.com',X'0A',X'0A',TRUE,CURDATE(),NULL,NULL),
(11,'Eduardo Batista',22,X'0B','1984-11-11','M','eduardo.batista@gmail.com',X'0B',X'0B',TRUE,CURDATE(),NULL,NULL),
(12,'Renata Moreira',23,X'0C','1990-12-03','F','renata.moreira@gmail.com',X'0C',X'0C',TRUE,CURDATE(),NULL,NULL),
(13,'Thiago Araujo',24,X'0D','1986-01-19','M','thiago.araujo@gmail.com',X'0D',X'0D',TRUE,CURDATE(),NULL,NULL),
(14,'Larissa Guimaraes',25,X'0E','1992-02-27','F','larissa.guimaraes@gmail.com',X'0E',X'0E',TRUE,CURDATE(),NULL,NULL),
(15,'Vinicius Melo',26,X'0F','1988-03-08','M','vinicius.melo@gmail.com',X'0F',X'0F',TRUE,CURDATE(),NULL,NULL),
(16,'Sabrina Ramos',27,X'10','1994-04-16','F','sabrina.ramos@gmail.com',X'10',X'10',TRUE,CURDATE(),NULL,NULL),
(17,'Gustavo Paiva',28,X'11','1987-05-25','M','gustavo.paiva@gmail.com',X'11',X'11',TRUE,CURDATE(),NULL,NULL),
(18,'Natalia Torres',29,X'12','1991-06-06','F','natalia.torres@gmail.com',X'12',X'12',TRUE,CURDATE(),NULL,NULL),
(19,'Leonardo Machado',30,X'13','1985-07-17','M','leonardo.machado@gmail.com',X'13',X'13',TRUE,CURDATE(),NULL,NULL),
(20,'Isabela Fonseca',31,X'14','1993-08-21','F','isabela.fonseca@gmail.com',X'14',X'14',TRUE,CURDATE(),NULL,NULL),
(21,'Paulo Dias',32,X'15','1986-09-02','M','paulo.dias@gmail.com',X'15',X'15',TRUE,CURDATE(),NULL,NULL),
(22,'Tatiane Neves',33,X'16','1994-10-11','F','tatiane.neves@gmail.com',X'16',X'16',TRUE,CURDATE(),NULL,NULL),
(23,'Henrique Moraes',34,X'17','1983-11-29','M','henrique.moraes@gmail.com',X'17',X'17',TRUE,CURDATE(),NULL,NULL),
(24,'Carolina Borges',35,X'18','1990-12-20','F','carolina.borges@gmail.com',X'18',X'18',TRUE,CURDATE(),NULL,NULL),
(25,'Diego Pinto',36,X'19','1988-01-07','M','diego.pinto@gmail.com',X'19',X'19',TRUE,CURDATE(),NULL,NULL),
(26,'Luana Rangel',37,X'1A','1995-02-14','F','luana.rangel@gmail.com',X'1A',X'1A',TRUE,CURDATE(),NULL,NULL),
(27,'Marcos Vieira',38,X'1B','1984-03-23','M','marcos.vieira@gmail.com',X'1B',X'1B',TRUE,CURDATE(),NULL,NULL),
(28,'Priscila Andrade',39,X'1C','1992-04-04','F','priscila.andrade@gmail.com',X'1C',X'1C',TRUE,CURDATE(),NULL,NULL),
(29,'Alexandre Ferraz',40,X'1D','1986-05-30','M','alexandre.ferraz@gmail.com',X'1D',X'1D',TRUE,CURDATE(),NULL,NULL),
(30,'Veronica Silveira',41,X'1E','1991-06-18','F','veronica.silveira@gmail.com',X'1E',X'1E',TRUE,CURDATE(),NULL,NULL);

-- LEITOS
INSERT INTO leitos VALUES
(1,1,'101','LIVRE',CURDATE(),NULL,NULL),
(2,1,'102','OCUPADO',CURDATE(),NULL,NULL),
(3,1,'103','MANUTENCAO',CURDATE(),NULL,NULL),
(4,1,'104','LIVRE',CURDATE(),NULL,NULL),
(5,1,'105','LIVRE',CURDATE(),NULL,NULL),
(6,2,'201','LIVRE',CURDATE(),NULL,NULL),
(7,2,'202','OCUPADO',CURDATE(),NULL,NULL),
(8,2,'203','LIVRE',CURDATE(),NULL,NULL),
(9,3,'301','LIVRE',CURDATE(),NULL,NULL),
(10,3,'302','OCUPADO',CURDATE(),NULL,NULL),
(11,3,'303','LIVRE',CURDATE(),NULL,NULL),
(12,4,'401','LIVRE',CURDATE(),NULL,NULL),
(13,4,'402','MANUTENCAO',CURDATE(),NULL,NULL),
(14,4,'403','LIVRE',CURDATE(),NULL,NULL),
(15,4,'404','LIVRE',CURDATE(),NULL,NULL);

-- CONSULTAS
INSERT INTO consultas VALUES
(1,1,1,1,CURDATE(),'PRESENCIAL','AGENDADA','Rotina',NULL,CURDATE(),NULL,NULL),
(2,2,2,2,CURDATE(),'TELECONSULTA','FINALIZADA','Retorno','http://meet/2',CURDATE(),NULL,NULL),
(3,3,3,3,CURDATE(),'PRESENCIAL','CANCELADA','Cancelada',NULL,CURDATE(),NULL,NULL),
(4,4,4,1,CURDATE(),'PRESENCIAL','FINALIZADA','Checkup',NULL,CURDATE(),NULL,NULL),
(5,5,5,2,CURDATE(),'TELECONSULTA','AGENDADA','Online','http://meet/5',CURDATE(),NULL,NULL),
(6,6,6,3,CURDATE(),'PRESENCIAL','AGENDADA','Dor lombar',NULL,CURDATE(),NULL,NULL),
(7,7,7,4,CURDATE(),'TELECONSULTA','FINALIZADA','Ansiedade','http://meet/7',CURDATE(),NULL,NULL),
(8,8,8,1,CURDATE(),'PRESENCIAL','AGENDADA','Dermatologia',NULL,CURDATE(),NULL,NULL),
(9,9,9,2,CURDATE(),'PRESENCIAL','CANCELADA','Cancelado',NULL,CURDATE(),NULL,NULL),
(10,10,10,3,CURDATE(),'TELECONSULTA','FINALIZADA','Avaliação','http://meet/10',CURDATE(),NULL,NULL),
(11,11,1,4,CURDATE(),'PRESENCIAL','AGENDADA','Retorno',NULL,CURDATE(),NULL,NULL),
(12,12,2,1,CURDATE(),'TELECONSULTA','FINALIZADA','Exames','http://meet/12',CURDATE(),NULL,NULL),
(13,13,3,2,CURDATE(),'PRESENCIAL','AGENDADA','Ortopedia',NULL,CURDATE(),NULL,NULL),
(14,14,4,3,CURDATE(),'PRESENCIAL','FINALIZADA','Neurologia',NULL,CURDATE(),NULL,NULL),
(15,15,5,4,CURDATE(),'TELECONSULTA','AGENDADA','Psiquiatria','http://meet/15',CURDATE(),NULL,NULL),
(16,16,6,1,CURDATE(),'PRESENCIAL','AGENDADA','Pediatria',NULL,CURDATE(),NULL,NULL),
(17,17,7,2,CURDATE(),'TELECONSULTA','FINALIZADA','Alergia','http://meet/17',CURDATE(),NULL,NULL),
(18,18,8,3,CURDATE(),'PRESENCIAL','AGENDADA','Ginecologia',NULL,CURDATE(),NULL,NULL),
(19,19,9,4,CURDATE(),'PRESENCIAL','FINALIZADA','Urologia',NULL,CURDATE(),NULL,NULL),
(20,20,10,1,CURDATE(),'TELECONSULTA','AGENDADA','Endocrinologia','http://meet/20',CURDATE(),NULL,NULL);


-- PRONTUARIOS
INSERT INTO prontuarios VALUES
(
  1, 1, 1, 1,
  AES_ENCRYPT('Avaliação clínica inicial do paciente', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Hipertensão leve', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Recomendado acompanhamento mensal', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  2, 2, 2, 2,
  AES_ENCRYPT('Consulta de retorno', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Diabetes tipo 2 controlada', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Manter medicação atual', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  3, 3, 3, 3,
  AES_ENCRYPT('Queixa de dor lombar', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Distensão muscular', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Repouso por 7 dias', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  4, 4, 4, 4,
  AES_ENCRYPT('Avaliação cardiológica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Arritmia leve', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Solicitado ECG', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  5, 5, 5, 5,
  AES_ENCRYPT('Consulta preventiva anual', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Paciente saudável', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Retorno em 12 meses', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  6, 6, 1, 6,
  AES_ENCRYPT('Consulta por queixa de cefaleia frequente', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Enxaqueca crônica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Orientado controle do estresse e uso de analgésico', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  7, 7, 2, 7,
  AES_ENCRYPT('Avaliação respiratória', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Bronquite leve', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Prescrito broncodilatador', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  8, 8, 3, 8,
  AES_ENCRYPT('Consulta dermatológica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Dermatite alérgica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Evitar contato com agentes irritantes', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  9, 9, 4, 9,
  AES_ENCRYPT('Avaliação ortopédica do joelho direito', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Condromalácia patelar', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Indicado fortalecimento muscular', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  10, 10, 5, 10,
  AES_ENCRYPT('Consulta de acompanhamento pós-operatório', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Boa evolução clínica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Manter repouso relativo por 15 dias', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  11, 11, 6, 11,
  AES_ENCRYPT('Consulta por dor abdominal recorrente', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Gastrite leve', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Orientada dieta leve e uso de protetor gástrico', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  12, 12, 7, 12,
  AES_ENCRYPT('Avaliação neurológica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Cefaleia tensional', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Recomendado alongamento e redução de estresse', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  13, 13, 8, 13,
  AES_ENCRYPT('Consulta por fadiga excessiva', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Anemia leve', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Solicitado exame de sangue e suplementação', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  14, 14, 9, 14,
  AES_ENCRYPT('Avaliação endocrinológica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Hipotireoidismo', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Iniciado tratamento hormonal', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  15, 15, 10, 15,
  AES_ENCRYPT('Consulta por dor torácica leve', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Dor muscular intercostal', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Prescrito analgésico e repouso', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  16, 16, 1, 16,
  AES_ENCRYPT('Avaliação oftalmológica de rotina', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Miopia moderada', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Atualização da prescrição de óculos', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  17, 17, 2, 17,
  AES_ENCRYPT('Consulta por dor articular em mãos', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Artrite inicial', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Encaminhado para reumatologista', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  18, 18, 3, 18,
  AES_ENCRYPT('Avaliação urológica', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Infecção urinária', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Prescrito antibiótico por 7 dias', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  19, 19, 4, 19,
  AES_ENCRYPT('Consulta por tosse persistente', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Infecção respiratória alta', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Recomendado hidratação e repouso', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
),
(
  20, 20, 5, 20,
  AES_ENCRYPT('Avaliação clínica geral', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Sem alterações significativas', 'VIDAPLUS_SECRET'),
  AES_ENCRYPT('Manter acompanhamento anual', 'VIDAPLUS_SECRET'),
  NOW(), NOW(), NULL
);


-- RECEITAS
INSERT INTO receitas VALUES
(1,1,1,1,CURDATE(),X'01','url1','sig1',CURDATE(),NULL,NULL),
(2,2,2,2,CURDATE(),X'02','url2','sig2',CURDATE(),NULL,NULL),
(3,3,3,3,CURDATE(),X'03','url3','sig3',CURDATE(),NULL,NULL),
(4,4,4,4,CURDATE(),X'04','url4','sig4',CURDATE(),NULL,NULL),
(5,5,5,5,CURDATE(),X'05','url5','sig5',CURDATE(),NULL,NULL),
(6,6,6,6,CURDATE(),X'06','url6','sig6',CURDATE(),NULL,NULL),
(7,7,7,7,CURDATE(),X'07','url7','sig7',CURDATE(),NULL,NULL),
(8,8,8,8,CURDATE(),X'08','url8','sig8',CURDATE(),NULL,NULL),
(9,9,9,9,CURDATE(),X'09','url9','sig9',CURDATE(),NULL,NULL),
(10,10,10,10,CURDATE(),X'0A','url10','sig10',CURDATE(),NULL,NULL),
(11,11,11,1,CURDATE(),X'0B','url11','sig11',CURDATE(),NULL,NULL),
(12,12,12,2,CURDATE(),X'0C','url12','sig12',CURDATE(),NULL,NULL),
(13,13,13,3,CURDATE(),X'0D','url13','sig13',CURDATE(),NULL,NULL),
(14,14,14,4,CURDATE(),X'0E','url14','sig14',CURDATE(),NULL,NULL),
(15,15,15,5,CURDATE(),X'0F','url15','sig15',CURDATE(),NULL,NULL),
(16,16,16,6,CURDATE(),X'10','url16','sig16',CURDATE(),NULL,NULL),
(17,17,17,7,CURDATE(),X'11','url17','sig17',CURDATE(),NULL,NULL),
(18,18,18,8,CURDATE(),X'12','url18','sig18',CURDATE(),NULL,NULL),
(19,19,19,9,CURDATE(),X'13','url19','sig19',CURDATE(),NULL,NULL),
(20,20,20,10,CURDATE(),X'14','url20','sig20',CURDATE(),NULL,NULL),
(21,1,21,1,CURDATE(),X'15','url21','sig21',CURDATE(),NULL,NULL),
(22,2,22,2,CURDATE(),X'16','url22','sig22',CURDATE(),NULL,NULL),
(23,3,23,3,CURDATE(),X'17','url23','sig23',CURDATE(),NULL,NULL),
(24,4,24,4,CURDATE(),X'18','url24','sig24',CURDATE(),NULL,NULL),
(25,5,25,5,CURDATE(),X'19','url25','sig25',CURDATE(),NULL,NULL);


-- TELECONSULTAS
INSERT INTO teleconsultas VALUES
(1,2,2,2,'http://call/2','EM_ANDAMENTO',CURDATE(),NULL),
(2,5,5,5,'http://call/5','AGENDADA',NULL,NULL),
(3,7,2,7,'http://call/7','FINALIZADA',CURDATE(),CURDATE()),
(4,10,5,10,'http://call/10','FINALIZADA',CURDATE(),CURDATE()),
(5,8,3,8,'http://call/8','AGENDADA',NULL,NULL),
(6,9,4,9,'http://call/9','CANCELADA',NULL,NULL),
(7,6,1,6,'http://call/6','EM_ANDAMENTO',CURDATE(),NULL);


-- AUTH TOKENS
INSERT INTO auth_tokens VALUES
(1,1,'hash1','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(2,1,'hash2','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(3,2,'hash3','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(4,3,'hash4','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(5,4,'hash5','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(6,5,'hash6','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(7,6,'hash7','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(8,7,'hash8','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(9,8,'hash9','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(10,9,'hash10','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(11,10,'hash11','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(12,11,'hash12','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(13,12,'hash13','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(14,13,'hash14','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(15,14,'hash15','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(16,15,'hash16','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(17,16,'hash17','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(18,17,'hash18','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(19,18,'hash19','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(20,19,'hash20','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(21,20,'hash21','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(22,21,'hash22','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(23,22,'hash23','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(24,23,'hash24','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(25,24,'hash25','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(26,25,'hash26','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(27,26,'hash27','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(28,27,'hash28','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(29,28,'hash29','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(30,29,'hash30','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(31,30,'hash31','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(32,31,'hash32','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(33,32,'hash33','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(34,33,'hash34','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(35,34,'hash35','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(36,35,'hash36','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(37,36,'hash37','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(38,37,'hash38','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE()),
(39,38,'hash39','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(40,39,'hash40','ACCESS',DATE_ADD(CURDATE(),INTERVAL 1 DAY),FALSE,CURDATE()),
(41,40,'hash41','REFRESH',DATE_ADD(CURDATE(),INTERVAL 7 DAY),FALSE,CURDATE());

-- AUDIT LOG
INSERT INTO audit_log VALUES
(1,1,'users',1,'CREATE','{}','127.0.0.1','corr-1',CURDATE()),
(2,1,'pacientes',1,'READ','{}','127.0.0.1','corr-2',CURDATE()),
(3,2,'consultas',2,'UPDATE','{}','127.0.0.1','corr-3',CURDATE()),
(4,3,'receitas',3,'DELETE','{}','127.0.0.1','corr-4',CURDATE());

-- NOTIFICACOES
INSERT INTO notificacoes VALUES
(1,1,1,'Consulta agendada','Sua consulta foi marcada',FALSE,CURDATE()),
(2,2,2,'Consulta finalizada','Consulta concluída',TRUE,CURDATE()),
(3,3,3,'Consulta cancelada','Consulta cancelada',FALSE,CURDATE());

-- INTERNACOES
INSERT INTO internacoes VALUES
(1, 1, 1, 1, CURDATE(), NULL, 'ADMITIDA', 'Paciente admitido para avaliação inicial'),

(2, 2, 2, 1, CURDATE(), NULL, 'EM_OBSERVACAO', 'Paciente em observação clínica'),

(3, 3, 1, 2, CURDATE(), NULL, 'INTERNADA', 'Paciente internado para tratamento contínuo');

-- RECEITAS DIGITAIS
INSERT INTO receitas_digitais VALUES
(1,1,1,1,'Dipirona 500mg','8/8h por 5 dias',CURDATE(),TRUE),
(2,2,2,2,'Paracetamol 750mg','6/6h por 3 dias',CURDATE(),TRUE),
(3,3,3,3,'Ibuprofeno 400mg','8/8h por 7 dias',CURDATE(),TRUE),
(4,4,4,4,'Amoxicilina 500mg','8/8h por 10 dias',CURDATE(),TRUE),
(5,5,5,5,'Sertralina 50mg','Uso contínuo',CURDATE(),TRUE),
(6,6,6,6,'Loratadina 10mg','1x ao dia',CURDATE(),TRUE),
(7,7,7,7,'Omeprazol 20mg','Jejum',CURDATE(),TRUE),
(8,8,8,8,'Metformina 850mg','12/12h',CURDATE(),TRUE),
(9,9,9,9,'Losartana 50mg','1x ao dia',CURDATE(),TRUE),
(10,10,10,10,'Levotiroxina 25mcg','Jejum',CURDATE(),TRUE),
(11,11,1,11,'Vitamina D','30 dias',CURDATE(),TRUE),
(12,12,2,12,'Cálcio','Uso diário',CURDATE(),TRUE),
(13,13,3,13,'Colágeno','30 dias',CURDATE(),TRUE),
(14,14,4,14,'Ácido fólico','Uso contínuo',CURDATE(),TRUE),
(15,15,5,15,'Fluoxetina','Uso diário',CURDATE(),TRUE),
(16,16,6,16,'Prednisona','5 dias',CURDATE(),TRUE),
(17,17,7,17,'Cetoconazol','Uso tópico',CURDATE(),TRUE),
(18,18,8,18,'Anticoncepcional','Uso contínuo',CURDATE(),TRUE),
(19,19,9,19,'Tansulosina','Uso diário',CURDATE(),TRUE),
(20,20,10,20,'Insulina','Conforme prescrição',CURDATE(),TRUE);

SET FOREIGN_KEY_CHECKS = 1;
