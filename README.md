# Gerador de Nota Fiscal


MVP do desafio do Gerador de NF

Nesse desafio foi feito um refact de arquivos de service com intuito de isolar algumas logicas e desacoplamento, dessa forma, temos métodos especificos para cada validaçao e preparados para a adiçao ou subtraçao de alguma regra, demodo que impacte apenas em um arquivo
Foi feito também a criaçao de uma validaçao do body utilizando um validator e ajustando os valores da model para setar o que pode ou não faltar
Criaçao de um multi thread para chamadas simultaneas 
Remoçao do sleep de 5 s para ajuste de performance 

Como melhorar e proximos passos

TO-DO
- Utilizaçao de funçoes lambdas da AWS e chamada de um serviço SNS para as chamadas paralelas de modo a trazer mais dinamismo e tornando mais escalável
- Criaçao de um middleware mais aprofundado de error handler para retornar o erro especifico do campo que veio em branco e retornar http status 400
- Testes unitários das validaçoes, das strategys, e do e2e da rota, validando, através de um mock, a response para o status de CREATED ou para casos de erro
