--
-- PostgreSQL database dump
--

-- Dumped from database version 10.16
-- Dumped by pg_dump version 10.16

-- Started on 2021-04-15 16:56:01

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 196 (class 1259 OID 16686)
-- Name: category_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16688)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer DEFAULT nextval('public.category_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16695)
-- Name: manufacturer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.manufacturer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.manufacturer_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16697)
-- Name: manufacturer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.manufacturer (
    id integer DEFAULT nextval('public.manufacturer_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL,
    country character varying(45) DEFAULT NULL::character varying
);


ALTER TABLE public.manufacturer OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16797)
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
-- TOC entry 208 (class 1259 OID 16805)
-- Name: order_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_item (
    id integer NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.order_item OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16754)
-- Name: payment_detail_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_detail_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_detail_seq OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16718)
-- Name: payment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16776)
-- Name: prod_manufacturer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prod_manufacturer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prod_manufacturer_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16778)
-- Name: prod_manufacturer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prod_manufacturer (
    id integer DEFAULT nextval('public.prod_manufacturer_seq'::regclass) NOT NULL,
    product_id integer NOT NULL,
    manufacturer_id integer NOT NULL
);


ALTER TABLE public.prod_manufacturer OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16734)
-- Name: product_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_seq OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16736)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer DEFAULT nextval('public.product_seq'::regclass) NOT NULL,
    name character varying(45) NOT NULL,
    price numeric(10,0) DEFAULT '0'::numeric,
    category_id integer,
    image character varying(255) DEFAULT NULL::character varying,
    description text
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16705)
-- Name: user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_seq OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 16688)
-- Dependencies: 197
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name) FROM stdin;
1	DIEN THOAI
2	MAY TINH BANG
3	LAPTOP
4	PHá»¤ KIá»†N
\.


--
-- TOC entry 2855 (class 0 OID 16697)
-- Dependencies: 199
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
-- TOC entry 2863 (class 0 OID 16797)
-- Dependencies: 207
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."order" (id, customer_name, customer_address, customer_phone, customer_email, order_date) FROM stdin;
\.


--
-- TOC entry 2864 (class 0 OID 16805)
-- Dependencies: 208
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_item (id, order_id, product_id) FROM stdin;
\.


--
-- TOC entry 2862 (class 0 OID 16778)
-- Dependencies: 206
-- Data for Name: prod_manufacturer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prod_manufacturer (id, product_id, manufacturer_id) FROM stdin;
6	11	2
19	22	1
20	34	1
21	35	2
25	36	2
\.


--
-- TOC entry 2859 (class 0 OID 16736)
-- Dependencies: 203
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, price, category_id, image, description) FROM stdin;
53	Keyboard	123456	2	\N	okfine
2	Galaxy note 10	123	2	upload/Screenshot_2.png	mt
36	San pham 3	123	2	upload/fake-duke.PNG	mt
50	Galaxy note 10	24	1	\N	64GB, Samsung
4	Sangsung tab s6	20	2	upload/Screenshot_2.png	128GB, Samsung
34	San pham 1	100	3	upload/fake-duke.PNG	ok
35	San pham 2	200	1	upload/fake-duke.PNG	ok
11	Iphone 7 Plus	16	1	upload/Screenshot_2.png	64GB, Apple
3	Ipad pro 11	18	2	upload/Screenshot_2.png	128GB, Apple
8	Iphone 7 Plus	16	1	upload/Screenshot_2.png	64GB, Apple
42	Iphone 7 Plus 100	16	1	\N	64GB, Apple!
54	key2	123456	2	\N	mo ta
22	Iphone 8	40001	1	upload/fake-duke.PNG	demo
\.


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 196
-- Name: category_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_seq', 5, false);


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 198
-- Name: manufacturer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.manufacturer_seq', 6, false);


--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 204
-- Name: payment_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_detail_seq', 3, false);


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 201
-- Name: payment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_seq', 2, false);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 205
-- Name: prod_manufacturer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prod_manufacturer_seq', 25, true);


--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 202
-- Name: product_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_seq', 58, true);


--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 200
-- Name: user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_seq', 4, false);


--
-- TOC entry 2712 (class 2606 OID 16693)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 16703)
-- Name: manufacturer manufacturer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manufacturer
    ADD CONSTRAINT manufacturer_pkey PRIMARY KEY (id);


--
-- TOC entry 2725 (class 2606 OID 16809)
-- Name: order_item order_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


--
-- TOC entry 2723 (class 2606 OID 16804)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- TOC entry 2721 (class 2606 OID 16783)
-- Name: prod_manufacturer prod_manufacturer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prod_manufacturer
    ADD CONSTRAINT prod_manufacturer_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 16746)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2718 (class 1259 OID 16796)
-- Name: fk_manufacturer_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_manufacturer_idx ON public.prod_manufacturer USING btree (manufacturer_id);


--
-- TOC entry 2715 (class 1259 OID 16753)
-- Name: fk_product_category_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_product_category_idx ON public.product USING btree (category_id);


--
-- TOC entry 2719 (class 1259 OID 16795)
-- Name: fk_product_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_product_idx ON public.prod_manufacturer USING btree (product_id);


--
-- TOC entry 2727 (class 2606 OID 16784)
-- Name: prod_manufacturer fk_manufacturer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prod_manufacturer
    ADD CONSTRAINT fk_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES public.manufacturer(id);


--
-- TOC entry 2729 (class 2606 OID 16810)
-- Name: order_item fk_order_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public."order"(id);


--
-- TOC entry 2728 (class 2606 OID 16789)
-- Name: prod_manufacturer fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prod_manufacturer
    ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2726 (class 2606 OID 16747)
-- Name: product fk_product_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2730 (class 2606 OID 16815)
-- Name: order_item fk_product_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.product(id);


-- Completed on 2021-04-15 16:56:02

--
-- PostgreSQL database dump complete
--

