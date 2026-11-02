# 07 - SPRING WEB

API REST com Spring Boot contendo dois CRUDs: **Biblioteca** (`/livros`) e **Gerenciamento de Tarefas** (`/tarefas`).

## Tecnologias

- Java 17
- Spring Boot 4.1.2 (Web MVC, Data JPA, Validation)
- H2 (banco em memória)
- Maven (wrapper incluso)

## Como executar

Pré-requisito: JDK 17 ou superior.

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

> O banco H2 é em memória: os dados são apagados sempre que a aplicação é reiniciada.

## Estrutura

```
src/main/java/br/edu/fatecpg/springweb/
├── controller/   LivroController, TarefaController, ApiExceptionHandler
├── model/        Livro, Tarefa
└── repository/   LivroRepository, TarefaRepository
```

## Endpoints

Os dois recursos têm os mesmos cinco endpoints.

| Método | Rota | Descrição | Sucesso |
|--------|------|-----------|---------|
| POST | `/livros` · `/tarefas` | Cria um registro | 201 |
| GET | `/livros` · `/tarefas` | Lista todos | 200 |
| GET | `/livros/{id}` · `/tarefas/{id}` | Busca por id | 200 |
| PUT | `/livros/{id}` · `/tarefas/{id}` | Atualiza | 200 |
| DELETE | `/livros/{id}` · `/tarefas/{id}` | Remove | 204 |

Códigos de erro: **404** (id não encontrado), **400** (validação) e **409** (violação de unicidade, ex.: ISBN repetido).

## Modelos

### Livro

| Campo | Tipo | Regra |
|-------|------|-------|
| id | Long | chave primária, gerada automaticamente |
| titulo | String | obrigatório |
| autor | String | obrigatório |
| isbn | String | obrigatório e único |
| anoPublicacao | Integer | opcional |

```json
{ "titulo": "Dom Casmurro", "autor": "Machado de Assis", "isbn": "978-8535910663", "anoPublicacao": 1899 }
```

### Tarefa

| Campo | Tipo | Regra |
|-------|------|-------|
| id | Long | chave primária, gerada automaticamente |
| descricao | String | obrigatória |
| concluida | Boolean | padrão `false` |
| prioridade | Integer | de 1 a 5 |

```json
{ "descricao": "Estudar Spring", "concluida": false, "prioridade": 3 }
```

## Testando no Postman

1. `POST http://localhost:8080/livros` com o JSON de exemplo (Body → raw → JSON).
2. `GET http://localhost:8080/livros` para listar e `GET .../livros/1` para buscar um.
3. `PUT http://localhost:8080/livros/1` com o JSON atualizado.
4. `DELETE http://localhost:8080/livros/1` para remover.
5. Repita com `/tarefas`.

Casos de erro para conferir: ISBN repetido (409), livro sem título (400), tarefa com prioridade 7 (400) e id inexistente (404).
