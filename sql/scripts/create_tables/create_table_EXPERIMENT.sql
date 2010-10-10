CREATE TABLE "EXPERIMENT" (
	"EXPERIMENT_ID" INTEGER NOT NULL ,
	"OWNER_ID" INTEGER NOT NULL ,
	"SUBJECT_PERSON_ID" INTEGER NOT NULL ,
	"SCENARIO_ID" INTEGER NOT NULL ,
	"WEATHER_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"START_TIME" DATE,
	"END_TIME" DATE,
	"TEMPERATURE" SMALLINT,
	"WEATHERNOTE" VARCHAR2 (255),
	"PRIVATE" INTEGER DEFAULT 0 NOT NULL ,
 CONSTRAINT "PK_EXPERIMENT" PRIMARY KEY ("EXPERIMENT_ID")
)
;