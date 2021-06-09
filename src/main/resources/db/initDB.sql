DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS tools;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS types;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS start_seq;

CREATE SEQUENCE global_seq START WITH 100000;
CREATE SEQUENCE start_seq START WITH 1;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_name_idx ON users (name);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE locations
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('start_seq'),
    name       TEXT      NOT NULL
);
CREATE UNIQUE INDEX locations_unique_name_idx ON locations (name);

CREATE TABLE types
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       TEXT                               NOT NULL,
    parent_id  INTEGER                            NOT NULL,
    level      INTEGER                            NOT NULL,
    final_type BOOLEAN            DEFAULT FALSE  NOT NULL
);
CREATE UNIQUE INDEX types_unique_name_parent_id_idx ON types (name, parent_id);

CREATE TABLE tools
(
    id                  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id             INTEGER   NOT NULL,
    registration_date   DATE      NOT NULL,
    description         TEXT      NOT NULL,
    tools_count         INT       NOT NULL,
    manufacturer        TEXT      NOT NULL,
    location            INTEGER   NOT NULL,
    deficiency          INT       NOT NULL,
    type                INTEGER   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (location) REFERENCES locations (id) ON DELETE CASCADE,
    FOREIGN KEY (type) REFERENCES types (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX tools_unique_description_location_idx ON tools (description, location);