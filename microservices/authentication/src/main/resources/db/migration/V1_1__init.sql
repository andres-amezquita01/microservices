CREATE EXTENSION pgcrypto;

CREATE TABLE AGENTS (
  "id" UUID PRIMARY KEY DEFAULT (gen_random_uuid()),
  "identification_code" TEXT,
  "name" TEXT NOT NULL,
  "last_name" TEXT,
  "phone" VARCHAR(30),
  "email" VARCHAR(254) UNIQUE NOT NULL ,
  "created_at" TIMESTAMP NOT NULL DEFAULT (now()),
  "updated_at" TIMESTAMP NOT NULL DEFAULT (now())
);

CREATE TABLE ROLES (
  "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "name" TEXT NOT NULL,
  "description" TEXT,
  "created_at" TIMESTAMP NOT NULL DEFAULT (now()),
  "updated_at" TIMESTAMP NOT NULL DEFAULT (now())
);

CREATE TABLE USERS (
  "id" UUID PRIMARY KEY DEFAULT (gen_random_uuid()),
  "username" TEXT UNIQUE,
  "password" TEXT,
  "description" TEXT,
  "source" TEXT NOT NULL,
  "agent_id" UUID NOT NULL,
  "external_id" TEXT,
  "created_at" TIMESTAMP NOT NULL DEFAULT (now()),
  "updated_at" TIMESTAMP NOT NULL DEFAULT (now())
);

CREATE TABLE USER_ROLES (
  "role_id" INT NOT NULL,
  "user_id" UUID NOT NULL
);

CREATE TABLE PERMISSIONS (
  "id" INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  "name" TEXT NOT NULL,
  "access_module" TEXT NOT NULL,
  "role_id" INT NOT NULL,
  "created_at" TIMESTAMP NOT NULL DEFAULT (now()),
  "updated_at" TIMESTAMP NOT NULL DEFAULT (now())
);

ALTER TABLE USERS ADD CONSTRAINT "fk_USERS_AGENT1" FOREIGN KEY ("agent_id") REFERENCES AGENTS ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE USER_ROLES ADD CONSTRAINT "fk_USER_ROLES_ROLES1" FOREIGN KEY ("role_id") REFERENCES ROLES ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE USER_ROLES ADD CONSTRAINT "fk_USER_ROLES_USERS1" FOREIGN KEY ("user_id") REFERENCES USERS ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE PERMISSIONS ADD CONSTRAINT "fk_PERMISSIONS_ROLES1" FOREIGN KEY ("role_id") REFERENCES ROLES ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
