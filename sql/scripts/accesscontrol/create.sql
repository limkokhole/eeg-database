DROP TABLE EXPERIMENT_PACKAGE_CONNECTION;
DROP TABLE EXPERIMENT_PACKAGE_LICENSE;
DROP TABLE PERSONAL_LICENSE;
DROP TABLE EXPERIMENT_PACKAGE;
DROP TABLE LICENSE;

-- added missing hibernate sequence
CREATE SEQUENCE HIBERNATE_SEQUENCE MINVALUE 10000 MAXVALUE 9999999999

CREATE TABLE EXPERIMENT_PACKAGE_CONNECTION(
  EPC_ID NUMBER PRIMARY KEY,
  EXPERIMENT NUMBER,
  EXPERIMENT_PACKAGE NUMBER
);

CREATE TABLE EXPERIMENT_PACKAGE(
  EXPERIMENT_PACKAGE_ID NUMBER PRIMARY KEY,
  NAME VARCHAR(255),
  RESEARCH_GROUP NUMBER
);

CREATE TABLE EXPERIMENT_PACKAGE_LICENSE(
  EXPERIMENT_PACKAGE_LICENSE_ID NUMBER PRIMARY KEY,
  EXPERIMENT_PACKAGE NUMBER,
  LICENSE NUMBER
);

CREATE TABLE LICENSE(
  LICENSE_ID NUMBER PRIMARY KEY,
  RESEARCH_GROUP_ID NUMBER,
  TITLE VARCHAR(255),
  DESCRIPTION VARCHAR(255),
  PRICE NUMBER,
  LICENSE_TYPE NUMBER,
  IS_TEMPLATE NUMBER,
  CONSTRAINT fk_lic_rg FOREIGN KEY (RESEARCH_GROUP_ID) REFERENCES RESEARCH_GROUP (RESEARCH_GROUP_ID)
);

CREATE TABLE PERSONAL_LICENSE(
  PERSONAL_LICENSE_ID NUMBER PRIMARY KEY,
  LICENSE NUMBER,
  PERSON NUMBER,
  REQUESTED_DATE DATE,
  CONFIRMED_DATE DATE,
  LICENSE_STATE NUMBER,
  RESOLUTION_COMMENT VARCHAR(255),
  ATTACHMENT_CONTENT BLOB,
  ATTACHMENT_FILE_NAME varchar(255),
  FIRST_NAME VARCHAR(255),
  LAST_NAME VARCHAR(255),
  ORGANISATION VARCHAR(255),
  EMAIL VARCHAR(255)
);

ALTER TABLE EXPERIMENT_PACKAGE_CONNECTION ADD CONSTRAINT fk_epc_e FOREIGN KEY (EXPERIMENT) REFERENCES EXPERIMENT (EXPERIMENT_ID);
ALTER TABLE EXPERIMENT_PACKAGE_CONNECTION ADD CONSTRAINT fk_ep_p FOREIGN KEY (EXPERIMENT_PACKAGE) REFERENCES EXPERIMENT_PACKAGE (EXPERIMENT_PACKAGE_ID);

ALTER TABLE EXPERIMENT_PACKAGE ADD CONSTRAINT fk_ep_rg FOREIGN KEY (RESEARCH_GROUP) REFERENCES RESEARCH_GROUP (RESEARCH_GROUP_ID);

ALTER TABLE EXPERIMENT_PACKAGE_LICENSE ADD CONSTRAINT fk_epl_ep FOREIGN KEY (EXPERIMENT_PACKAGE) REFERENCES EXPERIMENT_PACKAGE(EXPERIMENT_PACKAGE_ID);
ALTER TABLE EXPERIMENT_PACKAGE_LICENSE ADD CONSTRAINT fk_epl_l FOREIGN KEY (LICENSE) REFERENCES LICENSE(LICENSE_ID);

ALTER TABLE PERSONAL_LICENSE ADD CONSTRAINT fk_pl_p FOREIGN KEY (PERSON) REFERENCES PERSON(PERSON_ID);
ALTER TABLE PERSONAL_LICENSE ADD CONSTRAINT fk_pl_l FOREIGN KEY (LICENSE) REFERENCES LICENSE(LICENSE_ID);

ALTER TABLE RESEARCH_GROUP ADD PAID_ACCOUNT NUMBER DEFAULT 0 NOT NULL;

UPDATE RESEARCH_GROUP SET paid_account=1 WHERE research_group_id in (SELECT DISTINCT rg.research_group_id FROM research_group rg LEFT JOIN experiment e ON rg.research_group_id = e.research_group_id WHERE e.private=1 );