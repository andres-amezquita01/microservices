CREATE TABLE IF NOT EXISTS `SPOT_TYPES` (
  `id` integer PRIMARY KEY,
  `name` VARCHAR(250) NOT NULL,
  `description` TEXT,
  `color` VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS `SPOTS` (
  `id` integer PRIMARY KEY,
  `name` VARCHAR(250) NOT NULL,
  `floor_id` integer NOT NULL,
  `location_x` FLOAT,
  `location_y` FLOAT,
  `spot_type_id` integer,
  FOREIGN KEY (spot_type_id) REFERENCES SPOT_TYPES (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (floor_id) REFERENCES FLOORS (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `FLOORS` (
  `id` integer PRIMARY KEY,
  `name` VARCHAR(250) NOT NULL,
  `building_id` integer NOT NULL,
  FOREIGN KEY (building_id) REFERENCES BUILDINGS (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `BUILDINGS` (
  `id` integer PRIMARY KEY,
  `name` VARCHAR(250) NOT NULL
)
