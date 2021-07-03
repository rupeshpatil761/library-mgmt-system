CREATE TABLE library
(
    book_id bigint NOT NULL,
    user_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT library_pkey PRIMARY KEY (book_id, user_id)
)