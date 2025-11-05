# Projeto Genshin Build (Trabalho de LP3)

Este é um sistema de gerenciamento de builds para o jogo Genshin Impact, desenvolvido para a disciplina de Linguagem de Programação III.

## Funcionalidades implementadas para a P1:

* Cadastro e Login de Usuários.
* Tela principal com menu de navegação.
* Visualização da Home com descrição do projeto.
* Visualização do Perfil do usuário logado.
* Listagem e visualização de Builds Guia (do site).
* Listagem e visualização de Personagens, Armas e Artefatos cadastrados.
* Fluxo inicial para criação de Builds de Usuário (formulário estático).

## Como Configurar e Executar

### Eu utilizei nas configurações:

* Java JDK 21.
* Apache Maven.
* Servidor MySQL.

### 1. Configuração do Banco de Dados

1.  Eu utilizei o MySQL DBeaver (Workbench e outros é só utilizar o arquivo).
2.  Importe e execute o arquivo `database.sql` (incluído neste projeto). Isso criará o banco `genshin_db` e inserirá os dados de exemplo.

### 2. Configuração do Projeto (Importante!)

1.  Na pasta `src/main/resources/`, crie um novo arquivo chamado `config.properties`.
2.  **Nota:** Eu intencionalmente coloquei `.gitignore` para proteger senhas e dados sensíveis de conexão.
3.  Cole o seguinte conteúdo dentro do `config.properties` que você criou, alterando `SEU_USUARIO_MYSQL` e `SUA_SENHA_MYSQL` para o que você utilizar:

    ```properties
    db.url=jdbc:mysql://localhost:3306/genshin_db
    db.user=SEU_USUARIO_MYSQL
    db.password=SUA_SENHA_MYSQL
    ```

### 3. Execução

Após configurar o banco e as propriedades, você pode executar o projeto via Maven:

```bash
mvn clean javafx:run