DROP TABLE LINEAPEDIDO;
DROP TABLE LINEACOMPRA;
DROP TABLE COMPRA;
DROP TABLE PEDIDO;
DROP TABLE ESTADOPEDIDO;
DROP TABLE FACTURA;
DROP TABLE ESTADOFACTURA;
DROP TABLE PREFERENCIA;
DROP TABLE ABONADO;
DROP TABLE EMPLEADO;
DROP TABLE PERSONA;
DROP TABLE REFERENCIA;
DROP TABLE VINO;
DROP TABLE DENOMINACIONORIGEN;
DROP TABLE CATEGORIA;
DROP TABLE BODEGA;


CREATE TABLE Bodega (
 id INT NOT NULL,
 nombre VARCHAR(50) NOT NULL,
 cif VARCHAR(20),
 direccion VARCHAR(50)
);

ALTER TABLE Bodega ADD CONSTRAINT PK_Bodega PRIMARY KEY (id);


CREATE TABLE Categoria (
 clave CHAR NOT NULL,
 nombre VARCHAR(20)
);

ALTER TABLE Categoria ADD CONSTRAINT PK_Categoria PRIMARY KEY (clave);


CREATE TABLE Compra (
 idCompra INT NOT NULL,
 fechaInicioCompra DATE,
 recibidaCompleta CHAR,
 fechaCompraCompletada DATE,
 importe REAL,
 pagada CHAR,
 fechaPago DATE,
 idBodega INT NOT NULL
);

ALTER TABLE Compra ADD CONSTRAINT PK_Compra PRIMARY KEY (idCompra);


CREATE TABLE DenominacionOrigen (
 id INT NOT NULL,
 nombre VARCHAR(50)
);

ALTER TABLE DenominacionOrigen ADD CONSTRAINT PK_DenominacionOrigen PRIMARY KEY (id);


CREATE TABLE EstadoFactura (
 clave CHAR NOT NULL,
 nombre VARCHAR(20)
);

ALTER TABLE EstadoFactura ADD CONSTRAINT PK_EstadoFactura PRIMARY KEY (clave);


CREATE TABLE EstadoPedido (
 clave CHAR NOT NULL,
 nombre VARCHAR(20)
);

ALTER TABLE EstadoPedido ADD CONSTRAINT PK_EstadoPedido PRIMARY KEY (clave);


CREATE TABLE Factura (
 numeroFactura INT NOT NULL,
 fechaEmision DATE,
 importe REAL,
 estado CHAR NOT NULL,
 fechaPago DATE,
 idExtractoBancario VARCHAR(30)
);

ALTER TABLE Factura ADD CONSTRAINT PK_Factura PRIMARY KEY (numeroFactura);


CREATE TABLE LineaCompra (
 id INT NOT NULL,
 unidades SMALLINT,
 recibida CHAR,
 fechaRecepcion DATE,
 idCompra INT NOT NULL
);

ALTER TABLE LineaCompra ADD CONSTRAINT PK_LineaCompra PRIMARY KEY (id);


CREATE TABLE Persona (
 nif VARCHAR(9) NOT NULL,
 nombre VARCHAR(50),
 apellidos VARCHAR(50),
 direccion VARCHAR(50),
 telefono VARCHAR(50),
 email VARCHAR(50),
 cuentaBancaria VARCHAR(30)
);

ALTER TABLE Persona ADD CONSTRAINT PK_Persona PRIMARY KEY (nif);


CREATE TABLE Vino (
 id INT NOT NULL,
 nombreComercial VARCHAR(50),
 año SMALLINT,
 comentario VARCHAR(200),
 idCategoria CHAR,
 idDenominacion INT,
 idBodega INT NOT NULL
);

ALTER TABLE Vino ADD CONSTRAINT PK_Vino PRIMARY KEY (id);


CREATE TABLE Abonado (
 numeroAbonado INT NOT NULL,
 openIDRef VARCHAR(50),
 nif VARCHAR(9) NOT NULL
);

ALTER TABLE Abonado ADD CONSTRAINT PK_Abonado PRIMARY KEY (numeroAbonado);


CREATE TABLE Empleado (
 login VARCHAR(20) NOT NULL,
 nif VARCHAR(9) NOT NULL,
 password VARCHAR(20),
 fechaInicio DATE,
 tipoEmpleado CHAR
);

