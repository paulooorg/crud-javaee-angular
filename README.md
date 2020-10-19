# CRUD simples com Java EE e Angular

Esse é um projeto simples com um CRUD de tarefas, usando Java EE 8 e Angular.

# Como executar o projeto?

A pasta [webapp](/webapp) contém o projeto em Angular, dentro dela basta executar:

```bash
npm start
```

A pasta [api](/api) contém o Back-end, para executar o projeto pode ser utilizado o script:

```bash
.\run.bat
```

Se você tiver um servidor de aplicação WildFly instalado, basta configurar o Datasource para o PostgreSQL, as configurações do driver estão na pasta [docs](/docs).

O JNDI utilizado no projeto é:

```bash
java:jboss/datasources/PostgreSQLDS
```

Com o Wildfly configurado, poderá executar um comando maven com a profile *local*:

```bash
mvn clean install -Plocal
```

Isso faz o Deploy automático no WildFly

## Usando o modo dev do WildFly

Caso queira utilizar o modo dev do WildFly 21, poderá executar os scripts:

1. iniciar o modo desenvolvimento

```bash
.\start-dev.bat
```

Após fazer alterações no Back-end poderá executar outro script para fazer o re-deploy

2. Re-deploy

```bash
.\redeploy.bat
```

Para encerrar o modo dev poderá executar o script:

```bash
.\kill-dev.bat
```