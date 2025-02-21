## Aluno
Criar Aluno:
POST http://localhost:8080/aluno

Listar Todos os Alunos:
GET http://localhost:8080/aluno

Buscar Aluno por ID:
GET http://localhost:8080/aluno/{id}
(substitua {id} pelo UUID do aluno)

Atualizar Aluno por ID:
PUT http://localhost:8080/aluno/{id}

## Endereço

Listar Todos os Endereços:
GET http://localhost:8080/api/enderecos

Buscar Endereço por ID:
GET http://localhost:8080/api/enderecos/{id}
(substitua {id} pelo UUID do endereço)

Criar Endereço:
POST http://localhost:8080/api/enderecos

Atualizar Endereço por ID:
PUT http://localhost:8080/api/enderecos/{id}

Deletar Endereço por ID:
DELETE http://localhost:8080/api/enderecos/{id}

## Funcionario

Criar Funcionário:
POST http://localhost:8080/funcionario

Listar Todos os Funcionários:
GET http://localhost:8080/funcionario

Buscar Funcionário por ID:
GET http://localhost:8080/funcionario/{id}
(substitua {id} pelo UUID do funcionário)

Atualizar Funcionário por ID:
PUT http://localhost:8080/funcionario/{id}

## Medida

Listar Todas as Medidas:
GET http://localhost:8080/medidas

Buscar Medida por ID:
GET http://localhost:8080/medidas/{id}
(substitua {id} pelo identificador da medida)

Criar Medida:
POST http://localhost:8080/medidas

Atualizar Medida por ID:
PUT http://localhost:8080/medidas/{id}

Deletar Medida por ID:
DELETE http://localhost:8080/medidas/{id}

## AlunoExercicio

Listar Todos os Registros de AlunoExercicio:
GET http://localhost:8080/api/aluno-exercicios

Buscar AlunoExercicio pela Chave Composta:
GET http://localhost:8080/api/aluno-exercicios/{idAluno}/{idExercicio}
(substitua {idAluno} e {idExercicio} pelos respectivos UUIDs)

Criar AlunoExercicio:
POST http://localhost:8080/api/aluno-exercicios

Atualizar AlunoExercicio pela Chave Composta:
PUT http://localhost:8080/api/aluno-exercicios/{idAluno}/{idExercicio}

Deletar AlunoExercicio pela Chave Composta:
DELETE http://localhost:8080/api/aluno-exercicios/{idAluno}/{idExercicio}