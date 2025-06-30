<h1 align="center">
	Gerenciador de Campanhas de RPG
</h1>

<p align="center">
<img alt="Contador de linguagens" src="https://img.shields.io/github/languages/count/leanchec/projeto_mc322?color=%2304D361">
<img alt="Tamanho do repositório" src="https://img.shields.io/github/repo-size/leanchec/projeto_mc322">
<a href="https://github.com/leanchec/projeto_mc322/commits/main">
    <img alt="Último commit" src="https://img.shields.io/github/last-commit/leanchec/projeto_mc322">
</a>

</p>

## Projeto

Esse projeto foi desenvolvio para ajudar no controle de campanhas de RPG sendo uma forma de visualização gráfica intuitiva e prática para controle. O projeto foi desenvolvido durante a disciplina de MC322 da UNICAMP do Curso de Engenharia de Computação.

## Dependências

Para utilizar o sistema você precisa das bibliotecas:

<ul>
    <li><a href="https://postgresql.org">Banco de Dados PostgreSQL</a></li>
	<li><a href="https://www.oracle.com/br/java/technologies/downloads/">JRE 21</a></li>
	<li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
	<li><a href="https://spring.io/projects/spring-data-jpa">Spring Data JPA</a></li>
	<li><a href="https://spring.io/projects/spring-data-rest">Spring Data Rest</a></li>
	<li><a href="https://spring.io/projects/spring-hateoas">Spring HATEOAS</a></li>
	<li><a href="https://jdbc.postgresql.org/">PostgreSQL JDBC Driver</a></li>
</ul>

Esse repositório é apenas o backend com interface em RESTApi, o frontend para o mesmo está hospedado [neste repo](https://github.com/gvinfinity/graph-react).

## Tutorial de instalação

<ol>
	<li>Baixe/clone o repositório</li>
	<li>Instale as bibliotecas usando <code>mvn install</code></li>
	<li>-Opcional- Execute docker-compose com <code>sudo docker-compose up -d</code></li>
	<li>Configure o arquivo <code>src/main/resources/application.properties</code> para seu banco de dados</li>
	<li>Configure o arquivo <code>src/main/java/com/campanha/rpg/RpgApplication.java</code> com a url onde o frontend está hospedada</li>
    <li>Copile o projeto com <code>mvn package</code></li>
	<li>Execute o projeto com <code>java -jar target/rpg-0.0.1-SNAPSHOT.jar</code></li>
	<li>Utilize a interface que desejar para se comunicar com a API</li>
</ol>

## Time

Este projeto foi desenvolvido pelas seguintes pessoas e mantido por esses [incríveis contribuidores](https://github.com/leanchec/projeto_mc322/graphs/contributors).

| <a href="https://github.com/gvinfinity"><img src="https://avatars.githubusercontent.com/u/49999449?v=3&s=70" width="100px"/></a> | <a href="https://github.com/leanchec"><img src="https://avatars.githubusercontent.com/u/142359645?v=4" width="100px"/></a> | <a href="https://github.com/Okynaua"><img src="https://avatars.githubusercontent.com/u/75534803?v=4" width="100px"/></a> | <a href="https://github.com/Fegayotto"><img src="https://avatars.githubusercontent.com/u/202832167?v=4" width="100px"/></a> |
|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| [Gvinfinity](https://github.com/gvinfinity)                                                         | [Leanchec](https://github.com/leanchec)                                                            | [Okynaua](https://github.com/Okynaua)                                                              | [Fegayotto](https://github.com/Fegayotto)                                                          |

## 🤝 Contribuir
Contribuições, issues e pedidos de features são bem-vindos!<br />Sinta-se livre para checar a [página de issues](https://github.com/leanchec/projeto_mc322/issues). 
- Crie um fork;
- Crie um branch com a sua feature: `git checkout -b my-feature`;
- Faça um commit com as mudanças: `git commit -m 'feat: My new feature'`;
- Faça um push para o seu branch: `git push origin my-feature`.

Após a sua pull request ser aceita, você pode excluir o seu branch.
