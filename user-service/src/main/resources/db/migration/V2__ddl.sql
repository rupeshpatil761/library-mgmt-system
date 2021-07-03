CREATE TABLE library_user
(
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT library_user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_library_user UNIQUE (email),
    CONSTRAINT chk_user_type CHECK (user_type in ('USER','ADMIN'))
)


