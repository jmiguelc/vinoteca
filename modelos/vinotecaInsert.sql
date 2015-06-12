/*TABLA PERSONA*/
INSERT INTO PERSONA VALUES('38928345H','Miguel','Candelas Fuerte','C\Segismundo 3 2ºB','947508824','mcandelas@gmail.com','00490089854598725158');
INSERT INTO PERSONA VALUES('12345678N','Juan','Cuesta Arribas','C\Leopoldo 15 3ºA','699475232','jcuestaa@gmail.com','00490079235641289654');
INSERT INTO PERSONA VALUES('54896213V','Maria','Garcia Perez','C\De las Heras 9 5ºR','654121312','magape@gmail.com','00490015684225452485');
INSERT INTO PERSONA VALUES('71102598A','Carmen','Gomez Prieto','C\De la Amargura 15 1ºC','983252627','carmencita@gmail.com','00490062931596705980');
INSERT INTO PERSONA VALUES('81127345Z','Javier','Ferrero Nadal','C\Cristóbal Colón 54 9ºB','678258946','javiFerrero@gmail.com','00490069103596482103');
INSERT INTO PERSONA VALUES('26395175G','Sonia','Acebo Hernandez','C\Suspiros de Monja 23 7ºE','947512637','aceboSonia@gmail.com','00490075325697402597');

/*TABLA EMPLEADO*/
INSERT INTO EMPLEADO VALUES('jcuestaa','12345678N','jcuestaa','10/29/2000','P');
INSERT INTO EMPLEADO VALUES('carmencita','71102598A','carmencita','02/15/2002','C');
INSERT INTO EMPLEADO VALUES('javiFerrero','81127345Z','javiFerrero','05/08/2001','A');

/*TABLA ABONADO*/
INSERT INTO ABONADO VALUES(1000,'mcandelas','38928345H');
INSERT INTO ABONADO VALUES(1001,'magape','54896213V');
INSERT INTO ABONADO VALUES(1002,'aceboSonia','26395175G');

/*TABLA FACTURA*/
INSERT INTO FACTURA  VALUES(1,'2012-10-29',29,'V',NULL,NULL);
INSERT INTO FACTURA  VALUES(2,'2015-05-30',180,'E',NULL,NULL);
INSERT INTO FACTURA  VALUES(3,'2013-10-01',45,'P','2013-10-25','FP4986');


/*TABLA PEDIDO*/
INSERT INTO PEDIDO  VALUES(1,'2012-10-26','NOTA DE ENTREGA 1',9,'2012-10-27','2012-10-28','F',1000,1);
INSERT INTO PEDIDO  VALUES(2,'2012-10-26','NOTA DE ENTREGA 2',20,'2012-10-27','2012-10-28','F',1000,1);
INSERT INTO PEDIDO  VALUES(3,'2012-05-28','NOTA DE ENTREGA 3',180,'2012-05-29','2012-06-01','A',1001,2);
INSERT INTO PEDIDO  VALUES(4,'2013-07-14','NOTA DE ENTREGA 4',45,'2012-07-29','2013-06-15','C',1002,3);

/*TABLA BODEGA*/
INSERT INTO BODEGA VALUES(1,'Tierra Aranda','A12345678BG','Pza/ del Trigo 1');
INSERT INTO BODEGA VALUES(2,'Marques de Velilla','B87654321CD','C/ Juan Alvarez Mendizabal 37');
INSERT INTO BODEGA VALUES(3,'Virgen de las viñas','D12545678AG','Ctra Madrid-Burgos s/n');
INSERT INTO BODEGA VALUES(4,'Grandes viñas y viñedos','A11145779AE','Ctra Valencia s/n');
INSERT INTO BODEGA VALUES(5,'Garcia de Olano','A11595669BE','Ctra de Vitoria s/n');

/*TABLA DENOMINACION*/
INSERT INTO DENOMINACIONORIGEN  VALUES(1,'Ribera del Duero');
INSERT INTO DENOMINACIONORIGEN  VALUES(2,'Cariñena');
INSERT INTO DENOMINACIONORIGEN  VALUES(3,'Rioja');

/*TABLA VINO*/
INSERT INTO VINO  VALUES(1,'Tierra Aranda Joven',2014,'Vino joven de Aranda','A',1,1);
INSERT INTO VINO  VALUES(2,'Marques de Velilla',2000,'Vino tinto Marques de Velilla','G',1,2);
INSERT INTO VINO  VALUES(3,'Monte Villalobón',1998,'Vino tinto','A',1,2);
INSERT INTO VINO  VALUES(4,'Viña hijosa',2000,'Vino de Aranda','A',1,3);
INSERT INTO VINO  VALUES(5,'Corona de Aragón',2003,'Vino blanco reserva','R',2,4);
INSERT INTO VINO  VALUES(6,'Olanum',2014,'Vino joven de Logroño','A',3,5);
INSERT INTO VINO  VALUES(7,'Mauleón',2014,'Vino gran reserva Rioja','G',3,5);

/*TABLA REFERENCIA*/
INSERT INTO REFERENCIA  VALUES(1,'1',75,1.5,'1',1);
INSERT INTO REFERENCIA  VALUES(2,'1',75,2.4,'1',2);
INSERT INTO REFERENCIA  VALUES(3,'1',75,1.8,'1',3);
INSERT INTO REFERENCIA  VALUES(4,'1',75,2.0,'1',4);
INSERT INTO REFERENCIA  VALUES(5,'1',75,1.5,'0',5);
INSERT INTO REFERENCIA  VALUES(6,'1',75,2.7,'1',6);
INSERT INTO REFERENCIA  VALUES(7,'1',75,3.0,'1',7);

/*TABLA PREFERENCIA*/
INSERT INTO PREFERENCIA  VALUES('A',1,1000);
INSERT INTO PREFERENCIA  VALUES('R',3,1001);
INSERT INTO PREFERENCIA  VALUES('G',3,1002);

/*TABLA LINEAPEDIDO*/
INSERT INTO LINEAPEDIDO VALUES(1,6,'F',1,1,1);

/*TABLA LINEACOMPRA*/
INSERT INTO LINEACOMPRA VALUES(1,6,'F',NULL,1);

/*TABLA COMPRA*/
INSERT INTO COMPRA VALUES(1,NULL,'F',NULL,100,'F',NULL,1);

--INSERT INTO LINEACOMPRA VALUES(2,3,'F','2015-06-10',1);
--INSERT INTO LINEACOMPRA VALUES(3,4,'F',NULL,1);