CREATE TABLE client(
	id                 UUID NOT NULL,
	client_id          VARCHAR(255) NOT NULL,
	client_secret      VARCHAR(255) NOT NULL,
	created_date       TIMESTAMP(6) NOT NULL,
	last_modified_date TIMESTAMP(6) NOT NULL,
	redirect_uri       VARCHAR(255) NOT NULL,
	scope              VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_account(
	id                 UUID NOT NULL,
	created_date       TIMESTAMP(6) NOT NULL,
	email              VARCHAR(100) NOT NULL,
	last_modified_date TIMESTAMP(6) NOT NULL,
	name               VARCHAR(100) NOT NULL,
	password           VARCHAR(200) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE user_account_role(
	user_account_id UUID NOT NULL,
	ROLES           VARCHAR(255)
);

ALTER TABLE IF exists client ADD CONSTRAINT client_client_id UNIQUE (client_id);

ALTER TABLE IF exists user_account ADD CONSTRAINT user_account_email UNIQUE (email);

ALTER TABLE IF exists user_account_role ADD CONSTRAINT fk_user_account_id FOREIGN KEY (user_account_id) REFERENCES user_account;