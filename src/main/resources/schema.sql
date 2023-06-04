CREATE TABLE paciente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE
);

CREATE TABLE medicos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  crm INT UNIQUE,
  valor_hora DOUBLE
);

CREATE TABLE especialidades (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100)
);

CREATE TABLE medico_especialidade (
  id INT AUTO_INCREMENT PRIMARY KEY,
  medico_id INT,
  especialidade_id INT,
  FOREIGN KEY (medico_id) REFERENCES medicos(id),
  FOREIGN KEY (especialidade_id) REFERENCES especialidades(id)
);