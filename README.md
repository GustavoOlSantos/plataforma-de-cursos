# Plataforma de Cursos - Skill Up

<div align="center">

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F)
![React](https://img.shields.io/badge/React-18.3.1-61DAFB)
![Personal Project](https://img.shields.io/badge/type-personal_project-purple)

</div>

---

## Sobre o projeto

A Plataforma de Cursos - Skill Up é uma aplicação full stack desenvolvida individualmente com foco em arquitetura escalável, autenticação segura e integração entre frontend e backend.

O sistema simula uma plataforma EAD, permitindo gerenciamento e consumo de cursos através de uma aplicação React integrada a uma API REST desenvolvida com Spring Boot.

Este projeto está em desenvolvimento contínuo e vem sendo utilizado como ambiente de estudo, experimentação e evolução técnica em desenvolvimento full stack.

<div align="center">
  <img src="https://skillicons.dev/icons?i=java,spring,react,mysql,mongodb,nodejs" />
</div>

---

##  Deploy da aplicação

A plataforma está totalmente deployada e integrada com serviços cloud reais.

### Acessar aplicação
- [🌐 Frontend (Vercel)](https://project-pritz.vercel.app/)
- [⚙️ Backend API (Render)](https://plataforma-de-cursos-eeow.onrender.com)  (Render possui cold start, o primeiro request pode ter um delay)
- [📚 Documentação da API (Swagger)](https://plataforma-de-cursos-eeow.onrender.com/swagger-ui/index.html)


---

## Infraestrutura utilizada

| Serviço | Plataforma |
|---|---|
| Frontend | Vercel |
| Backend | Render |
| Banco Relacional | Aiven MySQL |
| Banco NoSQL | MongoDB Atlas |
| Armazenamento de imagens | Cloudinary |

---

## Funcionalidades

- Cadastro e autenticação JWT
- Compra de cursos
- Estrutura para avaliações de cursos
- Player de vídeo integrado ao YouTube
- Salvamento automático de progresso
- Continuação de aulas por timestamp
- Persistência de progresso do usuário
- Gerenciamento de imagens via Cloudinary
- Integração MySQL + MongoDB

---

# Stack utilizada

## Frontend

| Tecnologia | Versão |
|---|---|
| React | 18.3.1 |
| React Router DOM | 6.30.3 |
| Axios | 1.14.0 |
| JWT Decode | 4.0.0 |
| React Scripts | 5.0.1 |

---

## Backend

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Lombok | 1.18.46 |
| Spring Boot | 4.0.5 |
| Spring Security | 4.0.5 |
| Spring Data JPA | 4.0.5 |
| Spring Data MongoDB | 4.0.5 |
| JWT (jjwt) | 0.11.5 |
| Maven | Wrapper |

---

## Banco de dados

| Banco | Utilização |
|---|---|
| MySQL | Dados relacionados a usuários, cursos e compras |
| MongoDB | Dados relacionados a avaliações e comentários |

---

## Arquitetura do projeto

```bash
plataforma-de-cursos/
│
├── frontend/     # Aplicação React
├── backend/      # API REST Spring Boot
│
└── package.json  # Execução simultânea dos serviços
````

---

## Backend

O backend foi estruturado utilizando arquitetura em camadas:

```bash
config/
controller/
domain/
DTO/
exception/
repository/
security/
service/
utils/
```

### Principais responsabilidades
* Config → configuração Web e disponibilização de imagens
* Controllers → exposição da API REST
* Domain → centraliza as entidades e documentos do projeto
* DTOs → transferência de dados
* Exception Handler → tratamento global de erros
* Repositories → acesso aos dados
* Security → autenticação e autorização JWT
* Services → regras de negócio
* Utils  → utilitários de suporte

---

## Frontend

O frontend foi orientado à features e organizado buscando componentização e escalabilidade:

```bash
app/
assets/
components/
features/
services/
styles/
```

---

## Segurança

A autenticação da aplicação é baseada em **JWT (JSON Web Token)** utilizando Spring Security.

### Recursos implementados

* autenticação stateless
* validação de token
* proteção de rotas privadas
* autorização de requisições
* integração segura entre frontend e backend

---

## Preview
<p align="center">
  <img src="./screenshots/home-page.png" width="45%"/>
  <img src="./screenshots/registro-page.png" width="45%"/>
  <img src="./screenshots/detalhamento-curso.png" width="45%"/>
  <img src="./screenshots/home-gif.gif" width="45%"/>
</p>

## Como executar o projeto localmente

## Pré-requisitos

Antes de iniciar, é necessário possuir instalado:

* Node.js
* Java 21+
* MySQL
* MongoDB
* Maven (opcional, o projeto utiliza Maven Wrapper)

---

### Clone o repositório

```bash
git clone https://github.com/sGustavoOlSantos/plataforma-de-cursos.git
```

---

### Executando o backend

```bash
  npm run dev:backend 
```

---

### Executando o frontend

```bash
cd frontend
npm install
npm start
```

ou, após instalar os pacotes npm:
```bash
  cd ../
  npm run dev:frontend 
```

---

### Executando frontend e backend juntos

Na raiz do projeto:

```bash
npm install
npm run dev
```

---

## Objetivos do projeto

- Aplicar conceitos modernos de desenvolvimento full stack
- Estruturar uma aplicação escalável utilizando React e Spring Boot
- Implementar autenticação e autorização com JWT
- Desenvolver uma API REST segura e organizada em camadas
- Explorar persistência híbrida com MySQL e MongoDB
- Evoluir arquitetura, componentização e integração frontend/backend
  
---

## Roadmap

* As próximas melhorias podem ser vistas no Quadro de Tarefas na seção `Projects` do repositório

---

## Status do projeto

🚧 Projeto em desenvolvimento ativo.

Novas funcionalidades, melhorias estruturais e otimizações estão sendo adicionadas continuamente.

---

## Autor

Desenvolvido individualmente por mim como projeto pessoal para estudo, prática e evolução contínua em desenvolvimento full stack.
