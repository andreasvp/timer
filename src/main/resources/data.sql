CREATE TABLE metimer (
  id BIGINT NOT NULL,
  title VARCHAR(255),
  beschreibung VARCHAR(255),
  sprecher VARCHAR(255),
  zeitpunkt TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT pk_metimer PRIMARY KEY (id)
);

INSERT INTO metimer (id, title, beschreibung, sprecher, zeitpunkt) VALUES
(1, 'Docker', '.... in dem Entwicklungsprozess nutzen und die Vorteile werden uns glücklicher und besser machen.', 'MH', PARSEDATETIME('2023-10-17 20:30:09', 'yyyy-MM-DD HH:mm:ss'));
INSERT INTO metimer (id, title, beschreibung, sprecher, zeitpunkt) VALUES
(2, 'Kubernetes', '..... nutzen und die Vorteile werden uns glücklicher und besser machen.', 'MH', PARSEDATETIME('2028-10-27 10:45:34', 'yyyy-MM-DD HH:mm:ss'));
INSERT INTO metimer (id, title, beschreibung, sprecher, zeitpunkt) VALUES
(3, 'Rente', '..... endlich Rente.......', 'AvP', PARSEDATETIME('2036-07-10 19:00:00', 'yyyy-MM-DD HH:mm:ss'));