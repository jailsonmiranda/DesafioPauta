# DesafioPauta
 Foi realizado de uma forma que fique rápida e prática as
 requisições foram feitas se baseando por vários conceitos.

#Versionamento da API
Útilizado versionamento atraves do pom alterando campo
<version>0.0.2-SNAPSHOT</version>

###Recursos utilizados:
Java 11
Maven
JPA
Slf4j
Lombok
Postgresql- senha definada no propert como postgres caso nescessite alterar.
entre outros

##ORDEM E REQUEST PARA TESTE:

### POST CRIAR PAUTA:
localhost:8080/pauta/
json
{
    "descricao": "Votação código 321 - aumento de taxa administrativa"
}

###POST CRIAR ASSOCIADO:
localhost:8080/associado/
{
    "nomecompleto" : "João da Silva",
    "cpfcnpj" : "12388683964"
}

###Put iniciar a seção da pauta:
localhost:8080/pauta/iniciarSecao/1
{
    "pauta_iniciada" : true
}

###POST REALIZAR VOTO:
{
    "pauta":{
        "id":1
    },

    "associado":{
        "id":2
    },

    "flag_votou_sim" : false
}

###Votação já encerrada ao votar:
exemplo retorno 
{
    "timestamp": 1629310019502,
    "status": 405,
    "error": "Sessão encerrada",
    "message": "Sessão de votação fechada por tempo excedido! Pauta: 1",
    "path": "/voto/"
}
                
###Put fechar a seção da pauta:
localhost:8080/pauta/fecharSecao/1

###Post contabilizar votos (imprimir):
localhost:8080/voto/contabiliza/1
exemplo retorno:
{
    "id": 1,
    "nome": "Votação código 321 - aumento de taxa administrativa",
    "totalSim": 2,
    "totalNao": 0
}
