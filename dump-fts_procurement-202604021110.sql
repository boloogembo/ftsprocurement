--
-- PostgreSQL database dump
--

\restrict 0BSr7TG7AFlhIWvSpCPfb9UBTpI9uPYwIgFsEDTVpcS8eVkxJ2qoBBDqWgsObnJ

-- Dumped from database version 17.7
-- Dumped by pg_dump version 17.7

-- Started on 2026-04-02 14:10:33 EAT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 60452)
-- Name: fts; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA fts;


ALTER SCHEMA fts OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 227 (class 1259 OID 72155)
-- Name: approval_actions; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.approval_actions (
    id bigint NOT NULL,
    request_id bigint NOT NULL,
    step_id bigint NOT NULL,
    designation character varying(128) NOT NULL,
    denomination_id integer NOT NULL,
    user_id bigint,
    action character varying(20) NOT NULL,
    weight numeric(5,2),
    comment text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE fts.approval_actions OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 72154)
-- Name: approval_actions_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.approval_actions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.approval_actions_id_seq OWNER TO postgres;

--
-- TOC entry 3905 (class 0 OID 0)
-- Dependencies: 226
-- Name: approval_actions_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.approval_actions_id_seq OWNED BY fts.approval_actions.id;


--
-- TOC entry 223 (class 1259 OID 72112)
-- Name: approval_requests; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.approval_requests (
    id bigint NOT NULL,
    workflow_id bigint NOT NULL,
    reference_number character varying(100) NOT NULL,
    organisation_id bigint NOT NULL,
    amount numeric(18,2) NOT NULL,
    status character varying(20) DEFAULT 'PENDING'::character varying NOT NULL,
    current_step_id bigint,
    created_by bigint,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    department_name character varying(128),
    project_name character varying(255),
    approval_deadline timestamp without time zone
);


ALTER TABLE fts.approval_requests OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 72111)
-- Name: approval_requests_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.approval_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.approval_requests_id_seq OWNER TO postgres;

--
-- TOC entry 3906 (class 0 OID 0)
-- Dependencies: 222
-- Name: approval_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.approval_requests_id_seq OWNED BY fts.approval_requests.id;


--
-- TOC entry 225 (class 1259 OID 72135)
-- Name: approval_step_instances; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.approval_step_instances (
    id bigint NOT NULL,
    request_id bigint NOT NULL,
    step_id bigint NOT NULL,
    status character varying(20) DEFAULT 'PENDING'::character varying,
    started_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    completed_at timestamp without time zone
);


ALTER TABLE fts.approval_step_instances OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 72134)
-- Name: approval_step_instances_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.approval_step_instances_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.approval_step_instances_id_seq OWNER TO postgres;

--
-- TOC entry 3907 (class 0 OID 0)
-- Dependencies: 224
-- Name: approval_step_instances_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.approval_step_instances_id_seq OWNED BY fts.approval_step_instances.id;


--
-- TOC entry 235 (class 1259 OID 72544)
-- Name: approval_votes; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.approval_votes (
    id bigint NOT NULL,
    request_id bigint NOT NULL,
    step_id bigint NOT NULL,
    voter_id bigint NOT NULL,
    designation character varying(128) NOT NULL,
    organisation_id bigint NOT NULL,
    vote character varying(20) NOT NULL,
    weight numeric(5,2) DEFAULT 1,
    comment text,
    voted_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT approval_votes_vote_check CHECK (((vote)::text = ANY ((ARRAY['APPROVED'::character varying, 'REJECTED'::character varying])::text[])))
);


ALTER TABLE fts.approval_votes OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 72543)
-- Name: approval_votes_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.approval_votes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.approval_votes_id_seq OWNER TO postgres;

--
-- TOC entry 3908 (class 0 OID 0)
-- Dependencies: 234
-- Name: approval_votes_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.approval_votes_id_seq OWNED BY fts.approval_votes.id;


