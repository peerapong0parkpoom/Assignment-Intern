CREATE SEQUENCE test_id_seq OWNED BY "user".id;

ALTER TABLE "user" ALTER COLUMN id SET DEFAULT nextval('test_id_seq');

