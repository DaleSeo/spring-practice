CREATE TABLE examples (
id INTEGER NOT NULL,
name CHAR(25),
description VARCHAR(25) NOT NULL,
date DATE,
PRIMARY KEY (id));

INSERT INTO examples VALUES(1, 'Dale', 'Dale is cute.', sysdate);
INSERT INTO examples VALUES(2, 'Kate', 'Kate is pretty.', sysdate);
INSERT INTO examples VALUES(3, 'Yeji', 'Yeji is busy.', sysdate);
