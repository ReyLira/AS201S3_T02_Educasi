USE DBEDUCASI;
INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT) 
VALUES ('Aniversario','10','50','2021-11-05'),
 ('Pintado','10','50','2021-11-05'),
 ('Actividad Navidad','10','50','2021-11-05');

 insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER, ESTPER)
 values ('mark','quispe','QuilGO','ads@gmi.com','jr chincha','72713734','923456721','APODERADO','ACTIVO'),
  ('lucas','carranza','QuilGO','ads@gmi.com','jr chincha','72713734','923456721','APODERADO','ACTIVO'),
  ('chino','velarde','QuilGO','ads@gmi.com','jr chincha','72713734','923456721','APODERADO','ACTIVO');

INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT) 
VALUES ('60','arreglo','2021-05-05','1'),
	   ('50','compra','2021-05-05','2'),
       ('70','minta','2021-05-05','2');

insert into CUOTA (CANCUOT,MONCUOT,ESTCUOT,FECCUOT,IDACT,IDPER) 
values ('10','10','I','2021-03-06','3','3'),
		('10','10','I','2021-03-06','3','2');
