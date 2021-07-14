DROP TABLE IF EXISTS "Admin";
CREATE TABLE "Admin" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "username" TEXT NOT NULL,
  "password" TEXT NOT NULL,
  "enable" integer DEFAULT 1,
  "face" TEXT,
  "active" integer DEFAULT 1,
  CONSTRAINT "username" UNIQUE ("username")
);

DROP TABLE IF EXISTS "motion";
CREATE TABLE "motion" (
  "id" integer NOT NULL  PRIMARY KEY AUTOINCREMENT,
  "level" integer NOT NULL,
  "time" TEXT NOT NULL,
  "remark" TEXT
);

