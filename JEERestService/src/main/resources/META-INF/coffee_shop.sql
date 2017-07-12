DO
$body$
BEGIN
  IF NOT EXISTS (
      SELECT *
      FROM   pg_catalog.pg_user
      WHERE  usename = 'coffee_shop_db_auth') THEN
    CREATE ROLE coffee_shop_db_auth LOGIN PASSWORD 'ub6KxEDvn7pqHHTq';
  ELSE
    RAISE  NOTICE 'User already exists';
  END IF;
END
$body$;

DO
$body$
BEGIN
  IF EXISTS (SELECT 1 FROM information_schema.schemata WHERE schema_name = 'coffee_shop') THEN
    RAISE NOTICE 'Schema already exists';
  ELSE
    CREATE SCHEMA coffee_shop;
    GRANT SELECT, UPDATE, INSERT, DELETE ON SCHEMA coffee_shop to coffee_shop_db_auth;
    ALTER ROLE coffee_shop_db_auth SET SEARCH_PATH TO coffee_shop;
  END IF;
END
$body$;

create or replace function create_constraint_if_not_exists (
  t_name text, c_name text, constraint_sql text
)
  returns void AS
$$
begin
  -- Look for our constraint
  if not exists (select constraint_name
                 from information_schema.constraint_column_usage
                 where table_name = t_name  and constraint_name = c_name) then
    execute constraint_sql;
  end if;
end;
$$ language 'plpgsql';

GRANT USAGE ON SCHEMA coffee_shop to coffee_shop_db_auth;

CREATE TABLE IF NOT EXISTS coffee_shop.products();

GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE coffee_shop.products to coffee_shop_db_auth;

/*ALTER TABLE coffee_shop.products ADD COLUMN IF NOT EXISTS id BIGINT DEFAULT nextval('coffee_shop.product_id_seq'),*/
ALTER TABLE coffee_shop.products ADD COLUMN IF NOT EXISTS id BIGSERIAL,
  ADD COLUMN IF NOT EXISTS sku_id TEXT NOT NULL DEFAULT '',
  ADD COLUMN IF NOT EXISTS name TEXT NOT NULL DEFAULT '',
  ADD COLUMN IF NOT EXISTS size TEXT NOT NULL DEFAULT '',
  ADD COLUMN IF NOT EXISTS cost DECIMAL,
  ADD COLUMN IF NOT EXISTS "imgUrl" TEXT NOT NULL DEFAULT '';

SELECT create_constraint_if_not_exists('products', 'products_pkey', 'ALTER TABLE coffee_shop.products ADD CONSTRAINT products_pkey PRIMARY KEY (id);');
SELECT create_constraint_if_not_exists('products', 'sku_id_unique', 'ALTER TABLE coffee_shop.products ADD CONSTRAINT sku_id_unique UNIQUE (sku_id);');

GRANT SELECT, USAGE ON SEQUENCE coffee_shop.products_id_seq TO coffee_shop_db_auth;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE coffee_shop.products TO coffee_shop_db_auth;