--
-- TOC entry 231 (class 1259 OID 72525)
-- Name: department_projects; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.department_projects (
    id bigint NOT NULL,
    organisation_id integer NOT NULL,
    department_name character varying(128) NOT NULL,
    project_name character varying(255) NOT NULL,
    description text,
    created_by bigint,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE fts.department_projects OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 72524)
-- Name: department_projects_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.department_projects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.department_projects_id_seq OWNER TO postgres;

--
-- TOC entry 3909 (class 0 OID 0)
-- Dependencies: 230
-- Name: department_projects_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.department_projects_id_seq OWNED BY fts.department_projects.id;


--
-- TOC entry 229 (class 1259 OID 72177)
-- Name: leadership_assignments; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.leadership_assignments (
    id bigint NOT NULL,
    designation character varying(128) NOT NULL,
    denomination_id integer NOT NULL,
    personnel_id bigint NOT NULL,
    organisation_id bigint NOT NULL,
    active boolean DEFAULT true
);


ALTER TABLE fts.leadership_assignments OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 72176)
-- Name: leadership_assignments_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.leadership_assignments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.leadership_assignments_id_seq OWNER TO postgres;

--
-- TOC entry 3910 (class 0 OID 0)
-- Dependencies: 228
-- Name: leadership_assignments_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.leadership_assignments_id_seq OWNED BY fts.leadership_assignments.id;


--
-- TOC entry 233 (class 1259 OID 72535)
-- Name: project_budget_items; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.project_budget_items (
    id bigint NOT NULL,
    project_id bigint NOT NULL,
    item_name character varying(255) NOT NULL,
    quantity integer DEFAULT 1,
    unit_cost numeric(12,2) NOT NULL,
    total_cost numeric(12,2) GENERATED ALWAYS AS (((quantity)::numeric * unit_cost)) STORED
);


ALTER TABLE fts.project_budget_items OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 72534)
-- Name: project_budget_items_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.project_budget_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.project_budget_items_id_seq OWNER TO postgres;

--
-- TOC entry 3911 (class 0 OID 0)
-- Dependencies: 232
-- Name: project_budget_items_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.project_budget_items_id_seq OWNED BY fts.project_budget_items.id;


--
-- TOC entry 221 (class 1259 OID 72000)
-- Name: workflow_steps; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.workflow_steps (
    id bigint NOT NULL,
    workflow_id bigint NOT NULL,
    step_order integer NOT NULL,
    step_name character varying(150) NOT NULL,
    approval_mode character varying(30) NOT NULL,
    threshold_value numeric(10,2),
    min_amount numeric(18,2) DEFAULT 0,
    max_amount numeric(18,2),
    is_final_step boolean DEFAULT false,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE fts.workflow_steps OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 71999)
-- Name: workflow_steps_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.workflow_steps_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.workflow_steps_id_seq OWNER TO postgres;

--
-- TOC entry 3912 (class 0 OID 0)
-- Dependencies: 220
-- Name: workflow_steps_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.workflow_steps_id_seq OWNED BY fts.workflow_steps.id;


--
-- TOC entry 219 (class 1259 OID 71990)
-- Name: workflows; Type: TABLE; Schema: fts; Owner: postgres
--

CREATE TABLE fts.workflows (
    id bigint NOT NULL,
    name character varying(150) NOT NULL,
    denomination_id integer NOT NULL,
    organisation_level integer,
    active boolean DEFAULT true,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE fts.workflows OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 71989)
-- Name: workflows_id_seq; Type: SEQUENCE; Schema: fts; Owner: postgres
--

CREATE SEQUENCE fts.workflows_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE fts.workflows_id_seq OWNER TO postgres;

--
-- TOC entry 3913 (class 0 OID 0)
-- Dependencies: 218
-- Name: workflows_id_seq; Type: SEQUENCE OWNED BY; Schema: fts; Owner: postgres
--

ALTER SEQUENCE fts.workflows_id_seq OWNED BY fts.workflows.id;


--
-- TOC entry 3686 (class 2604 OID 72158)
-- Name: approval_actions id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_actions ALTER COLUMN id SET DEFAULT nextval('fts.approval_actions_id_seq'::regclass);


