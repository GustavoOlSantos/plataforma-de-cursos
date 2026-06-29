describe('Teste de cobertura do ciclo de cursos', () => {
    const userTest = Cypress.env('testUser');
    const backendUrl = Cypress.env('apiUrl');

    context('Acesso pela home', () => {
        beforeEach(() => {
            cy.visit('/');
            cy.location('pathname').should('eq', '/');
        })

        it('Deve acessar a um curso em alta pela home e verificar elementos principais', () => {
            cy.get('.em-alta h2').should('contain', 'Nossos cursos mais vendidos');
            cy.get('.cursos-em-alta').should('be.visible');
            cy.get('.cursos-em-alta .card-curso').first().click();

            cy.location('pathname').should('include', '/cursos/java-completo');
            
            cy.log('Container principal do curso');

            cy.get('.curso-banner.image').should('be.visible');
            cy.get('.curso-hero-content').should('exist').and('be.visible').within(() => {
                cy.get('span > h1').should('contain.text', 'Java completo: do básico ao avançado');
                cy.get('span > p.curso-subtitulo').should('contain.text', 'Bem vindo ao Curso mais completo de java do mercado, com OO, UML, JDBC, Spring, JPA e muito mais!');
                cy.get('span > .curso-atualizacao').should('exist');
                cy.get('.curso-atualizacao').should('exist');
            });

             cy.log('Container sidebar curso');

            cy.get('.curso-sidebar').should('exist').and('be.visible').within(() => {
                cy.get('.curso-beneficio').should('exist').and('be.visible').within(() => {
                    cy.get('.beneficio-row').should('have.length.greaterThan', 0);
                })

                cy.get('.curso-actions').should('exist').and('be.visible').within(() => {
                    cy.get('button.btn.full.full-sized').should('exist').and('contain.text', 'Comprar agora');
                    cy.get('button.btn.regular.full-sized').should('exist').and('contain.text', 'Adicionar ao carrinho');
                })

                cy.get('.outras-actions div.action-row > span').should('exist').and('contain.text', 'Compartilhe este curso');
            })

            cy.log('Descrições e outros detalhamentos do curso');

            cy.get('.curso-avaliacoes').should('exist').and('be.visible')
            cy.get('.curso-info').should('exist').and('be.visible').and('have.length.greaterThan', 0)
            cy.get('.cursos-relacionados').should('exist')
        })
    })

    context('Cenário de usuário deslogado', () => {
        beforeEach(() => {
            cy.visit('/cursos/java-completo');
        })

        it('Deve acessar o curso normalmente', () => {
            cy.location('pathname').should('include', '/cursos/java-completo');
        })

        it('Deve acessar o curso e ao tentar comprar, é redirecionado para o login', () => {
            cy.get('.curso-actions > button.btn.full.full-sized').click();
            cy.location('pathname').should('eq', '/entrar');
        })
    })

     context('Cenário de usuário logado', () => {
         beforeEach(() => {
            cy.login(userTest.email, userTest.password);
            cy.visit('/cursos/java-completo');
        })

        it('Deve acessar o curso normalmente', () => {
            cy.location('pathname').should('include', '/cursos/java-completo');
        })

        it('Deve comprar e ser redirecionado para assistir o curso', () => {
            let comprado = false;

            cy.intercept('GET', `${backendUrl}compras/1`, (req) => {
                req.reply({
                    statusCode: 200,
                    body: comprado
                });
            }).as('statusCompra');

            cy.intercept('POST', `${backendUrl}compras/1`, (req) => {
                comprado = true;

                req.reply({
                    statusCode: 200,
                    body: {}
                });
            }).as('comprar');

            cy.get('.curso-actions > button.btn.full.full-sized').click();

            cy.get('div.modal').should('be.visible');
            cy.get('.modal-heading > h2').should('contain.text', 'Deseja comprar este curso?') 
            cy.get('.modal-heading > button').should('contain.text', 'X')
            cy.get('section.pagamentos').should('be.visible')

            cy.get('section.pagamentos > .btn.regular').should('contain.text', 'Desistir'); 
            cy.get('section.pagamentos >.btn.full').should('contain.text', 'Comprar').click();

            cy.get('.success').should('contain.text', 'Compra efetuada com sucesso!')

            cy.wait('@comprar');

            cy.location('pathname')
                .should('include', '/ver-curso/java-completo');
        });

        it('Deve redirecionar para assistir quando o curso já foi comprado', () => {
            cy.intercept('GET', `${backendUrl}compras/1`, {
                statusCode: 200,
                body: true
            });

            cy.visit('/ver-curso/java-completo');
            cy.location('pathname').should('eq', '/ver-curso/java-completo');
        });

        it('Deve exibir a listagem de cursos comprados', () => {
            cy.intercept('GET', `${backendUrl}compras`, {
                statusCode: 200,
                body: [
                        {
                            "alunosMatriculados": 5000,
                            "descricao": "\nBem-vindo ao curso mais completo de Java do mercado, com Programação Orientada a Objetos, UML, JDBC, Spring Boot, JPA, APIs REST, arquitetura de software e muito mais...\n\nJava continua sendo uma das linguagens mais utilizadas do mundo.\n\nGrandes empresas, bancos, sistemas corporativos, aplicações web e APIs modernas utilizam Java diariamente para construir soluções robustas, escaláveis e seguras.\n\nEste curso foi criado para levar você do nível intermediário ao avançado, dominando não apenas a linguagem Java, mas também o ecossistema profissional utilizado no mercado de trabalho.\n\nAo longo das aulas, você desenvolverá aplicações reais, entenderá como sistemas corporativos são estruturados e aprenderá as tecnologias mais exigidas por empresas e recrutadores.\n\n---\n\n####  O que você vai aprender neste curso\n\n- Dominar os fundamentos avançados da linguagem Java.\n- Trabalhar com Programação Orientada a Objetos na prática.\n- Modelar sistemas utilizando UML e boas práticas de arquitetura.\n- Criar aplicações conectadas a banco de dados com JDBC.\n- Desenvolver APIs REST profissionais com Spring Boot.\n- Trabalhar com JPA e Hibernate para persistência de dados.\n- Estruturar projetos reais seguindo padrões utilizados no mercado.\n- Implementar autenticação, validações e boas práticas de segurança.\n- Aprender princípios de Clean Code e organização de código.\n- Construir um portfólio completo com projetos profissionais.\n\n---\n\n#### Por que este curso é diferente?\n\n- Conteúdo completo cobrindo desde conceitos fundamentais até aplicações avançadas.\n- Foco total em prática e desenvolvimento de projetos reais.\n- Explicações claras e organizadas, ideais para consolidar o aprendizado.\n- Abordagem moderna utilizando Spring Boot, JPA e arquitetura profissional.\n- Curso pensado para preparar você para o mercado de trabalho real.\n\n---\n\n#### Para quem é este curso?\n\n- Desenvolvedores que já conhecem lógica ou Java básico e querem evoluir.\n- Programadores que desejam entrar no mercado Java profissional.\n- Estudantes que querem aprender backend moderno com Spring Boot.\n- Pessoas interessadas em desenvolver APIs, sistemas web e aplicações corporativas.\n- Profissionais que desejam fortalecer conhecimentos em arquitetura e banco de dados.\n\n---\n\n#### Benefícios do Curso\n\n- Desenvolvimento Backend Profissional: Aprenda a criar aplicações robustas e escaláveis.\n- Integração com Banco de Dados: Trabalhe com persistência de dados utilizando JDBC e JPA.\n- Criação de APIs REST: Desenvolva serviços modernos utilizados em aplicações reais.\n- Arquitetura e Boas Práticas: Aprenda padrões utilizados por empresas e times profissionais.\n- Experiência Prática: Desenvolva projetos completos para seu portfólio.\n\n---\n\n#### Resultado esperado\n\nAo final do curso, você será capaz de desenvolver aplicações Java completas utilizando tecnologias modernas do ecossistema Spring, criando APIs profissionais, conectando bancos de dados, organizando projetos de forma escalável e aplicando boas práticas utilizadas no mercado.\n\nIndependentemente do seu objetivo — conseguir um emprego, evoluir na carreira ou construir projetos próprios — este curso vai preparar você para atuar profissionalmente com Java.\n",
                            "duracao": "16.66",
                            "id": 1,
                            "idioma": "Português",
                            "imagemUrl": "java-image.jpg",
                            "instrutor": "Gustavo Santos",
                            "nivel": "Todos os níveis",
                            "nome": "Java completo: do básico ao avançado",
                            "numeroAulas": 41,
                            "preco": 399.99,
                            "requisitos": [
                                "É necessário ter espaço o suficiente de armazenamento no sistema para a instalação do Java e outros softwares auxiliares, que serão indicados e com instalação guiada ao decorrer do curso"
                            ],
                            "slug": "java-completo",
                            "subcategorias": [
                                {
                                    "id": 1,
                                    "nome": "Java",
                                    "slug": "java"
                                }
                            ],
                            "subtitulo": "Bem vindo ao Curso mais completo de java do mercado, com OO, UML, JDBC, Spring, JPA e muito mais!",
                            "ultimaAtualizacao": "2026-04-24T00:00:00.000Z"
                        },
                        {
                            "alunosMatriculados": 4000,
                            "descricao": "Aprenda fluxo profissional com Git e GitHub.",
                            "duracao": "60",
                            "id": 9,
                            "idioma": "Português",
                            "imagemUrl": "git-image.png",
                            "instrutor": "Bruno Rocha",
                            "nivel": "Básico",
                            "nome": "Git e GitHub completo",
                            "numeroAulas": 25,
                            "preco": 79.99,
                            "requisitos": [],
                            "slug": "git-github",
                            "subcategorias": [],
                            "subtitulo": "Controle de versão do básico ao avançado",
                            "ultimaAtualizacao": "2026-04-05T00:00:00.000Z"
                        }
                      ]
            });

            cy.visit('/meus-cursos')
            cy.location('pathname').should('eq', '/meus-cursos');

            cy.get('.cursos-adquiridos').should('have.length.greaterThan', 0);
            cy.get('.cursos-adquiridos > :nth-child(1)').within(() =>{
                cy.get('h2').should('be.visible').and('contain', 'Java completo: do básico ao avançado')
                cy.get('.curso-infos').should('be.visible').and('contain', 'Gustavo Santos | 5000 alunos | Todos os níveis')
                cy.get('.curso-avaliacao')
                cy.get('.curso-progress')
            })
        })

        it('Deve exibir a a mensagem de que não há cursos comprados', () => {
            cy.visit('/meus-cursos')
            cy.location('pathname').should('eq', '/meus-cursos');

            cy.get('p').should('contain', 'Você ainda não adquiriu nenhum curso.')
        })

        it.skip('Deve acessar o curso e adicionar ao carrinho com sucesso', () => {
            cy.get('.curso-actions > button.btn.regular.full-sized').click();
        })
    })

    context('Cenários de falha', () => {
        beforeEach(() => {
            cy.login(userTest.email, userTest.password);
            cy.visit('/cursos/java-completo');
        })

        it('Usuário já comprou este curso', () => {
             cy.intercept('POST', `${backendUrl}compras/1`, (req) => {
                req.reply({
                    statusCode: 400,
                    body: 'Esse usuário já comprou este curso'
                });
            }).as('comprar');

            cy.get('.curso-actions > button.btn.full.full-sized').click();
            cy.get('section.pagamentos >.btn.full').should('contain.text', 'Comprar').click();
            cy.get('.error').should('contain.text', 'Falha ao comprar curso!')

            cy.wait('@comprar');

        })

        it('Usuário não autenticado', () => {
             cy.intercept('POST', `${backendUrl}compras/1`, (req) => {
                req.reply({
                    statusCode: 401,
                    body: 'Usuário não autenticado'
                });
            }).as('comprar');

            cy.get('.curso-actions > button.btn.full.full-sized').click();
            cy.get('section.pagamentos >.btn.full').should('contain.text', 'Comprar').click()
            cy.wait('@comprar');
             cy.location('pathname').should('eq', '/entrar');
        })

        it('Usuário ou curso não encontrado', () => {
             cy.intercept('POST', `${backendUrl}compras/1`, (req) => {
                req.reply({
                    statusCode: 404,
                    body: 'Usuário ou curso não encontrado'
                });
            }).as('comprar');

            cy.get('.curso-actions > button.btn.full.full-sized').click();
            cy.get('section.pagamentos >.btn.full').should('contain.text', 'Comprar').click();
            cy.get('.error').should('contain.text', 'Falha ao comprar curso!')

            cy.wait('@comprar');
        })
    })
})