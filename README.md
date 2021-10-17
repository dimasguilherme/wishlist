# Projeto Wishlist

Esse projeto consome uma api de listagem de produtos, disponibilizada pela Luizalabs e permite a clientes cadastrados que criem e editem sua lista de produtos favoritos.

## License

[Apache License 2.0](https://github.com/dimasguilherme/wishlist/blob/master/LICENSE)

## Tecnologias Utilizadas

- [Quakus](https://quarkus.io/) - Framework para criação de microserviços em Java.
- [Mysql](https://www.mysql.com/) - Banco de dados relacional.
- [Docker](https://www.docker.com/) - Plataforma open source que facilita a criação e administração de ambientes isolados. Ele possibilita o empacotamento de uma aplicação ou ambiente dentro de um container, se tornando portátil para qualquer outro host que contenha o Docker instalado.
- [Maven](https://maven.apache.org/) - Ferramenta de automação de compilação utilizada em projetos Java.
- [Hibernate](https://hibernate.org/) - Framework para persistência de dados. (ORM)
- [Panache](https://quarkus.io/guides/hibernate-orm-panache) - Simplifica a camada de persistência.
- [Security Jpa](https://quarkus.io/guides/security-jpa) - Utilizado no controle de autenticação e de autorização de acesso aos recursos do projeto.

## Requisitos para Executar o Projeto

Durante o desenvolvimento do projeto foram utilizadas as seguintes versões de sofwares:

- Windows 10
- Apache Maven 3.6.3
- Open JDK 11
- Docker Desktop 3.6.0

O uso de versões superiores as indicadas acima devem ser compatíveis com o projeto, não é recomendado uso de versões anteriores.

O uso de uma distribuição Linux é compatível com o projeto, desde que tenha os softwares acima instalados e configurados.

## Executando o Projeto

- Faça o clone do projeto: `git clone https://github.com/dimasguilherme/wishlist.git`
- Rode o projeto: No linux `./run.sh`, no windows `.\run.cmd` Esses scripts irão realizar o build e o start das imagens do projeto e do mysql. A api ficará disponível em `http://localhost:8080/api`
- Caso queria rodar o projeto localmente (fora de um container) para aproveitar recursos de 'live code' do Quarkus e alterar o código, você pode usar o arquivo `docker-compose-db-only.yml`. Para isso basta executar o comando `docker-compose -f docker-compose-db-only.yml up`, isso irá expor um servidor mysql na porta `3306` e o adminer (interface gráfica para administração do banco mysql) no endereço `http://localhost:8081`. O usuário do banco é `root` e a senha é `senha`. Agora pra executar o projeto basta o comando `mvn quarkus:dev`

## Regras de Autenticação e Autorização

Para o controle do acesso aos recursos do projeto, foram criadas duas roles (admin e client).

- `admin`: Administrador, responsável pela administração do sistema. O administrador pode listar clientes, deletar clientes, alterar clientes, listar produtos favoritos de qualquer cliente.
- `client`: Cliente do sistema. Pode listar seu próprio cadastro, adicionar ou remover produtos a sua lista de favoritos e listar seus produtos favoritos.
- Os recursos de incluir clientes, listar produtos e detalhes de produtos são abertos. Não são necessárias autenticação e autorização.

Para definir a role de um cliente, foi implementada a seguinte regra:

- Qualquer cliente que tiver admin no endereço de e-mail se torna admisnitrador do sistema. Claro que isso é apenas para exemplificar o controle de autorização dos recursos.
- Qualquer cliente cadastrado, que não se encaixa na regra acima, é apenas um cliente comum.

## Entidades

### Produtos

Essa entidade é composta por:

- `price`: preço do produto
- `image`: URL da imagem do produto
- `brand`: marca do produto
- `id`: id do produto
- `title`: nome do produto
- `reviewScore`: média dos reviews para este produto

Os endpoints de produtos e os métodos para utilizá-los são:

- `[GET] /api/product/?page=<pagina>`: Lista uma página dos produtos disponíveis na api fornecida pelo Magalu, onde `<pagina>` é um valor inteiro para paginar os resultados
- `[GET] /api/product/<id>/`: Detalha o produto cujo id é o valor passado em `<id>`

### Clientes

Essa entidade é composta por:

- `email`: email do cliente, utilizado também como usuario da autenticação
- `name`: nome do cliente
- `password`: senha do cliente, utilizada na autenticação
- `role`: perfil de acesso, criado conforme regras explicadas em "Regras de Autenticação e Autorização"

Os endpoints de cliente e os métodos para utilizá-los são:

- `[GET] /api/client`: Lista todos os clientes cadastrados
- `[POST] /api/client`: Grava um novo cliente, deve ser enviado email, name e password
- `[GET] /api/client/<email>/`: Detalha o cliente cujo e-mail é o valor passado em `<email>`
- `[DELETE] /api/client/<email>/`: Deleta o cliente cujo e-mail é o valor passado em `<email>`
- `[PUT] /api/client/<email>/`: Altera o cliente cujo e-mail é o valor passado em `<email>`, esse endpoint não altera a senha nem role (essas são criadas apenas no POST do cliente)

Durante a criação da imagem do banco de dados é criado um cliente administrador, email `admin@admin.com`, senha `123456`, você pode usá-lo para administrar os dados ou criar outro conforme as regras já informadas.

### Wishlist

Os endpoints de wishlist e os métodos para utilizá-los são:

- `[GET] /api/wishlist/add/<productId>/`: Adiciona o produto cujo id é o valor passado em `<productId>` na lista de favoritos do cliente autenticado
- `[GET] /api/wishlist/remove/<productId>/`: Remove o produto cujo id é o valor passado em `<productId>` da lista de favoritos do cliente autenticado
- `[GET] /api/wishlist/<email>/`: Retorna a lista de produtos favoritos do cliente cujo e-mail é o valor passado em `<email>`