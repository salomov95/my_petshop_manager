<div align="center">
  <br />

  <a href="#" target="_blank">
    <img src="https://github.com/user-attachments/assets/b8ab414b-7bed-468f-a3dd-e92212fb3158" alt="Project Banner"/>
  </a>

  <br />
  <br />
  <br />

  <div>
    <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff&style=for-the-badge" alt="Java" />
    <img src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white" alt="Thymeleaf" />
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
    <img src="https://img.shields.io/badge/Flyway-CC0200?logo=flyway&logoColor=fff&style=for-the-badge" alt="Flyway" />
    <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff&style=for-the-badge" alt="Docker" />
  </div>
<br/><br/>
 
  <h1 align="center">My PetShop Manager</h1>

   <div align="center">
     An app designed to manage appointments of a PetShop.
   </div>
</div>

## 📋 <a name="table">Sumary</a>

1. 🚀 [Introduction](#introduction)
2. ⚙️ [Tech Stack](#tech-stack)
3. 🔋 [Features](#features)
4. 💻 [Quick Start](#quick-start)
5. 💾 [Environment Variables](#envs)
6. 📅 [Releases](#versions)
7. 🤝 [Contributing](#contributing)
8. 👥 [Authors](#authors)
9. ⚙️ [Wiki](https://github.com/salomov95/family_planner_api/wiki)




## <a name="introduction">🚀 Introduction</a>

&nbsp;Developed to bring joy of management to your PetShop, though to be the ultimate management system.


## <a name="features">🔋 Features</a>

- Easy appointment track with date filters.
- Easy deployment, with just a few touchs.
- Friendly UI.


## <a name="tech-stack">⚙️ Tech Stack</a>

- [Java](https://www.java.com/en/)
- [Maven](https://maven.apache.org)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JUnit5](https://junit.org/junit5/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Spring JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)


## <a name="quick-start">💻 Quick Start</a>

Follow these steps to set up the project locally on your machine.

**00 - Prerequisites**

To use this project you must have previously installed the following packages:

- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

> Not using Docker? See [HOW_TO_RUN.md](HOW_TO_RUN.md) for further instructions.

**01 - Cloning the Repository**

```bash
git clone https://github.com/salomov95/my_petshop_manager
cd my-petshop-manager
```

**02 - Running the Project**

```bash
docker compose up -d
```

Open [http://localhost:8080](http://localhost:8080) in your browser to view the project.
Please, check the port.

## <a name="envs">💾 Environment Variables</a>

> IF RUNNING ON DOCKER, NO MANUAL SETUP IS NEEDED, ONE CAN SKIP THIS SECTION!

<details>
<summary><code>aplication.yml</code></summary>

```yml
# Active Profile - default, dev or production
spring:
  profiles:
    active: dev

```

</details>

<details>
<summary><code>application-PROFILE.yml</code></summary>

```yml
# Session Max Timeout
server:
  servlet:
    session:
      timeout: 60000


spring:
  # Session Password Encoding
  password-encoder-seed: MY-PET-SUPER-SECRET-SEED

  # Database Connection
  datasource:
    username: # Database Previously Configured Username
    password: # Database Previously Configured Password
    url: # Database Previously Configured Connection URL
```
</details>

## <a name="versions">📅 Release History</a>

* 0.0.1  - The initial release.<br />
  FEAT: Authentication and Appointment Management.
* 0.1.0  - First containerized release.<br />
  FEAT: Includes Dockerfile based builds, automated tests and first publishing.
* 0.1.25 - Latest release
  FEAT: Inlcudes deployment, CI actions, all chore features integrated.

## <a name="contributing">🤝 Contributing</a>

Contributions, issues, and feature requests are welcome!

1. Fork it (<[https://github.com/salomov95/my_pet](https://github.com/salomov95/my_petshop_manager>))
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request


## <a name="authors">👥 Authors</a>

<table style="border-collapse: collapse; table-layout: auto text-align: left;">

  <tbody>
    <tr>
      <td style="padding: 10px; border: 1px solid #ddd;">
        <img src="https://avatars.githubusercontent.com/u/170432574?v=4" width="60" style="border-radius: 50%; display: block; margin: 0 auto;">
      </td>
      <td style="padding: 10px; border: 1px solid #ddd;">Salomao Souza</td>
      <td style="padding: 10px; border: 1px solid #ddd;">
        <a href="https://br.linkedin.com/in/salomao-souza-643995306" target="_blank">LinkedIn</a> |
        <a href="https://github.com/salomov95" target="_blank">GitHub</a>
      </td>
    </tr>
  </tbody>
</table>
