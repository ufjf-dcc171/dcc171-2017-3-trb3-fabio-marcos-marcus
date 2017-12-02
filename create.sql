create table PROJETO
(
	IDPROJETO INTEGER not null primary key,
	DESCRICAO VARCHAR(255)
);
create table TAREFA
(
	IDTAREFA INTEGER not null primary key,
	IDPROJETO INTEGER,
	DESCRICAO VARCHAR(255),
	STATUS BOOLEAN,
	DATAINICIO DATE,
	DATAFINAL DATE,
	DIASCONCLUSAO INTEGER,
        PERCENTUAL INTEGER
);
create table USUARIO
(
	IDUSUARIO INTEGER not null primary key,
	NOME VARCHAR(255)
);
create table USUARIOTAREFA
(
	IDTAREFA INTEGER not null,
	IDUSUARIO BOOLEAN not null,
	primary key (IDTAREFA, IDUSUARIO)
);
create table TAREFAASSOCIADA
(
	IDTAREFA INTEGER not null,
	IDTAREFAASSOCIADA INTEGER not null,
	primary key (IDTAREFA, IDTAREFAASSOCIADA)
)