--
-- TOC entry 3680 (class 2604 OID 72115)
-- Name: approval_requests id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_requests ALTER COLUMN id SET DEFAULT nextval('fts.approval_requests_id_seq'::regclass);


--
-- TOC entry 3683 (class 2604 OID 72138)
-- Name: approval_step_instances id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_step_instances ALTER COLUMN id SET DEFAULT nextval('fts.approval_step_instances_id_seq'::regclass);


--
-- TOC entry 3695 (class 2604 OID 72547)
-- Name: approval_votes id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_votes ALTER COLUMN id SET DEFAULT nextval('fts.approval_votes_id_seq'::regclass);


--
-- TOC entry 3690 (class 2604 OID 72528)
-- Name: department_projects id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.department_projects ALTER COLUMN id SET DEFAULT nextval('fts.department_projects_id_seq'::regclass);


--
-- TOC entry 3688 (class 2604 OID 72180)
-- Name: leadership_assignments id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.leadership_assignments ALTER COLUMN id SET DEFAULT nextval('fts.leadership_assignments_id_seq'::regclass);


--
-- TOC entry 3692 (class 2604 OID 72538)
-- Name: project_budget_items id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.project_budget_items ALTER COLUMN id SET DEFAULT nextval('fts.project_budget_items_id_seq'::regclass);


--
-- TOC entry 3676 (class 2604 OID 72003)
-- Name: workflow_steps id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.workflow_steps ALTER COLUMN id SET DEFAULT nextval('fts.workflow_steps_id_seq'::regclass);


--
-- TOC entry 3673 (class 2604 OID 71993)
-- Name: workflows id; Type: DEFAULT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.workflows ALTER COLUMN id SET DEFAULT nextval('fts.workflows_id_seq'::regclass);


--
-- TOC entry 3891 (class 0 OID 72155)
-- Dependencies: 227
-- Data for Name: approval_actions; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.approval_actions (id, request_id, step_id, designation, denomination_id, user_id, action, weight, comment, created_at) FROM stdin;
\.


--
-- TOC entry 3887 (class 0 OID 72112)
-- Dependencies: 223
-- Data for Name: approval_requests; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.approval_requests (id, workflow_id, reference_number, organisation_id, amount, status, current_step_id, created_by, created_at, department_name, project_name, approval_deadline) FROM stdin;
\.


--
-- TOC entry 3889 (class 0 OID 72135)
-- Dependencies: 225
-- Data for Name: approval_step_instances; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.approval_step_instances (id, request_id, step_id, status, started_at, completed_at) FROM stdin;
\.


--
-- TOC entry 3899 (class 0 OID 72544)
-- Dependencies: 235
-- Data for Name: approval_votes; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.approval_votes (id, request_id, step_id, voter_id, designation, organisation_id, vote, weight, comment, voted_at) FROM stdin;
\.


--
-- TOC entry 3895 (class 0 OID 72525)
-- Dependencies: 231
-- Data for Name: department_projects; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.department_projects (id, organisation_id, department_name, project_name, description, created_by, created_at) FROM stdin;
\.


--
-- TOC entry 3893 (class 0 OID 72177)
-- Dependencies: 229
-- Data for Name: leadership_assignments; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.leadership_assignments (id, designation, denomination_id, personnel_id, organisation_id, active) FROM stdin;
\.


--
-- TOC entry 3897 (class 0 OID 72535)
-- Dependencies: 233
-- Data for Name: project_budget_items; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.project_budget_items (id, project_id, item_name, quantity, unit_cost) FROM stdin;
\.


--
-- TOC entry 3885 (class 0 OID 72000)
-- Dependencies: 221
-- Data for Name: workflow_steps; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.workflow_steps (id, workflow_id, step_order, step_name, approval_mode, threshold_value, min_amount, max_amount, is_final_step, created_at) FROM stdin;
\.


--
-- TOC entry 3883 (class 0 OID 71990)
-- Dependencies: 219
-- Data for Name: workflows; Type: TABLE DATA; Schema: fts; Owner: postgres
--

