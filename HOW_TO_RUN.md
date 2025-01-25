# How To Run This Application Manually!

If you're not accommodate using Docker, here's how to run application manually.

> Prior to run this application, some _**environment variables**_ must be set, se Environment Variables section in [README.md](README.md) for further instructions.
> Also a proper installation of Maven and PostgreSQL are required to go any further with this.

**01 - Cloning the Repository**

```bash
git clone https://github.com/salomov95/my_petshop_manager
cd my-petshop-manager
```

**02 - Installation**

Install/Update the project dependencies using maven global installation or local mvnw:

```bash
mvn clean install
```

**03 - Database Setup**

Before running the application itself, the database must be created, then setup the tables by running the migrations as follows:

```bash
mvn clean flyway:migrate
```

**02 - Running the Project**

```bash
mvn clean spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) in your browser to view the project.
Please, check the port.
