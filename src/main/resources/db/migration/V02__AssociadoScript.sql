CREATE SEQUENCE public.associado_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE public.associado
(
    id                BIGINT       NOT NULL DEFAULT nextval('public.associado_id_seq'::regclass),
    nomeCompleto         VARCHAR(250) NOT NULL,
    cpfcnpj         BIGINT,
    -- EntidadeAuditavel.class
    dt_criacao      TIMESTAMP    NOT NULL DEFAULT now(),
    dt_alteracao    TIMESTAMP,
    CONSTRAINT associado_pk PRIMARY KEY (id)
);

