--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.25
-- Dumped by pg_dump version 9.5.25

-- Started on 2021-04-16 14:00:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 16464)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16469)
-- Name: manufacturer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.manufacturer (
    id integer NOT NULL,
    name character varying(45) NOT NULL,
    country character varying(45) DEFAULT NULL::character varying
);


ALTER TABLE public.manufacturer OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16516)
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    id integer NOT NULL,
    customer_name character varying,
    customer_address character varying,
    customer_phone character varying,
    customer_email character varying,
    order_date timestamp without time zone
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16514)
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_id_seq OWNER TO postgres;

--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 185
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_id_seq OWNED BY public."order".id;


--
-- TOC entry 188 (class 1259 OID 16525)
-- Name: order_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_item (
    id integer NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.order_item OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16523)
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_item_id_seq OWNER TO postgres;

--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 187
-- Name: order_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_item_id_seq OWNED BY public.order_item.id;


--
-- TOC entry 184 (class 1259 OID 16490)
-- Name: prod_manufacturer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prod_manufacturer (
    id integer NOT NULL,
    product_id integer NOT NULL,
    manufacturer_id integer NOT NULL
);


ALTER TABLE public.prod_manufacturer OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16475)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(45) NOT NULL,
    price numeric(10,0) DEFAULT '0'::numeric,
    category_id integer NOT NULL,
    image character varying(255) DEFAULT NULL::character varying,
    description text
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 2008 (class 2604 OID 16519)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order" ALTER COLUMN id SET DEFAULT nextval('public.order_id_seq'::regclass);


--
-- TOC entry 2009 (class 2604 OID 16528)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item ALTER COLUMN id SET DEFAULT nextval('public.order_item_id_seq'::regclass);


--
-- TOC entry 2135 (class 0 OID 16464)
-- Dependencies: 181
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name) FROM stdin;
1	DIEN THOAI
2	MAY TINH BANG
3	LAPTOP
\.


--
-- TOC entry 2136 (class 0 OID 16469)
-- Dependencies: 182
-- Data for Name: manufacturer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.manufacturer (id, name, country) FROM stdin;
1	Apple	\N
2	Samsung	\N
3	Nokia	\N
4	Sony	\N
5	Oppo	\N
\.


--
-- TOC entry 2140 (class 0 OID 16516)
-- Dependencies: 186
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."order" (id, customer_name, customer_address, customer_phone, customer_email, order_date) FROM stdin;
2	Duong Xuan Da	BN	1693719775	xuandapa@gmail.com	2021-04-16 00:00:00
3	Tu Cong	Dong Da	0961010169	dadx.jsoft@gmail.com	2021-04-16 00:00:00
4	Tu Cong	Dong Da	0961010169	dadx.jsoft@gmail.com	2021-04-16 00:00:00
5	Nhan Luong	Hoang Cau	0123456789	nhan@gmail.com	2021-04-16 00:00:00
6	Nhan Luong 2	Hoang Cau	0961010169	xuandapa@gmail.com	2021-04-16 00:00:00
7	Duong Xuan Da 1	BN	1693719775	xuandapa@gmail.com	2021-04-16 00:00:00
\.


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 185
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_id_seq', 7, true);


--
-- TOC entry 2142 (class 0 OID 16525)
-- Dependencies: 188
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_item (id, order_id, product_id, quantity) FROM stdin;
1	2	2	1
2	2	3	1
3	3	2	1
4	3	3	1
5	4	1	1
6	5	2	1
7	6	1	1
8	7	2	1
\.


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 187
-- Name: order_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_item_id_seq', 8, true);


--
-- TOC entry 2138 (class 0 OID 16490)
-- Dependencies: 184
-- Data for Name: prod_manufacturer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prod_manufacturer (id, product_id, manufacturer_id) FROM stdin;
1	5	1
3	7	5
4	9	3
6	11	2
8	10	2
\.


--
-- TOC entry 2137 (class 0 OID 16475)
-- Dependencies: 183
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, price, category_id, image, description) FROM stdin;
1	Iphone 7 Plus	16	1	upload/Screenshot_2.png	64GB, Apple
2	Galaxy note 10	24	1	upload/Screenshot_2.png	64GB, Samsung
3	Ipad pro 11	18	2	upload/Screenshot_2.png	128GB, Apple
4	Sangsung tab s6	20	2	upload/Screenshot_2.png	128GB, Samsung
5	Iphone 13	5000	1	upload/Screenshot_2.png	khong co gi
7	Iphone 13	5000	1	upload/Screenshot_2.png	khong co gi
8	Iphone 7 Plus	16	1	upload/Screenshot_2.png	64GB, Apple
9	Iphone 7 Plus	16	1	upload/Screenshot_2.png	64GB, Apple
10	Iphone 7 Plus	16	1	upload/Screenshot_2.png	64GB, Apple
11	Iphone 7 Plus	19	1	upload/Screenshot_2.png	64GB, Apple
\.


--
-- TOC entry 2011 (class 2606 OID 16468)
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 16474)
-- Name: manufacturer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manufacturer
    ADD CONSTRAINT manufacturer_pkey PRIMARY KEY (id);


--
-- TOC entry 2017 (class 2606 OID 16494)
-- Name: prod_manufacturer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prod_manufacturer
    ADD CONSTRAINT prod_manufacturer_pkey PRIMARY KEY (id);


--
-- TOC entry 2015 (class 2606 OID 16484)
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2019 (class 2606 OID 16495)
-- Name: fk_manufacturer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prod_manufacturer
    ADD CONSTRAINT fk_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES public.manufacturer(id);


--
-- TOC entry 2020 (class 2606 OID 16500)
-- Name: fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prod_manufacturer
    ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2018 (class 2606 OID 16485)
-- Name: fk_product_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2021-04-16 14:00:36

--
-- PostgreSQL database dump complete
--

