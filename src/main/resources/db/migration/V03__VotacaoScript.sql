CREATE SEQUENCE public.votacao_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE public.votacao
(
    id                       BIGINT         NOT NULL DEFAULT nextval('public.votacao_id_seq'::regclass),
    id_pauta                 BIGINT         NOT NULL,
    id_associado             BIGINT         NOT NULL,
    flag_votou_sim  BOOLEAN,
    -- EntidadeAuditavel.class
    dt_movimento               TIMESTAMP      NOT NULL DEFAULT now(),
    CONSTRAINT votacao_pk PRIMARY KEY (id),
    CONSTRAINT votacao_pauta_fk FOREIGN KEY (id_pauta)
        REFERENCES public.pauta (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE RESTRICT,
    CONSTRAINT votacao_associado_fk FOREIGN KEY (id_associado)
        REFERENCES public.associado (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE RESTRICT
);



