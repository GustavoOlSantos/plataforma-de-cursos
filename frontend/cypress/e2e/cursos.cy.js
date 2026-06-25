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