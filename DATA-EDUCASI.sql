INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES	('ANIVERSARIO','10','70','2021-12-05','A','A');

INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('PINTADO','10','50','2021-05-11','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('MANTENIMIENTO','9','50','2021-12-14','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('EXCURSION','12','30','2021-12-14','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('MANTENIMIENTO CARPET','10','50','2021-03-16','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('REPARAR CARPETAS','5','50','2021-03-16','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('COMPRAR PODADORAS DE CESPED','5','40','2021-05-20','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('REPARAR COMPUTADORAS','5','70','2021-09-25','A','A');
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,ESTFINACT) 
VALUES		('PROFESOR DE MUSICA','10','30','2021-05-27','A','A');

        
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values ('EDGARD','RODRIGUEZ','Quilmana','edgar124@gmail.com','JR. CHINCHA-IMPERIAL','44248333','960163827','APODERADO','ACTIVO');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values         ('LUCAS','CARRANZA','Quilmana','lucas223@gmail.com','JR. PUNO-IMPERIAL','40230753','958159127','APODERADO','ACTIVO');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values 		('ANTHONY','VELARDE','AntVelar','velarde332@gmail.com','JR. AYACUCHO-','12558199','984821652','APODERADO','ACTIVO');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values 		('MARTHA','SANCHEZ','SanMart','martha223@gmail.com','JR. ICA-IMPERIAL','52022189','970456787','APODERADO','ACTIVO');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values 		('MATEO','FELIX','MatFelix','mateo223@gmail.com','AV. 9 DICIEMBRE ','33842703','946078090','APODERADO','ACTIVO');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values 		('FERNANDO','MARTINEZ','Imperial','fernandito@gmail.com','ARC.CENTRAL-','72717476','946078090','APODERADO','ACTIVO');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values 		('MARCOS','MEZA','MarcMez','Marco999@gmail.com','AV. 28 DE JULIO','12833407','952933352','ADMIN','ACTIVO');
        


insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER,PERSONA_IDPER)
VALUES  ('ANDERSON','HUAMAN','Quilmana','edgar124@gmail.com','JR. AREQUIPA','12548590','985417252','ALUMNO','ACTIVO','3');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER,PERSONA_IDPER)
VALUES 		('FRANCISCO','PERALTA','Quilmana','edgar124@gmail.com','JR. LAMBAYEQUE','38726412','962565840','ALUMNO','ACTIVO','4');
insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER,PERSONA_IDPER)
VALUES 		('JUAN','ALBERDI','Quilmana','JuanP@gmail.com','JR. SUCRE-','83923460','960361973','ALUMNO','ACTIVO','4');
        
        
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values	('10','9','2021-06-20','2','2');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('12','10','2021-06-21','4','3');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('10','8','2021-06-22','5','4');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('9','9','2021-06-22','3','5');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('5','5','2021-06-22','6','6');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('5','5','2021-06-23','7','2');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('9','9','2021-06-23','3','5');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('5','5','2021-06-24','8','4');
insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) 
values		('10','10','2021-06-24','2','3');
        
        
--SELECT * FROM actividad;
        
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES ('90','COMPRA DE MATERIALES','2021-12-06','1','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES  ('50','COMPRA DE PINTURA','2021-05-12','2','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('54','COMPRA DE MATERIAL DE LIMPIEZA','2021-12-15','3','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('80','GASTOS DE ALIMENTACION','2021-12-16','4','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('100','COMPRA DE IMPLEMENTOS','2021-03-17','5','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('120','PAGO DE PERSONAL','2021-03-18','6','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('60','REPUESTOS DE PODADORA','2021-05-21','7','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('45','ADAPTADOR DE COMPUTADORA','2021-09-26','8','A');
INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT,ESTGASACT) 
VALUES	   ('85','EQUIPO DE SONIDO','2021-05-28','9','A');

