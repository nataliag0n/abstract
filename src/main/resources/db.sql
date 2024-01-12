 CREATE database demo;
 
 CREATE TABLE demo.demo (
  id int NOT NULL AUTO_INCREMENT primary key,
  name varchar(30), 
  distancefromsun bigint,
  angularvelocity bigint,
  clockwise boolean
 );
 
INSERT INTO demo.demo (name, distancefromsun, angularvelocity, clockwise) VALUES ("Ferengi", 500, 1, true); 
INSERT INTO demo.demo (name, distancefromsun, angularvelocity, clockwise) VALUES ("Betasoide", 2000, 3, true); 
INSERT INTO demo.demo (name, distancefromsun, angularvelocity, clockwise) VALUES ("Vulcano", 1000, 5, false); 

SET @@sql_mode = '';