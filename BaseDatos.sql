CREATE TABLE pais (
  nombre VARCHAR2(50) PRIMARY KEY,
  moneda VARCHAR2(20) NOT NULL
);

CREATE TABLE dpto (
  codigo NUMBER(10) PRIMARY KEY,
  nombre VARCHAR2(50) NOT NULL,
  nompais VARCHAR2(50),
  CONSTRAINT fk_dpto_pais FOREIGN KEY (nompais) REFERENCES pais (nombre)
);

CREATE TABLE ciudad (
  codigo NUMBER(10) PRIMARY KEY,
  nombre VARCHAR(30) NOT NULL,
  poblacion NUMBER(10) NOT NULL,
  coddpto NUMBER(10),
  CONSTRAINT fk_ciudad_dpto FOREIGN KEY (coddpto) REFERENCES dpto (codigo)
);

CREATE TABLE sucursal (
  codigo NUMBER(10) PRIMARY KEY,
  nombre VARCHAR2(25) NOT NULL,
  direccion VARCHAR2(25) NOT NULL,
  codciudad NUMBER(10),
  CONSTRAINT fk_sucursal_ciudad FOREIGN KEY (codciudad) REFERENCES ciudad (codigo)
);

CREATE TABLE marca (
  nombre VARCHAR2(25) PRIMARY KEY,
  descripcion VARCHAR2(100) NOT NULL
);

CREATE TABLE producto (
  codbarras NUMBER(20) PRIMARY KEY,
  nombre VARCHAR2(25) NOT NULL,
  tipo VARCHAR2(20) NOT NULL,
  nommarca VARCHAR2(25),
  CONSTRAINT fk_producto_marca FOREIGN KEY (nommarca) REFERENCES marca (nombre)
);

CREATE TABLE gremio (
  codigo NUMBER(10) PRIMARY KEY,
  nombre VARCHAR2(25) NOT NULL
);

CREATE TABLE vendedor (
  codigo NUMBER(10) PRIMARY KEY,
  nombre VARCHAR2(50) NOT NULL,
  salario NUMBER(10, 2) NOT NULL,
  codgremio NUMBER(10),
  CONSTRAINT fk_vendedor_gremio FOREIGN KEY (codgremio) REFERENCES gremio (codigo)
);

CREATE TABLE venta (
  codigo NUMBER(10) PRIMARY KEY,
  valor NUMBER(10, 2) NOT NULL,
  codvendedor NUMBER(10),
  codproducto NUMBER(20),
  codsucursal NUMBER(10),
  CONSTRAINT fk_venta_vendedor FOREIGN KEY (codvendedor) REFERENCES vendedor (codigo),
  CONSTRAINT fk_venta_producto FOREIGN KEY (codproducto) REFERENCES producto (codbarras),
  CONSTRAINT fk_venta_sucursal FOREIGN KEY (codsucursal) REFERENCES sucursal (codigo)
);