ALTER TABLE Empleado ADD CONSTRAINT PK_Empleado PRIMARY KEY (login);


CREATE TABLE Pedido (
 numero INT NOT NULL,
 fechaRealizacion DATE,
 notaEntrega VARCHAR(200),
 importe REAL,
 fechaRecepcion DATE,
 fechaEntrega DATE,
 estado CHAR NOT NULL,
 numeroAbonado INT NOT NULL,
 numeroFactura INT NOT NULL
);

ALTER TABLE Pedido ADD CONSTRAINT PK_Pedido PRIMARY KEY (numero);


CREATE TABLE Preferencia (
 categoria CHAR NOT NULL,
 idDenominacion INT NOT NULL,
 numeroAbonado INT NOT NULL
);


CREATE TABLE Referencia (
 codigo INT NOT NULL,
 esPorCajas CHAR,
 contenidoEnCL SMALLINT,
 precio REAL,
 disponible CHAR,
 id INT
);

ALTER TABLE Referencia ADD CONSTRAINT PK_Referencia PRIMARY KEY (codigo);


CREATE TABLE LineaPedido (
 id INT NOT NULL,
 unidades INT,
 completada CHAR,
 numeroPedido INT NOT NULL,
 idLineaCompra INT,
 codigo INT
);

ALTER TABLE LineaPedido ADD CONSTRAINT PK_LineaPedido PRIMARY KEY (id);


ALTER TABLE Factura ADD CONSTRAINT FK_Factura_0 FOREIGN KEY (estado) REFERENCES EstadoFactura (clave);


ALTER TABLE LineaCompra ADD CONSTRAINT FK_LineaCompra_0 FOREIGN KEY (idCompra) REFERENCES Compra (idCompra);


ALTER TABLE Vino ADD CONSTRAINT FK_Vino_0 FOREIGN KEY (idCategoria) REFERENCES Categoria (clave);
ALTER TABLE Vino ADD CONSTRAINT FK_Vino_1 FOREIGN KEY (idDenominacion) REFERENCES DenominacionOrigen (id);
ALTER TABLE Vino ADD CONSTRAINT FK_Vino_2 FOREIGN KEY (idBodega) REFERENCES Bodega (id);


ALTER TABLE Abonado ADD CONSTRAINT FK_Abonado_0 FOREIGN KEY (nif) REFERENCES Persona (nif);


ALTER TABLE Empleado ADD CONSTRAINT FK_Empleado_0 FOREIGN KEY (nif) REFERENCES Persona (nif);


ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_0 FOREIGN KEY (estado) REFERENCES EstadoPedido (clave);
ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_1 FOREIGN KEY (numeroAbonado) REFERENCES Abonado (numeroAbonado);
ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_2 FOREIGN KEY (numeroFactura) REFERENCES Factura (numeroFactura);


ALTER TABLE Preferencia ADD CONSTRAINT FK_Preferencia_0 FOREIGN KEY (categoria) REFERENCES Categoria (clave);
ALTER TABLE Preferencia ADD CONSTRAINT FK_Preferencia_1 FOREIGN KEY (idDenominacion) REFERENCES DenominacionOrigen (id);
ALTER TABLE Preferencia ADD CONSTRAINT FK_Preferencia_2 FOREIGN KEY (numeroAbonado) REFERENCES Abonado (numeroAbonado);


ALTER TABLE Referencia ADD CONSTRAINT FK_Referencia_0 FOREIGN KEY (id) REFERENCES Vino (id);


ALTER TABLE LineaPedido ADD CONSTRAINT FK_LineaPedido_0 FOREIGN KEY (numeroPedido) REFERENCES Pedido (numero);
ALTER TABLE LineaPedido ADD CONSTRAINT FK_LineaPedido_1 FOREIGN KEY (idLineaCompra) REFERENCES LineaCompra (id);
ALTER TABLE LineaPedido ADD CONSTRAINT FK_LineaPedido_2 FOREIGN KEY (codigo) REFERENCES Referencia (codigo);

ALTER TABLE Compra ADD CONSTRAINT FK_Compra_0 FOREIGN KEY (idBodega) REFERENCES Bodega (id);
