ALTER TABLE token_temporary MODIFY expired_date TINYINT(1);
ALTER TABLE token_temporary RENAME COLUMN expired_date TO expired;
