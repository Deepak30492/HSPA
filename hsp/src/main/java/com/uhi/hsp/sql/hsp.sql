
-- DROP TABLE IF EXISTS hsp.provider;
CREATE TABLE IF NOT EXISTS hsp.provider
(
    provider_id integer NOT NULL,
    city character varying(255) COLLATE pg_catalog."default",
    country character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    state character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT provider_pkey PRIMARY KEY (provider_id)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS hsp.provider
    OWNER to postgres;
-- DROP TABLE IF EXISTS hsp.practitioner;

CREATE TABLE IF NOT EXISTS hsp.practitioner
(
    practitioner_id integer NOT NULL,
    consultation_charge character varying(255) COLLATE pg_catalog."default",
    cred character varying(255) COLLATE pg_catalog."default",
    currency character varying(255) COLLATE pg_catalog."default",
    gender character(1) COLLATE pg_catalog."default",
    image character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    end_time character varying(255) COLLATE pg_catalog."default",
    start_time character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT practitioner_pkey PRIMARY KEY (practitioner_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS hsp.practitioner
    OWNER to postgres;
    

-- DROP TABLE IF EXISTS hsp.fulfillments;

CREATE TABLE IF NOT EXISTS hsp.fulfillments
(
    fulfillment_id integer NOT NULL,
    end_time character varying(255) COLLATE pg_catalog."default",
    start_time character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    category_id integer NOT NULL,
    practitioner_id integer,
    provider_id integer NOT NULL,
    CONSTRAINT fulfillments_pkey PRIMARY KEY (fulfillment_id),
    CONSTRAINT fk4nh7tx3t28wk65xmqtg1f6tsi FOREIGN KEY (provider_id)
        REFERENCES hsp.provider (provider_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk6m08s4qyyimj4xmyfsu6w9fi8 FOREIGN KEY (practitioner_id)
        REFERENCES hsp.practitioner (practitioner_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkdqyc5s3r0200vth1rc04pag66 FOREIGN KEY (category_id)
        REFERENCES hsp.categories (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS hsp.fulfillments
    OWNER to postgres;
    
    
    -- Table: hsp.categories

-- DROP TABLE IF EXISTS hsp.categories;

CREATE TABLE IF NOT EXISTS hsp.categories
(
    category_id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    provider_id integer NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (category_id),
    CONSTRAINT fkhgn5d96a3hollc0ctjmltt863 FOREIGN KEY (provider_id)
        REFERENCES hsp.provider (provider_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS hsp.categories
    OWNER to postgres;
    
    
    -- Table: hsp.billing

-- DROP TABLE IF EXISTS hsp.billing;

CREATE TABLE IF NOT EXISTS hsp.billing
(
    bill_id integer NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    state character varying(255) COLLATE pg_catalog."default",
    address_id integer,
    fulfillment_id integer,
    CONSTRAINT billing_pkey PRIMARY KEY (bill_id),
    CONSTRAINT fk8byyrmxrl7mmy0h6463frwxn1 FOREIGN KEY (fulfillment_id)
        REFERENCES hsp.fulfillments (fulfillment_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk8vv74s6uc58osqhoeq7k8102x FOREIGN KEY (address_id)
        REFERENCES hsp.address (address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS hsp.billing
    OWNER to postgres;
    
    -- Table: hsp.address

-- DROP TABLE IF EXISTS hsp.address;

CREATE TABLE IF NOT EXISTS hsp.address
(
    address_id integer NOT NULL,
    area_code character varying(255) COLLATE pg_catalog."default",
    building character varying(255) COLLATE pg_catalog."default",
    door character varying(255) COLLATE pg_catalog."default",
    street character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT address_pkey PRIMARY KEY (address_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS hsp.address
    OWNER to postgres;
    