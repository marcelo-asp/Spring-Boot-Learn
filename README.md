# Gerenciador de Produtos

## Este projeto tem como objetivo de simular um software de gerenciamento de produtos para um mercado via web com Spring Boot

### As tecnologias/ utilizadas para o desenvolvimento foram:
* Java 21
* Spring Boot
* H2 (Banco de Dados)
* Intellij (IDE)
* API REST

### Endpoits:

### /produto
#### Buscar produto
```
GET /produto{id} -> Retorna o produto pelo id ou todos se nao for fornecido

Ex: GET /produto
retorno: 
"content": [
    {
      "id": "4e889a22-960b-4aa0-9d61-5b0a8bba48f4",
      "nome": "Banana",
      "valor": 29.99,
      "quantidade": 3,
      "dataExpiracao": "2026-10-10"
    },
    {
      "id": "3750c133-2253-494d-8a6c-040d789c4230",
      "nome": "Banana",
      "valor": 29.99,
      "quantidade": 3,
      "dataExpiracao": "2026-10-10"
    }
  ],

Codigos de retorno:
200 OK
```

#### Criar produto

```
POST /produto{id} -> Cria um produto passando suas informação em um json, se ocorrer tudo corretamente retorna o produto com id.

Ex: POST \produto
body:{
    "nome": "Banana",
      "valor": 29.99,
      "quantidade": 3,
      "dataExpiracao": "2026-10-10"
}

retorno:{
    200 OK 
    body:{
      "id": "3750c133-2253-494d-8a6c-040d789c4230",
      "nome": "Banana",
      "valor": 29.99,
      "quantidade": 3,
      "dataExpiracao": "2026-10-10"
      }
      
}

Codigos de retorno:
200 OK
400 Bad Request
```

#### Deletar produto

```
DELETE /produto{id} -> Deleta um produto passado o id dele.

Ex: DELETE /produto/3750c133-2253-494d-8a6c-040d789c4230

retorno:{
    204: No body
}

Codigos de retorno:
204 No body
404 Not Found
```