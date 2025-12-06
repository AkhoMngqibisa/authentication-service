CREATE TABLE users (
        id                  BIGINT              AUTO_INCREMENT PRIMARY KEY
,       email               VARCHAR(255)        NOT NULL UNIQUE
,       password            VARCHAR(255)        NOT NULL
,       role                VARCHAR(50)         NOT NULL
,       enabled             BOOLEAN             DEFAULT TRUE
,       failed_attempts     INT                 DEFAULT 0
,       account_locked      BOOLEAN             DEFAULT FALSE
);