COPY fts.workflows (id, name, denomination_id, organisation_level, active, created_at) FROM stdin;
\.


--
-- TOC entry 3914 (class 0 OID 0)
-- Dependencies: 226
-- Name: approval_actions_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.approval_actions_id_seq', 1, false);


--
-- TOC entry 3915 (class 0 OID 0)
-- Dependencies: 222
-- Name: approval_requests_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.approval_requests_id_seq', 1, false);


--
-- TOC entry 3916 (class 0 OID 0)
-- Dependencies: 224
-- Name: approval_step_instances_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.approval_step_instances_id_seq', 1, false);


--
-- TOC entry 3917 (class 0 OID 0)
-- Dependencies: 234
-- Name: approval_votes_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.approval_votes_id_seq', 1, false);


--
-- TOC entry 3918 (class 0 OID 0)
-- Dependencies: 230
-- Name: department_projects_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.department_projects_id_seq', 1, false);


--
-- TOC entry 3919 (class 0 OID 0)
-- Dependencies: 228
-- Name: leadership_assignments_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.leadership_assignments_id_seq', 1, false);


--
-- TOC entry 3920 (class 0 OID 0)
-- Dependencies: 232
-- Name: project_budget_items_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.project_budget_items_id_seq', 1, false);


--
-- TOC entry 3921 (class 0 OID 0)
-- Dependencies: 220
-- Name: workflow_steps_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.workflow_steps_id_seq', 1, false);


--
-- TOC entry 3922 (class 0 OID 0)
-- Dependencies: 218
-- Name: workflows_id_seq; Type: SEQUENCE SET; Schema: fts; Owner: postgres
--

SELECT pg_catalog.setval('fts.workflows_id_seq', 1, false);


--
-- TOC entry 3717 (class 2606 OID 72163)
-- Name: approval_actions approval_actions_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_actions
    ADD CONSTRAINT approval_actions_pkey PRIMARY KEY (id);


--
-- TOC entry 3708 (class 2606 OID 72119)
-- Name: approval_requests approval_requests_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_requests
    ADD CONSTRAINT approval_requests_pkey PRIMARY KEY (id);


--
-- TOC entry 3714 (class 2606 OID 72142)
-- Name: approval_step_instances approval_step_instances_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_step_instances
    ADD CONSTRAINT approval_step_instances_pkey PRIMARY KEY (id);


--
-- TOC entry 3727 (class 2606 OID 72554)
-- Name: approval_votes approval_votes_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_votes
    ADD CONSTRAINT approval_votes_pkey PRIMARY KEY (id);


--
-- TOC entry 3723 (class 2606 OID 72533)
-- Name: department_projects department_projects_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.department_projects
    ADD CONSTRAINT department_projects_pkey PRIMARY KEY (id);


--
-- TOC entry 3721 (class 2606 OID 72183)
-- Name: leadership_assignments leadership_assignments_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.leadership_assignments
    ADD CONSTRAINT leadership_assignments_pkey PRIMARY KEY (id);


--
-- TOC entry 3725 (class 2606 OID 72542)
-- Name: project_budget_items project_budget_items_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.project_budget_items
    ADD CONSTRAINT project_budget_items_pkey PRIMARY KEY (id);


--
-- TOC entry 3712 (class 2606 OID 72121)
-- Name: approval_requests unique_reference; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_requests
    ADD CONSTRAINT unique_reference UNIQUE (reference_number);


--
-- TOC entry 3704 (class 2606 OID 72010)
-- Name: workflow_steps unique_step_order; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.workflow_steps
    ADD CONSTRAINT unique_step_order UNIQUE (workflow_id, step_order);


--
-- TOC entry 3729 (class 2606 OID 72556)
-- Name: approval_votes unique_vote_per_user; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_votes
    ADD CONSTRAINT unique_vote_per_user UNIQUE (request_id, step_id, voter_id);


--
-- TOC entry 3706 (class 2606 OID 72008)
-- Name: workflow_steps workflow_steps_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.workflow_steps
    ADD CONSTRAINT workflow_steps_pkey PRIMARY KEY (id);


