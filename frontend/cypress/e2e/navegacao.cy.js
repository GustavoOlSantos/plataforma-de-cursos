describe('Teste de navegação entre páginas pelo menu/navbar', () => {
    
    const userTest = Cypress.env('testUser');

    beforeEach(() => {
        cy.visit('/');
        cy.location('pathname').should('eq', '/');
    })


    context('Usuário deslogado', () => {

        it('Deve verificar a disponibilidade de todos os elementos', () => {
            cy.get('header img.logo').should('be.visible')
            cy.contains('Descobrir').should('be.visible')
            cy.get('input.main-searcher').should('be.visible')
            cy.contains('Business').should('be.visible')
            cy.contains('Ensine na SkillUp').should('be.visible')
            cy.contains('Fazer login').should('be.visible')
            cy.contains('Cadastre-se').should('be.visible')
        })

        it('Deve navegar para a página Home', () => {
            cy.get('header img.logo').click()
            cy.location('pathname').should('eq', '/')
        })

        it.skip('Deve navegar para a página de descoberta de cursos', () => {
            cy.contains('Descobrir').click()
            cy.location('pathname').should('eq', '/')
        })

         it.skip('Deve navegar para a página de Busines', () => {
            cy.contains('Business').click()
            cy.location('pathname').should('eq', '/')
        })

         it.skip('Deve navegar para a página de cadastro para mentor', () => {
            cy.contains('Ensine na SkillUp').click()
            cy.location('pathname').should('eq', '/')
        })

        it('Deve navegar para a página de Login', () => {
            cy.contains('Fazer login').click()
            cy.location('pathname').should('eq', '/entrar')
        })

        it('Deve navegar para a página de Cadastro', () => {
            cy.contains('Cadastre-se').click()
            cy.location('pathname').should('eq', '/cadastro')
        })
    })
    
    context('Usuário logado', () => {

        beforeEach(() => {
            cy.login(userTest.email, userTest.password)
        })

        it('Deve verificar a disponibilidade de todos os elementos', () => {
            cy.get('header img.logo').should('be.visible')
            cy.contains('Descobrir').should('be.visible')
            cy.get('input.main-searcher').should('be.visible')
            cy.contains('Meus cursos').should('be.visible')
            cy.contains('Ensine na SkillUp').should('be.visible')
            cy.get('button[alt="Carrinho de compras"]').should('be.visible')
            cy.get('button[alt="Cursos desejados"]').should('be.visible')
        })

       it('Deve navegar para a página Home', () => {
            cy.get('header img.logo').click()
            cy.location('pathname').should('eq', '/')
        })

        it.skip('Deve navegar para a página de descoberta de cursos', () => {
            cy.contains('Descobrir').click()
            cy.location('pathname').should('eq', '/')
        })

        it.skip('Deve navegar para a página de cadastro para mentor', () => {
            cy.contains('Ensine na SkillUp').click()
            cy.location('pathname').should('eq', '/')
        })
        
        it('Deve navegar para a página de cursos comprados', () => {
            cy.contains('Meus cursos').click()
            cy.location('pathname').should('eq', '/meus-cursos')
        })

         it.skip('Deve navegar para a página de carrinho de compras', () => {
            cy.get('button[alt="Carrinho de compras"]').click()
            cy.location('pathname').should('eq', '/')
        })

        it.skip('Deve navegar para a página de cursos desejados', () => {
            cy.get('button[alt="Cursos desejados"]').click()
            cy.location('pathname').should('eq', '/')
        })
    })
})