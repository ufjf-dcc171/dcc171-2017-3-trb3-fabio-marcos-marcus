create table USUARIO
(
	IDUSUARIO INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
	NOME VARCHAR(255)
);
create table USUARIOTAREFA
(
	IDTAREFA INTEGER not null,
	IDUSUARIO INTEGER not null,
	primary key (IDTAREFA, IDUSUARIO)
);