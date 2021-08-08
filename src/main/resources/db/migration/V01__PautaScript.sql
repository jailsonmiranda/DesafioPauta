CREATE SEQUENCE public.pauta_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE public.pauta
(
    id                BIGINT       NOT NULL DEFAULT nextval('public.pauta_id_seq'::regclass),
    descricao         VARCHAR(250) NOT NULL,
    pauta_fechada     BOOLEAN,
    total_sim         BIGINT,
    total_nao         BIGINT,
    -- EntidadeAuditavel.class
    dt_criacao      TIMESTAMP    NOT NULL DEFAULT now(),
    dt_alteracao    TIMESTAMP,
    CONSTRAINT pauta_pk PRIMARY KEY (id)
);