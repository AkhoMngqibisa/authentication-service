CREATE TABLE audit (
         id                         BIGINT                  AUTO_INCREMENT PRIMARY KEY
,        email                      VARCHAR(255)            NOT NULL
,        success                    BOOLEAN                 NOT NULL
,        ip_address                 VARCHAR(255)            NOT NULL
,        user_agent                 VARCHAR(255)
,        failure_reason             VARCHAR(255)
,        created_at                 TIMESTAMP               DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_email ON audit(email);
CREATE INDEX idx_timestamp ON audit(created_at);