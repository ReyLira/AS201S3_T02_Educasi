ALTER SESSION SET nls_date_format = 'YYYY-MM-DD';
-- tables
-- Table: ACTIVIDAD
CREATE TABLE actividad (
    idact     INT NOT NULL,
    nomact    VARCHAR2(60) NOT NULL,
    monespact INT NOT NULL,
    canapoact INT NOT NULL,
    fecact    DATE NOT NULL,
    estact    CHAR(1) DEFAULT 'A' NOT NULL,
    estfinact CHAR(1) DEFAULT 'C' NOT NULL,
    CONSTRAINT actividad_pk PRIMARY KEY ( idact )
);

-- Table: CRONOGRAMAS_COMUNICADOS
CREATE TABLE cronogramas_comunicados (
    idcron INT NOT NULL,
    imacro BLOB NOT NULL,
    CONSTRAINT cronogramas_comunicados_pk PRIMARY KEY ( idcron )
);

-- Table: CUOTA
CREATE TABLE cuota (
    idcuot  INT NOT NULL,
    cancuot INT NOT NULL,
    moncuot INT NOT NULL,
    feccuot DATE NOT NULL,
    idact   INT NOT NULL,
    idper   INT NOT NULL,
    CONSTRAINT cuota_pk PRIMARY KEY ( idcuot )
);

-- Table: GASTO_ACTIVIDAD
CREATE TABLE gasto_actividad (
    idgasact  INT NOT NULL,
    desgasact VARCHAR2(60) NOT NULL,
    gasact    INT NOT NULL,
    fecgasact DATE NOT NULL,
    estgasact CHAR(1) NOT NULL,
    idact     INT NOT NULL,
    CONSTRAINT gasto_actividad_pk PRIMARY KEY ( idgasact )
);

-- Table: PERSONA
CREATE TABLE persona (
    idper         INT NOT NULL,
    nomper        VARCHAR2(20) NOT NULL,
    apeper        VARCHAR2(20) NOT NULL,
    pasper        VARCHAR2(16) NOT NULL,
    emaper        VARCHAR2(20) NOT NULL,
    direper       VARCHAR2(20) NOT NULL,
    dniper        CHAR(8) NOT NULL,
    celper        CHAR(9) NOT NULL,
    rolper        CHAR(9) NOT NULL,
    estper        CHAR(8) DEFAULT 'ACTIVO' NULL,
    persona_idper INT NULL,
    CONSTRAINT persona_pk PRIMARY KEY ( idper )
);

-- foreign keys
-- Reference: CUOTA_ACTIVIDAD (table: CUOTA)
ALTER TABLE cuota
    ADD CONSTRAINT cuota_actividad FOREIGN KEY ( idact )
        REFERENCES actividad ( idact );

-- Reference: CUOTA_PERSONA (table: CUOTA)
ALTER TABLE cuota
    ADD CONSTRAINT cuota_persona FOREIGN KEY ( idper )
        REFERENCES persona ( idper );

-- Reference: PERSONA_PERSONA (table: PERSONA)
ALTER TABLE persona
    ADD CONSTRAINT persona_persona FOREIGN KEY ( persona_idper )
        REFERENCES persona ( idper );

-- Reference: SALDO_ACTIVIDAD_ACTIVIDAD (table: GASTO_ACTIVIDAD)
ALTER TABLE gasto_actividad
    ADD CONSTRAINT saldo_actividad_actividad FOREIGN KEY ( idact )
        REFERENCES actividad ( idact );

-- End of file.



------------------------------------  TRIGGER AND SECUENCE
------------------------------------  TRIGGER AND SECUENCE
------------------------------------  TRIGGER AND SECUENCE


CREATE SEQUENCE sec_persona START WITH 1 INCREMENT BY 1 ORDER;
/
-- Creacion de trigger
CREATE OR REPLACE TRIGGER trig_persona BEFORE
    INSERT ON persona
    FOR EACH ROW
BEGIN
    SELECT
        sec_persona.NEXTVAL
    INTO :new.idper
    FROM
        dual;

END;
/

CREATE SEQUENCE sec_cuota START WITH 1 INCREMENT BY 1 ORDER;
/
-- Creacion de trigger
CREATE OR REPLACE TRIGGER trig_cuota BEFORE
    INSERT ON cuota
    FOR EACH ROW
