CREATE TABLE "PROJECT_TYPE_GROUP_REL" (
	"PROJECT_TYPE_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL,
PRIMARY KEY ("RESEARCH_GROUP_ID", "PROJECT_TYPE_ID") 
)
;