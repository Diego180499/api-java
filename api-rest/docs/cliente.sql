CREATE DATABASE salon;


/*TABLA DE USUARIO*/

CREATE TABLE usuario(

`id` int(11) auto_increment,
`nombre`  varchar(30),
`apellido` varchar(30),
`email` varchar(60),
`telefono` varchar(10),
`admin` bool ,
`confirmado` bool,
`token` varchar(20),
`password` varchar(60),
PRIMARY KEY (`id`)
);




/*TABLA DE SERVICIO*/

CREATE TABLE `salon`.`servicio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `precio` DOUBLE(5,2) NOT NULL,
  PRIMARY KEY (`id`));
  
  
  
  /*TABLA DE CITA*/
  
  CREATE TABLE `salon`.`cita` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NULL,
  `usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_idx` (`usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `salon`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);




/*TABLA PIVOTE DE CITA-SERVICIO*/

CREATE TABLE `salon`.`citaservicio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cita` INT NOT NULL,
  `servicio` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cita_idx` (`cita` ASC) VISIBLE,
  INDEX `fk_servicio_idx` (`servicio` ASC) VISIBLE,
  CONSTRAINT `fk_cita`
    FOREIGN KEY (`cita`)
    REFERENCES `salon`.`cita` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servicio`
    FOREIGN KEY (`servicio`)
    REFERENCES `salon`.`servicio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




/*----PRUEBAS----*/

USE salon;

SELECT * FROM usuario;




