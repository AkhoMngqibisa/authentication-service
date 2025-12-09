CREATE TABLE refresh_tokens (
        id                     BIGINT               AUTO_INCREMENT  PRIMARY KEY
,       token                  VARCHAR(255)         NOT NULL UNIQUE
,       user_id                 BIGINT
,       expiry_date             TIMESTAMP

,       CONSTRAINT fk_refresh_tokens_user   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);