## Grupo
Fabio Henrique Neves Reis Ribeiro - 201135013 -Ciência da computação

Marcos Roberto Chindelar de Olvieira Leite - 201165248C -Ciência da computação

Marcus Vinicius da Silva - 201365178C - Ciência da Computação

## Modelo de dados

Foram utilizadas 3 classes principais para modelar o trabalho

* Projeto
  * id - ID gerado no BD
  * descricao - Nome do projeto  
* Tarefa
  * id - ID gerado no BD
  * projeto - Objeto do projeto associado
  * status - True para projeto concluidos e False para projetos pendentes
  * inicio - Data de inicio do projeto
  * fim - Data de conclusão do projeto
  * diasConclusao - Total de dias previsto para a conclusão do projeto
  * percentual - Percentual do andamento do projeto
  
  
* Usuario
  * id - ID gerado no BD
  * nome - Nome do usuário

## Banco de dados

Foram utilizados 5 tabelas no banco de dados:

Os campos em negrito, indicam chave primária

* Projeto
  * **idProjeto** (inteiro)
  * descricao (varchar(255))
* Tarefa
  * **idTarefa** (inteiro)
  * idProjeto
  * descricao (varchar(255))
  * dataInicio (date)
  * dataFinal (date)
  * diasConclusao (inteiro)
  * percentual (inteiro)
  * status (boolean)
* TarefaAssociada
  * **idTarefa** (inteiro)
  * **idTarefaAssociada** (inteiro)
  * status (boolean)
* Usuario
  * **idUsuario** (inteiro)
  * nome (varchar(255))
* UsuarioTarefa
  * **idTarefa** (inteiro)
  * **idUsuario** (inteiro)