--
-- TOC entry 3701 (class 2606 OID 71997)
-- Name: workflows workflows_pkey; Type: CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.workflows
    ADD CONSTRAINT workflows_pkey PRIMARY KEY (id);


--
-- TOC entry 3718 (class 1259 OID 72174)
-- Name: idx_actions_request; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_actions_request ON fts.approval_actions USING btree (request_id);


--
-- TOC entry 3719 (class 1259 OID 72175)
-- Name: idx_actions_step; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_actions_step ON fts.approval_actions USING btree (step_id);


--
-- TOC entry 3709 (class 1259 OID 72133)
-- Name: idx_requests_status; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_requests_status ON fts.approval_requests USING btree (status);


--
-- TOC entry 3710 (class 1259 OID 72132)
-- Name: idx_requests_workflow; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_requests_workflow ON fts.approval_requests USING btree (workflow_id);


--
-- TOC entry 3715 (class 1259 OID 72153)
-- Name: idx_step_instances_request; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_step_instances_request ON fts.approval_step_instances USING btree (request_id);


--
-- TOC entry 3702 (class 1259 OID 72016)
-- Name: idx_steps_workflow; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_steps_workflow ON fts.workflow_steps USING btree (workflow_id);


--
-- TOC entry 3699 (class 1259 OID 71998)
-- Name: idx_workflows_denomination; Type: INDEX; Schema: fts; Owner: postgres
--

CREATE INDEX idx_workflows_denomination ON fts.workflows USING btree (denomination_id);


--
-- TOC entry 3735 (class 2606 OID 72164)
-- Name: approval_actions approval_actions_request_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_actions
    ADD CONSTRAINT approval_actions_request_id_fkey FOREIGN KEY (request_id) REFERENCES fts.approval_requests(id) ON DELETE CASCADE;


--
-- TOC entry 3736 (class 2606 OID 72169)
-- Name: approval_actions approval_actions_step_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_actions
    ADD CONSTRAINT approval_actions_step_id_fkey FOREIGN KEY (step_id) REFERENCES fts.workflow_steps(id);


--
-- TOC entry 3731 (class 2606 OID 72127)
-- Name: approval_requests approval_requests_current_step_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_requests
    ADD CONSTRAINT approval_requests_current_step_id_fkey FOREIGN KEY (current_step_id) REFERENCES fts.workflow_steps(id);


--
-- TOC entry 3732 (class 2606 OID 72122)
-- Name: approval_requests approval_requests_workflow_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_requests
    ADD CONSTRAINT approval_requests_workflow_id_fkey FOREIGN KEY (workflow_id) REFERENCES fts.workflows(id);


--
-- TOC entry 3733 (class 2606 OID 72143)
-- Name: approval_step_instances approval_step_instances_request_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_step_instances
    ADD CONSTRAINT approval_step_instances_request_id_fkey FOREIGN KEY (request_id) REFERENCES fts.approval_requests(id) ON DELETE CASCADE;


--
-- TOC entry 3734 (class 2606 OID 72148)
-- Name: approval_step_instances approval_step_instances_step_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.approval_step_instances
    ADD CONSTRAINT approval_step_instances_step_id_fkey FOREIGN KEY (step_id) REFERENCES fts.workflow_steps(id);


--
-- TOC entry 3730 (class 2606 OID 72011)
-- Name: workflow_steps workflow_steps_workflow_id_fkey; Type: FK CONSTRAINT; Schema: fts; Owner: postgres
--

ALTER TABLE ONLY fts.workflow_steps
    ADD CONSTRAINT workflow_steps_workflow_id_fkey FOREIGN KEY (workflow_id) REFERENCES fts.workflows(id) ON DELETE CASCADE;


-- Completed on 2026-04-02 14:10:57 EAT

--
-- PostgreSQL database dump complete
--

\unrestrict 0BSr7TG7AFlhIWvSpCPfb9UBTpI9uPYwIgFsEDTVpcS8eVkxJ2qoBBDqWgsObnJ

