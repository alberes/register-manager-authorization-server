SELECT * FROM client;
SELECT * FROM user_account;
SELECT * FROM user_account_role;


INSERT INTO  client(ID, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, SCOPE, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES (gen_random_uuid(), 'my-client-id', '$2a$12$UKQvBMFvDSOwRdPbPCiY0eXSt9rSpWgiFG2Wi5GrpQV6yqAEya0Be', 'http://localhost:8081', 'USER', NOW(), NOW());

INSERT INTO user_account(ID, NAME, EMAIL, PASSWORD, CREATED_DATE, LAST_MODIFIED_DATE)
VALUES (gen_random_uuid(), 'Admin Manager Data', 'admin@admin.com', '$2a$12$jyoZ3UQBbkYll/oDXIcYh.KJFn6xiVcVs./28e/IWpi5i4SyVSQne', NOW(), NOW());

INSERT INTO user_account_role(USER_ACCOUNT_ID, ROLES) VALUES(
  (SELECT id FROM user_account WHERE email = 'admin@admin.com'), 'ADMIN');