## End points para verificar se rodam corretamente...

## Alunos:
GET: http://localhost:8080/alunos
GET: http://localhost:8080/alunos/{id}
POST: http://localhost:8080/alunos
PUT: http://localhost:8080/alunos/{id}
DELETE: http://localhost:8080/alunos/{id}

## Ficha:
GET: http://localhost:8080/fichas
GET por ID: http://localhost:8080/fichas/{id}
POST: http://localhost:8080/fichas
## Exemlo de post
{
"descricao": "Ficha de treino A",
"dataCriacao": "2025-01-01",
"aluno": { "id": 1 }
}

PUT: http://localhost:8080/fichas/{id}
DELETE: http://localhost:8080/fichas/{id}

## Exercicio:
GET: http://localhost:8080/exercicios
GET por ID: http://localhost:8080/exercicios/{id}
POST: http://localhost:8080/exercicios
{
"nome": "Agachamento",
"descricao": "Exercício para pernas",
"ficha": { "id": 1 }
}

PUT: http://localhost:8080/exercicios/{id}
DELETE: http://localhost:8080/exercicios/{id}

## Avaliacao:
GET: http://localhost:8080/avaliacoes
GET por ID: http://localhost:8080/avaliacoes/{id}
POST: http://localhost:8080/avaliacoes
PUT: http://localhost:8080/avaliacoes/{id}
DELETE: http://localhost:8080/avaliacoes/{id}