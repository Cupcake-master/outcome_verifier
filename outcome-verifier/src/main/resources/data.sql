CREATE DATABASE outcome_verifier;

INSERT INTO users(name, surname, patronymic, squad, email, password, created, updated, status, token)
VALUES ('Bulat', 'Bilalov', 'Foatovich', '11-906', 'bulat.bilalov.orion@gmail.com',
        '$2a$10$qBXy8/nnwfwQFtL4hz5DOOXnpMI1eeYL0XTTG50gnMMgnZlDKkHTe', now(), now(), 'ACTIVE', null);

INSERT INTO roles(name, created, updated, status) VALUES ('ROLE_STUDENT', now(), now(), 'ACTIVE');