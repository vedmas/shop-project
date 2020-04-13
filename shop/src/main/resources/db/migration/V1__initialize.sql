DROP TABLE IF EXISTS public.roles;
CREATE TABLE public.roles
(
    id serial  NOT NULL,
    name_role character varying(50),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.gender;
CREATE TABLE public.gender
(
    id serial NOT NULL,
    name_gender character varying(10) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.category;
CREATE TABLE public.category
(
    id serial NOT NULL,
    name_category character varying(255) NOT NULL,
    discount_category smallint,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.producers;
CREATE TABLE public.producers
(
    id serial NOT NULL,
    name_producer character varying(255) NOT NULL,
    region character varying(255),
    country character varying(255)NOT NULL,
    CONSTRAINT producer_product_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users
(
    id serial NOT NULL,
    first_user_name character varying(50) NOT NULL,
    last_user_name character varying(50) NOT NULL,
    number_phone character varying(15) NOT NULL,
    password character varying(500) NOT NULL,
    email character varying(50),
    gender_id smallint,
    PRIMARY KEY (id),
    CONSTRAINT "genderIdToId" FOREIGN KEY (gender_id)
        REFERENCES public.gender (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS public.users_roles;
CREATE TABLE public.users_roles
(
    users_id integer NOT NULL,
    roles_id integer NOT NULL,
    PRIMARY KEY (users_id, roles_id),
    CONSTRAINT "rolesIdToId" FOREIGN KEY (roles_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "usersIdToId" FOREIGN KEY (users_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS public.status_payment;
CREATE TABLE public.status_payment
(
    id serial  NOT NULL,
    name_status character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.pictures_data;
CREATE TABLE public.pictures_data
(
    id serial  NOT NULL,
    data bytea,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.pictures;
CREATE TABLE public.pictures
(
    id serial  NOT NULL,
    name character varying(50),
    content_type character varying(255),
    picture_data_id integer NOT NULL,
    CONSTRAINT pictures_pkey PRIMARY KEY (id),
    CONSTRAINT "pictures_dataToPictures" FOREIGN KEY (picture_data_id)
        REFERENCES public.pictures_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products
(
    id serial NOT NULL,
    name_product character varying(255) NOT NULL,
    category_id integer NOT NULL,
    producer_id integer NOT NULL,
    price numeric NOT NULL,
    discount smallint,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT "categoryIdToId" FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "producerIdToId" FOREIGN KEY (producer_id)
        REFERENCES public.producers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS public.products_pictures;
CREATE TABLE public.products_pictures
(
    products_id integer NOT NULL,
    pictures_id integer NOT NULL,
    PRIMARY KEY (products_id, pictures_id),
    CONSTRAINT "pictures_idToPictures" FOREIGN KEY (pictures_id)
        REFERENCES public.pictures (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "product_idToProduct" FOREIGN KEY (products_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS public.orders;
CREATE TABLE public.orders
(
    id bigserial NOT NULL,
    user_id integer,
    status_payment_id integer NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    country character varying(50) NOT NULL,
    zip_code character varying(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "ordersStatus_paymentIdToId" FOREIGN KEY (status_payment_id)
        REFERENCES public.status_payment (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ordersUserIdToId" FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS public.orders_products;
CREATE TABLE public.orders_products
(
    orders_id bigint NOT NULL,
    products_id bigint NOT NULL,
    quantity integer NOT NULL,
    PRIMARY KEY (orders_id, products_id),
    CONSTRAINT orders_id_to_orders FOREIGN KEY (orders_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT products_id_to_products FOREIGN KEY (products_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO public.roles (name_role)
VALUES ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_CLIENT');

INSERT INTO public.gender (name_gender)
VALUES ('Man'), ('Woman');

INSERT INTO public.category (name_category)
VALUES ('Cameras'), ('Accessories'), ('Laptops');

INSERT INTO public.category (name_category, discount_category)
VALUES ('Smartphones', 15);

INSERT INTO public.producers (name_producer, region, country)
VALUES ('Sony', 'Osaka', 'Japan'), ('Apple', 'New York', 'USA');

INSERT INTO public.users (first_user_name, last_user_name, number_phone, password, email, gender_id)
VALUES ('admin', 'admin', '000000', '$2a$10$U7BQlxao8PUgp//YF6okpe4ZO.7I4gcnyEs00Cu4QErGfSS1gzSBm', 'admin@admin', 1);
INSERT INTO public.users_roles (users_id, roles_id)
VALUES (1, 1);

INSERT INTO public.status_payment (name_status)
VALUES ('Payment received'), ('Waiting for payment');

