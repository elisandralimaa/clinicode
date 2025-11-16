API CRUD de Especialidades Médicas
Esta é uma API RESTful desenvolvida em Java com Spring Boot 3 para gerenciar o cadastro de especialidades médicas dentro de um sistema de fila para clínicas. 
O projeto segue a arquitetura em três camadas (Controller, Service, Repository), aplicando os princípios de Código Limpo e Soft Delete.

<h2>Tecnologias e Arquitetura</h2>
Linguagem: Java 17+

Framework: Spring Boot 3.x

Persistência: Spring Data JPA

Banco de Dados: H2 (padrão em memória para desenvolvimento)

Padrão de Design: RESTful

Arquitetura: Camadas (Controller -> Service -> Repository)

<h2>Como Executar o Projeto</h2>
Pré-requisitos
Você precisará ter instalado:

JDK 17 ou superior

Maven ou Gradle (dependendo de como você gerou o projeto)

**Clone o Repositório:**
git clone https://www.youtube.com/watch?v=m_6f3r-fwsE
cd crud-especialidades

**Execute a Aplicação:**

Maven: mvn spring-boot:run

IDE (IntelliJ/Eclipse): Execute a classe principal CrudEspecialidadesApplication.java.

<h2>Endpoints da API (CRUD)<h2>
  
O recurso base é /api/especialidades. 
A API segue estritamente o design RESTful e utiliza Soft Delete para exclusão lógica.

POST	/api/especialidades	
Cadastra uma nova especialidade (Verifica unicidade).	201 Created
GET	/api/especialidades	
Lista todas as especialidades ativas.	200 OK
GET	/api/especialidades/{id}	
Busca uma especialidade ativa pelo ID.	200 OK
PUT	/api/especialidades/{id}	
Atualiza nome/descrição da especialidade.	200 OK
DELETE	/api/especialidades/{id}	
Realiza o Soft Delete (muda ativo para false).	204 No Content






