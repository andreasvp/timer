CREATE TABLE metimer (
  id BIGINT NOT NULL,
  title VARCHAR(255),
  beschreibung VARCHAR(255),
  sprecher VARCHAR(255),
  zeitpunkt TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT pk_metimer PRIMARY KEY (id)
);