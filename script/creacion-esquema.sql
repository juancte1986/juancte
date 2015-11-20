-- password is 'somePassword' hashed con http://www.nitrxgen.net/hashgen/ - Ojo que tal vez haya que agregar un '*' adelante
CREATE USER 'jveron'@'localhost' IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194'; 

CREATE SCHEMA IF NOT EXISTS `appschema`;

USE `appschema`;

create table usuario (
	id integer not null NULL AUTO_INCREMENT,
	usuario varchar (50) not null,
	contrasenia varchar (50) not null,
	nombre varchar (50) not null,
	apellido varchar (50) not null,
	locale varchar (10) not null,
	primary key (id)
);

	insert into usuario (usuario, contrasenia, nombre, apellido, locale) values('user1','1234','juan','pppp','es_ar');
	insert into usuario (usuario, contrasenia, nombre, apellido, locale) values('user2','1234','martin','pppp','es_ar');
	insert into usuario (usuario, contrasenia, nombre, apellido, locale) values('user3','1234','esteban','pppp','en_us');

create table sala (
	id integer not null AUTO_INCREMENT,
	descripcion varchar (500) not null,
	primary key (id)
);

insert into sala (descripcion) values ('Los corales');
insert into sala (descripcion) values ('Los cardales');
insert into sala (descripcion) values ('Principal');

create table evento (
	eventoId integer not null AUTO_INCREMENT,
	nombre varchar (50) not null, 
	fecha DATETIME not null,
	usuarioId integer not null,
	horaInicio DATETIME not null,
	horaFin  DATETIME not null,
	primary key(eventoId),
	FOREIGN KEY (usuarioId) REFERENCES usuario(id)
);

create table evento_privado  (
	eventoId integer not null,
	direccion varchar(500) not null,
	descripcion varchar(500) not null,
	primary key(eventoId)
);

create table reunion (
	eventoId integer not null,
	tema varchar(500) not null,
	salaId integer not null,
	primary key(eventoId),
	FOREIGN KEY (salaId) REFERENCES sala(id)
	
);

create table invitado (
	id integer not null AUTO_INCREMENT,
	eventoId integer not null,
	usuarioId integer not null,
	estado integer not null,
	primary key(id),
	FOREIGN KEY (usuarioId) REFERENCES usuario(id),
	FOREIGN KEY (eventoId) REFERENCES reunion(eventoId)
);

GRANT ALL PRIVILEGES ON `appschema`.* TO 'jveron'@'localhost'
  IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194';


