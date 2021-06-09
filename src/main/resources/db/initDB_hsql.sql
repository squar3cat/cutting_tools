DROP TABLE user_roles IF EXISTS;
DROP TABLE tools IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE locations IF EXISTS;
DROP SEQUENCE GLOBAL_SEQ IF EXISTS;
DROP SEQUENCE START_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;
CREATE SEQUENCE START_SEQ AS INTEGER START WITH 1;

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_name_idx
    ON USERS (name);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE locations
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE START_SEQ PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);

CREATE TABLE tools
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    registration_date   DATE         NOT NULL,
    description         VARCHAR(255) NOT NULL,
    tools_count         INT          NOT NULL,
    manufacturer        VARCHAR(255) NOT NULL,
    location            INTEGER      NOT NULL,
    deficiency          INTEGER      NOT NULL,
    user_id             INTEGER      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
    FOREIGN KEY (location) REFERENCES locations (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX tools_unique_description_location_idx
    ON tools (description, location)