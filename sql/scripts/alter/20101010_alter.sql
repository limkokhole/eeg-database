-- ADD COLUMNS
ALTER TABLE PERSON ADD AUTHENTICATION VARCHAR2(50);
ALTER TABLE PERSON ADD CONFIRMED NUMBER;
ALTER TABLE PERSON ADD REGISTRATION_DATE DATE;

--ADD FOREIGN KEYS
ALTER TABLE "ARTICLES" ADD CONSTRAINT "ARTICLES_PERSON_FK1" FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID");