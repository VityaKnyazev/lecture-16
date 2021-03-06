CREATE TABLE IF NOT EXISTS `teacher_details` (
`id` INT NOT NULL AUTO_INCREMENT,
`subject` VARCHAR(25) NOT NULL,
`email` VARCHAR(25) NOT NULL,
`school_number` INT NOT NULL,

PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `teacher` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(25) NOT NULL,
`age` INT NOT NULL,
`teacher_details_id` INT NOT NULL,

PRIMARY KEY(`id`),

KEY `FK_TEACHER_DETAILS` (`teacher_details_id`),
CONSTRAINT `FK_TEACHER_DETAILS_ID` FOREIGN KEY (`teacher_details_id`)
REFERENCES `teacher_details` (`id`)
ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;