Teste Java BackEnd - Api Rest

* Java 8
* Spring Mvc
* Spring Rest
* Hibernate
* JPA 2.1
* PostGreSQL
* TomCat 9.0
* Maven

Curl Para Testes -

Cadastro de Pessoa - curl --header "Content-Type: application/json" --request POST --data '{"nome":"xyz","pessoaId":"1","dataInicio":"12-12-2019","dataFim":"10-10-2019"}' http://localhost:8080/desafio/projeto

Cadastro do Projeto - curl --header "Content-Type: application/json" --request POST --data '{"nome":"xyz","dataInicio":"12-12-2019","dataFim":"10-10-2019","dataPrevisaoFim":"01-10-2019","descricao":"Projeto de App Academia Ritmos","status":"Iniciado","orcamento":"500.99","pessoaId":"1"}' http://localhost:8080/desafio/projeto

