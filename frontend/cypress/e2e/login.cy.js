const backendUrl = Cypress.env('apiUrl');

describe('Teste da página de login', () => {

  const userTest = Cypress.env('testUser');

  beforeEach(() => {
    cy.visit('/entrar');
    cy.location('pathname').should('eq', '/entrar');

    cy.intercept('POST', `${backendUrl}auth/login`).as('loginRequest'); 
  });

  context('Cenário de login bem-sucedido', () => {

    it('Deve carregar a página de login e exibir os elementos principais', () => {
      cy.get('h1').should('contain', 'Bem-vindo de volta');
      
      cy.get('input[name="email"]').should('be.visible');
      cy.get('input[name="password"]').should('be.visible');
      cy.get('button').contains('Entrar').should('be.visible');
    });

    it('Deve preencher o formulário, realizar login e redirecionar para a home', () => {
      cy.get('input[name="email"]').type(userTest.email);
      cy.get('input[name="password"]').type(userTest.password, { log: false });
      cy.get('button').contains('Entrar').click();

      cy.wait('@loginRequest');

      cy.location('pathname').should('eq', '/');

      cy.get('.welcome-avatar').should('be.visible');
      cy.get('.welcome-text h2').should('contain', `Bem-vindo(a) de volta, ${userTest.name}!`);
      
      cy.window().its('localStorage').invoke('getItem', 'token').should('exist');
    });

    it('Deve preencher o formulário, realizar login e fazer logout', () => {
      cy.get('input[name="email"]').type(userTest.email);
      cy.get('input[name="password"]').type(userTest.password, { log: false });
      cy.get('button').contains('Entrar').click();

      cy.wait('@loginRequest');

      cy.location('pathname').should('eq', '/');
      cy.window().its('localStorage').invoke('getItem', 'token').should('exist');

      cy.get('.user-icon').click();
      cy.get('li').contains('Sair').click();

      cy.location('pathname').should('eq', '/');
      cy.get('.welcome-section').should('not.exist');
      cy.window().its('localStorage').invoke('getItem', 'token').should('not.exist');
    });
  });

  context('Cenário de login com falha', () => {
    it('Deve exibir erro ao tentar entrar com credenciais inválidas', () => {
      cy.get('input[name="email"]').type('usuario@invalido.com');
      cy.get('input[name="password"]').type('senhaIncorreta123', { log: false });
      cy.get('button').contains('Entrar').click();

      cy.location('pathname').should('eq', '/entrar');
      cy.get('.error').should('be.visible').and('contain', 'Credenciais inválidas');
    });

    it('Deve exibir erro ao tentar entrar sem preencher os campos', () => {
      cy.get('button').contains('Entrar').click();

      cy.location('pathname').should('eq', '/entrar');
      cy.get('.error').should('be.visible').and('contain', 'Por favor, preencha todos os campos');
    });
  });
});