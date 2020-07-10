/*
 Source Server Type    : PostgreSQL
 Source Server Version : 120003
 Source Catalog        : company_db
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 120003
 File Encoding         : 65001
*/


-- ----------------------------
-- Table structure for xyz_applicant
-- ----------------------------
DROP TABLE IF EXISTS "xyz_applicant";
CREATE TABLE "xyz_applicant" (
  "applicant_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "applicant_contact" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "applicant_email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "uen" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "role" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "xyz_applicant"."applicant_name" IS 'Nromally company admin';
COMMENT ON COLUMN "xyz_applicant"."applicant_contact" IS 'Applicant contact number';
COMMENT ON COLUMN "xyz_applicant"."applicant_email" IS 'Applicant contact email';
COMMENT ON COLUMN "xyz_applicant"."uen" IS 'uen number';

-- ----------------------------
-- Records of xyz_applicant
-- ----------------------------
BEGIN;
INSERT INTO "xyz_applicant" VALUES ('Mike', '97220001', 'mike@xyz.com', 'T00SS0080D', 'company_admin');
INSERT INTO "xyz_applicant" VALUES ('Isen', '88665544', 'isen@agent.com', 'E11223344E', 'agent_admin');
INSERT INTO "xyz_applicant" VALUES ('Ken', '87654321', 'ken@eft.com', 'F12345679A', 'company_admin');
INSERT INTO "xyz_applicant" VALUES ('Keith', '97509111', 'keith@xyz.com', 'T00SS0080D', 'company_admin');
COMMIT;

-- ----------------------------
-- Table structure for xyz_application
-- ----------------------------
DROP TABLE IF EXISTS "xyz_application";
CREATE TABLE "xyz_application" (
  "application_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "uen_no" varchar(100) COLLATE "pg_catalog"."default",
  "employee_name" varchar(255) COLLATE "pg_catalog"."default",
  "employee_id" varchar(100) COLLATE "pg_catalog"."default",
  "employee_passport" varchar(255) COLLATE "pg_catalog"."default",
  "employee_origin_country" varchar(255) COLLATE "pg_catalog"."default",
  "employee_destination_country" varchar(255) COLLATE "pg_catalog"."default",
  "employee_travel_from_date" varchar(100) COLLATE "pg_catalog"."default",
  "employee_travel_to_date" varchar(100) COLLATE "pg_catalog"."default",
  "submit_datetime" timestamp(6),
  "submit_by" varchar(255) COLLATE "pg_catalog"."default",
  "last_update_datetime" timestamp(6),
  "last_update_by" varchar(255) COLLATE "pg_catalog"."default",
  "travel_cost" numeric(18,2),
  "status" varchar(10) COLLATE "pg_catalog"."default",
  "remarks" varchar(500) COLLATE "pg_catalog"."default",
  "travel_path" jsonb,
  "company_name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of xyz_application
-- ----------------------------
BEGIN;
INSERT INTO "xyz_application" VALUES ('2020_T00SS0080D_0705001', 'T00SS0080D', 'John', '20150809101', 'E12345678A', 'Singapore', 'Malaysia', '2020-07-15', '2020-07-25', '2020-07-05 22:09:15', 'mike@xyz.com', '2020-07-07 11:08:43.202', 'juan', 2.00, 'New', 'string2.0', '{}', 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200708-T00SS0080D-f2d440630bd215773ade071b4bb4f14f', 'T00SS0080D', 'Test C', 'S8789098D', 'P1234468F', 'Singapore', 'Unit States(New York)', '2020-07-09', '2020-07-26', '2020-07-08 16:43:55.76', 'keith@xyz.com', '2020-07-08 16:43:55.76', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200708-T00SS0080D-9735b5e476e6326b3357d848de8d8839', 'T00SS0080D', 'Test A', 'S9878989B', 'P7654321B', 'Singapore', 'China(BeiJing)', '2020-07-10', '2020-07-22', '2020-07-08 15:39:11.74', 'keith@xyz.com', '2020-07-08 15:39:11.74', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200708-T00SS0080D-b14ed4ce4114af7536f2038ac92418ef', 'T00SS0080D', 'Test B', 'S8765432B', 'P12345678', 'Singapore', 'Unit States(New York)', '2020-07-10', '2020-07-24', '2020-07-08 16:07:49.243', 'keith@xyz.com', '2020-07-08 16:07:49.243', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200709-T00SS0080D-1c6820fbf21e31dd984bf9f6bfb9dab2', 'T00SS0080D', 'Test D', 'S8789098D', 'T123890F', 'Singapore', 'Shanghai', '2020-07-09', '2020-07-29', '2020-07-09 16:47:42.091', 'keith@xyz.com', '2020-07-09 16:47:42.091', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200709-T00SS0080D-97be110e5a6fbef0ecfb08319f4d7365', 'T00SS0080D', 'Test E', 'S8482589A', 'P12345678', 'Singapore', 'Shanghai', '2020-07-21', '2020-07-28', '2020-07-09 17:22:40.805', 'keith@xyz.com', '2020-07-09 17:22:40.805', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200709-E11223344E-d43a70f18c013b3262176e0b6663bb39', 'E11223344E', 'Test X', 'S8482589X', 'T123890X', 'Singapore', 'Unit States(New York)', '2020-08-03', '2020-07-30', '2020-07-09 17:35:00.933', 'isen@agent.com', '2020-07-09 17:35:00.933', 'isen@agent.com', NULL, 'New', NULL, NULL, 'SSC Agent');
INSERT INTO "xyz_application" VALUES ('20200709-F12345679A-585a0320bf83003a8dd7f8f98bc9e15f', 'F12345679A', 'Test EF', 'S8482589EF', 'T123890EF', 'Singapore', 'Unit States(New York)', '2020-07-16', '2020-07-30', '2020-07-09 17:36:02.79', 'ken@eft.com', '2020-07-09 17:36:02.79', 'ken@eft.com', NULL, 'New', NULL, NULL, 'Ken Can');
INSERT INTO "xyz_application" VALUES ('20200710-T00SS0080D-014b6277e5d74cd5acf5cf7d99c40326', 'T00SS0080D', 'Test F', 'S8765432B', 'P1234468F', 'Singapore', 'Unit States(New York)', '2020-07-13', '2020-07-13', '2020-07-10 03:01:56.743', 'keith@xyz.com', '2020-07-10 03:01:56.743', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200710-T00SS0080D-8d21657895aced3612e1610012f3eea0', 'T00SS0080D', 'Test G', 'S8482589X', 'P1234468F', 'Singapore', 'Unit States(New York)', '2020-07-15', '2020-07-22', '2020-07-10 03:03:16.513', 'keith@xyz.com', '2020-07-10 03:03:16.513', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
INSERT INTO "xyz_application" VALUES ('20200710-T00SS0080D-860ed4841b89d7c9ce4bfa2a0b7cccd0', 'T00SS0080D', 'Test I', 'S8482589A', 'P1234468F', 'Singapore', 'Unit States(New York)', '2020-07-16', '2020-07-24', '2020-07-10 15:23:26.347', 'keith@xyz.com', '2020-07-10 15:23:26.347', 'keith@xyz.com', NULL, 'New', NULL, NULL, 'Play@Tech');
COMMIT;

-- ----------------------------
-- Table structure for xyz_application_transaction
-- ----------------------------
DROP TABLE IF EXISTS "xyz_application_transaction";
CREATE TABLE "xyz_application_transaction" (
  "application_id" varchar(255) COLLATE "pg_catalog"."default",
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_datetime" timestamp(6),
  "travel_cost" numeric(18,2),
  "status" varchar(10) COLLATE "pg_catalog"."default",
  "remarks" varchar(500) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for xyz_company
-- ----------------------------
DROP TABLE IF EXISTS "xyz_company";
CREATE TABLE "xyz_company" (
  "uen" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "company_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "company_address" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "xyz_company"."uen" IS 'UEN ID';
COMMENT ON COLUMN "xyz_company"."company_name" IS 'Company Full Name';
COMMENT ON COLUMN "xyz_company"."company_address" IS 'Company Address String';

-- ----------------------------
-- Records of xyz_company
-- ----------------------------
BEGIN;
INSERT INTO "xyz_company" VALUES ('T00SS0080D', 'Play@Tech', '10, Pasir Panjang Road, #10-01, Mapletree Business City, SINGAPORE 117438');
INSERT INTO "xyz_company" VALUES ('F12345679A', 'Ken Can', 'Ken and Can Road');
INSERT INTO "xyz_company" VALUES ('E11223344E', 'SSC Agent', 'Sembawang Road');
COMMIT;

-- ----------------------------
-- Primary Key structure for table xyz_application
-- ----------------------------
ALTER TABLE "xyz_application" ADD CONSTRAINT "xyz_application_pkey" PRIMARY KEY ("application_id");
