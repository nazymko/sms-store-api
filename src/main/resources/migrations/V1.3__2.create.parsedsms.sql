CREATE TABLE moneygraph.parsedsms
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rawsms_id BIGINT,
    address_from VARCHAR(64),
    body LONGTEXT,
    date TIMESTAMP
);
ALTER TABLE moneygraph.parsedsms ADD identification VARCHAR(512) NULL;
CREATE UNIQUE INDEX parsedsms_id_uindex ON moneygraph.parsedsms (id);
CREATE INDEX parsedsms_address_from_index ON moneygraph.parsedsms (address_from);
CREATE INDEX parsedsms_date_index ON moneygraph.parsedsms (date);
CREATE INDEX parsedsms_identification_index ON moneygraph.parsedsms (identification);