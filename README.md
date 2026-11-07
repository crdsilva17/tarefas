# API de Tarefas

API REST para criar, listar, atualizar e excluir tarefas. O projeto usa Java 21, Spring Boot, Spring Data JPA e H2.

## Requisitos

- JDK 21
- Maven (opcional: o projeto inclui o Maven Wrapper)

## Executar a aplicação

Na raiz do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Por padrão, a aplicação usa o perfil `dev` e fica disponível em `http://localhost:8080`. Esse perfil usa um banco H2 em memória; os dados são perdidos quando a aplicação é encerrada.

Para executar os testes:

```powershell
.\mvnw.cmd test
```

Para gerar o pacote:

```powershell
.\mvnw.cmd package
```

## Documentação interativa

Com a aplicação em execução, acesse a interface Swagger em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). A especificação OpenAPI está disponível em [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).

## Endpoints

Todas as rotas usam o prefixo `/api/v1/tarefas`.

| Método | Rota | Descrição | Resposta de sucesso |
|---|---|---|---|
| `GET` | `/api/v1/tarefas` | Lista todas as tarefas | `200 OK` |
| `POST` | `/api/v1/tarefas` | Cria uma tarefa | `200 OK` |
| `PUT` | `/api/v1/tarefas/{id}` | Atualiza uma tarefa existente | `200 OK` |
| `DELETE` | `/api/v1/tarefas/{id}` | Exclui uma tarefa | `204 No Content` |

### Criar uma tarefa

```http
POST /api/v1/tarefas
Content-Type: application/json
```

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar os conceitos de API REST",
  "concluida": false
}
```

O título é obrigatório e aceita até 50 caracteres. A descrição também é obrigatória. O campo `concluida` deve ser informado como `true` ou `false`.

Exemplo de resposta:

```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar os conceitos de API REST",
  "concluida": false
}
```

Para atualizar, envie os mesmos campos para `PUT /api/v1/tarefas/{id}`. Para excluir, informe o identificador em `DELETE /api/v1/tarefas/{id}`.

## Perfis e banco de dados

O perfil ativo por padrão está definido em `src/main/resources/application.properties` como `dev`. Ele usa o H2 em memória e configura a porta `8080`.

Há também um perfil `prod`, que usa variáveis de ambiente para configurar a conexão com o banco de dados. Consulte `src/main/resources/application-prod.properties` e defina os valores exigidos antes de executar a aplicação nesse perfil. Para ativá-lo, configure `SPRING_PROFILES_ACTIVE=prod`.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- H2
- Springdoc OpenAPI / Swagger UI
- Maven
