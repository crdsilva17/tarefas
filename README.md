# API de Tarefas

API REST para criar, listar, atualizar e excluir tarefas. O projeto usa Java 21, Spring Boot, Spring Data JPA e H2.

## Requisitos

- JDK 21
- Maven (opcional: o projeto inclui o Maven Wrapper)

## Executar a aplicação

Na raiz do projeto, execute o comando correspondente ao seu sistema:

```powershell
.\mvnw.cmd spring-boot:run
```

```bash
./mvnw spring-boot:run
```

Por padrão, a aplicação usa o perfil `dev` e fica disponível em `http://localhost:8080`. Esse perfil usa um banco H2 em memória; os dados são perdidos quando a aplicação é encerrada.

Para executar os testes:

```powershell
.\mvnw.cmd test
```

```bash
./mvnw test
```

Para gerar o pacote:

```powershell
.\mvnw.cmd package
```

```bash
./mvnw package
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

O título é obrigatório e aceita até 50 caracteres. A descrição também é obrigatória. Use `true` ou `false` em `concluida` para indicar se a tarefa foi concluída.

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

Há também um perfil `prod`, que usa a porta `3001` e requer estas variáveis de ambiente para conectar ao banco de dados:

- `DATABASE_URL`
- `DATABASE_DRIVER`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`

Ative o perfil com `SPRING_PROFILES_ACTIVE=prod`. No PowerShell, por exemplo:

```powershell
$env:SPRING_PROFILES_ACTIVE = "prod"
$env:DATABASE_URL = "<url-do-banco>"
$env:DATABASE_DRIVER = "<classe-do-driver>"
$env:DATABASE_USERNAME = "<usuario>"
$env:DATABASE_PASSWORD = "<senha>"
.\mvnw.cmd spring-boot:run
```

No Bash:

```bash
export SPRING_PROFILES_ACTIVE=prod
export DATABASE_URL="<url-do-banco>"
export DATABASE_DRIVER="<classe-do-driver>"
export DATABASE_USERNAME="<usuario>"
export DATABASE_PASSWORD="<senha>"
./mvnw spring-boot:run
```

Os valores de conexão dependem do banco utilizado. Consulte `src/main/resources/application-prod.properties` para os nomes e propriedades esperados.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- H2
- Springdoc OpenAPI / Swagger UI
- Maven
