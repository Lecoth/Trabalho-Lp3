# Projeto Genshin Build (Trabalho de LP3)

Este é um sistema de gerenciamento de builds para o jogo Genshin Impact, desenvolvido para a disciplina de Linguagem de Programação III.

## Funcionalidades implementadas:

* Cadastro e Login de Usuários com autentificação.
* Tela principal com menu de navegação.
* Visualização da Home com descrição do projeto.
* Visualização do Perfil do usuário logado.
* Listagem e visualização de Builds Guia (do site).
* Listagem e visualização de Builds de Usuários.
* Listagem e visualização de Personagens, Armas e Artefatos cadastrados.
* Controle de Acesso: Diferenciação entre usuários comuns e Administradores
* Painel Administrativo: Cadastro, edição e exclusão de Personagens, Armas, Artefatos e Builds Guia (CRUD completo).
* Gerenciamento de Builds de Usuário:
Criação de builds personalizadas com seleção de equipamentos e status.
Opção de privacidade (Builds Públicas vs. Privadas).
Edição e exclusão das próprias builds.
* Sistema de Busca: Pesquisa global por nome de personagem ou título de build na tela principal (quando digita três letras já começa a pesuisar a build).
* Sistema de Favoritos: Usuários podem favoritar builds (Guia ou de Usuários) para acesso rápido no perfil (tendo no máximo 4 builds favoritadas).

## Usuários para Teste (Já incluídos no database.sql)

* **Administrador:**
    * Email: `lele@gmail.com`
    * Senha: `senha`
    * *Acesso total ao painel de cadastro de itens do jogo.*

* **Usuário Comum:**
    * Email: `le23@gmail.com`
    * Senha: `123`
    * *Pode criar builds, favoritar e visualizar guias.*

## Problemas Comuns e Soluções

### Erro no `module-info.java` (Duplicação de Requires)
Se ao rodar o projeto aparecer um erro relacionado ao `module-info.java` exigindo o próprio módulo, verifique se há uma linha duplicada como `requires com.example.trabalholp3;`.
* **Solução:** Apague essa linha manualmente no arquivo `src/main/java/module-info.java` e recarregue o Maven. Isso é um bug conhecido de importação automática em algumas IDEs.

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