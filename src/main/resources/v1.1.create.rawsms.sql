CREATE TABLE moneygraph.rawsms
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    sms_body LONGTEXT,
    sms_date BIGINT,
    device_id VARCHAR (512)
);