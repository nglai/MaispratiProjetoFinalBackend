# Projeto Final de uma aplicação para Academia.

### Para o banco de dados foi utilizado o postgres.
Caso use o usuário root, é preciso mudar no application.properties.
Caso utilize o usuário postgresmais, seguir os passos:
1. Logar com o superusuario: psql -U postgres.
2. Criar o usuário postgresmais: CREATE USER postgresmais WITH PASSWORD 'postgresmais';
3. Conceder superusuario: ALTER USER postgresmais WITH SUPERUSER;
4. Verificar se foi alterado a role: \du
5. Criar o banco de dados maispratiacademia: CREATE DATABASE maispratiacademia;
6. Listar os bancos pra ver se criou: \l
7. Conceder os privilégios: GRANT ALL PRIVILEGES ON DATABASE maispratiacademia TO postgresmais;

### Para documentar, foi utilizado o Swagger.
Acessar pela URL, depois de subir o projeto:
http://localhost:8080/swagger-ui/index.html
