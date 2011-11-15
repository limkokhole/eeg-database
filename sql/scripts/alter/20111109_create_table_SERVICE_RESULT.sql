CREATE TABLE "PBORIK"."SERVICE_RESULT"
  (
    "RESULT_ID" NUMBER NOT NULL ENABLE,
    "FIGURE" BLOB,
    "FILENAME"  VARCHAR2(80 BYTE),
    "STATUS"    VARCHAR2(20 BYTE) NOT NULL ENABLE,
    "PERSON_ID" NUMBER NOT NULL ENABLE,
    "TITLE"     VARCHAR2(80 BYTE) NOT NULL ENABLE,
    CONSTRAINT "SERVICE_RESULT_PK" PRIMARY KEY ("RESULT_ID"),
    CONSTRAINT "PERSON_ID" FOREIGN KEY ("PERSON_ID") REFERENCES "PBORIK"."PERSON" ("PERSON_ID")
  );