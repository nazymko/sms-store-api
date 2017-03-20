CREATE TABLE moneygraph.parsedsms_details
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `key` VARCHAR(1024),
    value LONGTEXT,
    value_type VARCHAR(64),
    parsedsms_id BIGINT
);

