describe('Teste da Home Page', () =>{

    const userTest = Cypress.env('testUser');

    beforeEach(() => {
        cy.visit('/');
        cy.location('pathname').should('eq', '/');
    })

    context('Cenário geral', () => {
        it('Deve exibir a seção de cursos em alta', () => {
            cy.get('.em-alta h2').should('contain', 'Nossos cursos mais vendidos');
            cy.get('.cursos-em-alta').should('be.visible');
            cy.get('.cursos-em-alta .card-curso').should('have.length.greaterThan', 0);
        })

        it('Deve exibir o Banner principal com os cards de destaque', () => {
            cy.get('.colored-banner').should('be.visible');
            cy.get('.carousel.banner-cards .card').should('have.length.greaterThan', 0);
        })

        it('Deve exibir a seção de evolução de carreira', () => {
            cy.get('.evolucao').should('be.visible');
            cy.get('.evolucao figure img').should('be.visible');
            cy.get('.evolucao article h1').should('be.visible');
            cy.get('.evolucao article p').should('be.visible');
        })
        
        it('Deve exibir a seção de vantagens da plataforma', () => {
            cy.get('.features-section').should('be.visible');
            cy.get('.features-section .feature-card').should('have.length.greaterThan', 2);
            
        })
    })

    context('Cenário de usuário deslogado', () => {
        it('Deve exibir a seção de boas-vindas apenas para usuários logados', () => {
            cy.get('.welcome-section').should('not.exist');
        }) 
    })

    context('Cenário de usuário logado', () => {
        beforeEach(() => {
            cy.login(userTest.email, userTest.password);
        })

       it('Deve exibir a seção de boas-vindas apenas para usuários logados', () => {
            cy.get('.welcome-section').should('exist');
            cy.get('.welcome-avatar').should('be.visible');
            cy.get('.welcome-text h2').should('contain', `Bem-vindo(a) de volta, ${userTest.name}!`);
        })
    })
})