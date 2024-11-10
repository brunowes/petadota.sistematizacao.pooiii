# Sistematização - POOIII

-   **Aluno**: Bruno Wesley Borges de Sousa
-   **R.A.**: 72300880
-   **Curso**: Análise e Desenvolvimento de Sistemas
-   **Professor**: Romes Heriberto Pires de Araujo

## API de Adoção de Animais - PetAdota

Aplicação web CRUD que gerencia o cadastro, atualização,
listagem e remoção de animais disponíveis para adoção. A aplicação utiliza um banco
de dados H2 para persistência e endpoints RESTful para facilitar a comunicação com
os usuários.

## Tecnologias Utilizadas

-   Java 11
-   Spring Boot
-   Spring Data JPA
-   H2 Database
-   Swagger para Documentação

## URL Base

`http://localhost:8080/api/animais`

## Instruções para Rodar o Projeto

-   Clone o repositório ou extraia o arquivo ZIP.
-   Na raiz do projeto, execute:
-   `mvn clean install`
-   `mvn spring-boot:run`

-   Acesse a documentação Swagger em: `http://localhost:8080/swagger-ui/index.html`

## Endpoints da API

**OBS** : Mais informações sobre os endpoint podem ser acessadas na seguinte URL com a aplicação em execução:
`http://localhost:8080/swagger-ui/index.html`

### Listar Todos os Animais

-   **Endpoint**: `GET /api/animais`
-   **Descrição**: Retorna uma lista de todos os animais para adoção.

### Buscar um Animal por ID

-   **Endpoint**: `GET /api/animais/{id}`
-   **Descrição**: Retorna detalhes de um animal específico.

### Adicionar um Novo Animal

-   **Endpoint**: `POST /api/animais`
-   **Descrição**: Adiciona um novo animal.
-   **Exemplo de Corpo da Requisição**:
    ```json
    {
        "nome": "Thor",
        "tipo": "Cachorro",
        "idade": 3,
        "raca": "Labrador",
        "statusAdocao": "Disponível",
        "descricao": "Amigável e brincalhão"
    }
    ```

### Atualizar um Animal

-   **Endpoint**: `PUT /api/animais/{id}`
-   **Descrição**: Atualiza as informações de um animal existente.

### Excluir um Animal

-   **Endpoint**: `DELETE /api/animais/{id}`
-   **Descrição**: Exclui um animal pelo ID.
