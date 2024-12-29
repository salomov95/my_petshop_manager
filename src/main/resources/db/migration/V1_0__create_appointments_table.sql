CREATE TABLE IF NOT EXISTS `tb_appointments` (
  `id` VARCHAR(255) PRIMARY KEY NOT NULL,
  `descriptor` VARCHAR(255) NOT NULL,
  `pet_tutor_name` VARCHAR(128) NOT NULL,
  `pet_name` VARCHAR(64) NOT NULL,
  `contact_phone` VARCHAR(64) NOT NULL,
  `due_date` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
