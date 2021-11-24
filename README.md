# ProyectoOOP2
Para correr el programa se necesita una base de datos llamada Usuarios, con una tabla llamada users y 7 columnas llamadas id, nick, email, nombre, apellido, password y pais, id siendo serial y primary key y el resto siendo varchar
En DB.java se debe alterar el usuario y la password acorde al postgres propio, una vez creada una base de datos con esas condiciones el programa funcionara sin problemas
CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "nick" varchar NOT NULL,
  "email" varchar NOT NULL,
  "nombre" varchar NOT NULL,
  "apellido" varchar NOT NULL,
  "password" varchar NOT NULL,
  "pais" varchar NOT NULL
);
