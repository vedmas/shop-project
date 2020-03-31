-- CREATE DATABASE market_db
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     CONNECTION LIMIT = -1;

--Таблица products содержит поля название продукта, связанные поля category_id (таблица category) и producer_id (таблица producer),
-- поле price и discount(скидка на этот продукт).
-- Таблица users содержит поле имя пользователя, numberPhone(которое будет логином), password, email и связанное поле gender_id (таблица gender)
-- Таблица users_roles связь многие ко многим
-- Таблица orders, содержит информацию о товарах которые заказывает user, с определением статуса оплаты заказа.

CREATE TABLE public.category
(
    id serial NOT NULL,
    name_category character varying(255) NOT NULL,
    discount_category smallint,
    PRIMARY KEY (id)
);

ALTER TABLE public.category
    OWNER to postgres;

CREATE TABLE public.producer
(
    id serial NOT NULL,
    name_producer character varying(255) NOT NULL,
    region character varying(255),
    country character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.producer
    OWNER to postgres;

CREATE TABLE public.roles
(
    id serial NOT NULL,
    name_role character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.roles
    OWNER to postgres;

CREATE TABLE public.gender
(
    id serial NOT NULL,
    name_gender character varying(10) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.gender
    OWNER to postgres;

CREATE TABLE public.products
(
    id serial NOT NULL,
    name_product character varying(255) NOT NULL,
    "category)id" integer NOT NULL,
    producer_id integer NOT NULL,
    price money NOT NULL,
    discount smallint,
    PRIMARY KEY (id),
    CONSTRAINT "categoryIdToId" FOREIGN KEY ("category)id")
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "producerIdToId" FOREIGN KEY (producer_id)
        REFERENCES public.producer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.products
    OWNER to postgres;

CREATE TABLE public.users
(
    id serial NOT NULL,
    first_user_name character varying(50) NOT NULL,
    last_user_name character varying(50) NOT NULL,
    number_phone character varying(15) NOT NULL,
    password character varying(20) NOT NULL,
    email character varying(50),
    gender_id smallint,
    PRIMARY KEY (id),
    CONSTRAINT "genderIdToId" FOREIGN KEY (gender_id)
        REFERENCES public.gender (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.users
    OWNER to postgres;

CREATE TABLE public.users_roles
(
    users_id integer NOT NULL,
    roles_id integer NOT NULL,
    PRIMARY KEY (users_id, roles_id),
    CONSTRAINT "usersIdToId" FOREIGN KEY (users_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "rolesIdToId" FOREIGN KEY (roles_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.users_roles
    OWNER to postgres;


CREATE TABLE public.status_payment
(
    id smallserial NOT NULL,
    name_status character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.status_payment
    OWNER to postgres;


CREATE TABLE public.orders
(
    id bigserial NOT NULL,
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    status_payment_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "ordersUserIdToId" FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ordersProductIdToId" FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ordersStatus_paymentIdToId" FOREIGN KEY (status_payment_id)
        REFERENCES public.status_payment (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.orders
    OWNER to postgres;

CREATE TABLE public.pictures_data
(
    id serial NOT NULL,
    data bytea NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.pictures_data
    OWNER to postgres;

CREATE TABLE public.pictures
(
    id serial NOT NULL,
    name character varying(50),
    content_type character varying(255),
    pictures_data_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "pictures_dataToPictures" FOREIGN KEY (pictures_data_id)
        REFERENCES public.pictures_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.pictures
    OWNER to postgres;

CREATE TABLE public.products_pictures
(
    products_id integer NOT NULL,
    pictures_id integer NOT NULL,
    PRIMARY KEY (products_id, pictures_id),
    CONSTRAINT "product_idToProduct" FOREIGN KEY (products_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "pictures_idToPictures" FOREIGN KEY (pictures_id)
        REFERENCES public.pictures (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.products_pictures
    OWNER to postgres;