BEGIN
    SELECT
        sec_cuota.NEXTVAL
    INTO :new.idcuot
    FROM
        dual;

END;
/

CREATE SEQUENCE sec_actividad START WITH 1 INCREMENT BY 1 ORDER;
/
-- Creacion de trigger
CREATE OR REPLACE TRIGGER trig_actividad BEFORE
    INSERT ON actividad
    FOR EACH ROW
BEGIN
    SELECT
        sec_actividad.NEXTVAL
    INTO :new.idact
    FROM
        dual;

END;
/

CREATE SEQUENCE sec_gasto_actividad START WITH 1 INCREMENT BY 1 ORDER;
/
-- Creacion de trigger
CREATE OR REPLACE TRIGGER trig_gasto_actividad BEFORE
    INSERT ON gasto_actividad
    FOR EACH ROW
BEGIN
    SELECT
        sec_gasto_actividad.NEXTVAL
    INTO :new.idgasact
    FROM
        dual;

END;
/

CREATE SEQUENCE sec_cronogramas_comunicados START WITH 1 INCREMENT BY 1 ORDER;
/
-- Creacion de trigger
CREATE OR REPLACE TRIGGER trig_cronogramas_comunicados BEFORE
    INSERT ON cronogramas_comunicados
    FOR EACH ROW
BEGIN
    SELECT
        sec_cronogramas_comunicados.NEXTVAL
    INTO :new.idcron
    FROM
        dual;

END;
/

------------------------------------------ DATA
------------------------------------------ DATA
------------------------------------------ DATA

--vistas
--vista de la tabla cuota
CREATE OR REPLACE VIEW v_cuota AS
    SELECT
        idcuot,
        cancuot,
        moncuot,
        feccuot,
        concat(nomper, apeper) AS nomper,
        nomact,
        actividad.idact,
        persona.idper
    FROM
             cuota
        INNER JOIN persona ON cuota.idper = persona.idper
        INNER JOIN actividad ON cuota.idact = actividad.idact;

--vista de la tabla persona
CREATE OR REPLACE VIEW v_persona AS
    SELECT
        ROW_NUMBER()
        OVER(
            ORDER BY
                super.idper DESC
        )                                  AS fila,
        super.idper,
        super.nomper,
        super.apeper,
        super.pasper,
        super.emaper,
        super.direper,
        super.dniper,
        super.celper,
        super.rolper,
        super.estper,
        concat(infer.nomper, infer.apeper) AS relacion
    FROM
        persona super
        LEFT JOIN persona infer ON super.persona_idper = infer.idper;

-- vista de la tabla rol
CREATE OR REPLACE VIEW v_persona_rol AS
    SELECT
        ROW_NUMBER()
        OVER(
            ORDER BY
                super.idper DESC
        )                                  AS fila,
        super.idper,
        super.nomper,
        super.apeper,
        super.pasper,
        super.emaper,
        super.direper,
        super.dniper,
        super.celper,
        super.rolper,
        super.estper,
        concat(infer.nomper, infer.apeper) AS relacion
    FROM
        persona super
        LEFT JOIN persona infer ON super.persona_idper = infer.idper;

-- vista de la tabla gasto actividad
CREATE OR REPLACE VIEW vw_gasto_actividad AS
    SELECT
        gasto_actividad.idgasact  AS idgasact,
        gasto_actividad.gasact    AS gasact,
        gasto_actividad.desgasact AS desgasact,
        gasto_actividad.fecgasact AS fecgasact,
        gasto_actividad.idact     AS idact,
        actividad.nomact          AS nomact,
        gasto_actividad.estgasact AS estgasact
    FROM
             gasto_actividad
        INNER JOIN actividad ON gasto_actividad.idact = actividad.idact;



--funcion
CREATE OR REPLACE FUNCTION  SaldoCuota
( 
 idActividad int,
  idPersona int
)
return int
is
 monto int;
 acu int;
	
    BEGIN
		select NVL( sum(MONCUOT),0) into acu from CUOTA where IDACT=idActividad and IDPER = idPersona;
		select MONESPACT into monto from ACTIVIDAD where IDACT=idActividad;
        RETURN monto -acu;

end SaldoCuota;

SELECT SaldoCuota(1,1)  AS  saldoCuota from dual;
        