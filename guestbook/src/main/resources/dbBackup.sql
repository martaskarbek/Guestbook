--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-08-31 02:26:17 CEST

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16907)
-- Name: entry; Type: TABLE; Schema: public; Owner: Marta
--

CREATE TABLE public.entry (
    entry text,
    name text,
    date text
);


ALTER TABLE public.entry OWNER TO "Marta";

--
-- TOC entry 3922 (class 0 OID 16907)
-- Dependencies: 202
-- Data for Name: entry; Type: TABLE DATA; Schema: public; Owner: Marta
--

INSERT INTO public.entry VALUES ('first entry', 'Marta', 'Mon Aug 31 02:23:26 CEST 2020');
INSERT INTO public.entry VALUES ('second entry', 'Marta', 'Mon Aug 31 02:23:38 CEST 2020');
INSERT INTO public.entry VALUES ('third entry', 'Marta', 'Mon Aug 31 02:23:48 CEST 2020');


-- Completed on 2020-08-31 02:26:17 CEST

--
-- PostgreSQL database dump complete
--

