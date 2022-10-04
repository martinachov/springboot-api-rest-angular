// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This is will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

import 'cypress-wait-until';

/*
    Login con DAUT
*/
Cypress.Commands.add('login', function (username, password) {

    cy.visit('/');
    cy.location('pathname').should('eq', '/daut/login');

    cy.get('input[id="username"]').fill(username);
    cy.get('input[id="password"]').fill(password);

    cy.get('button[id=login-button]').click();
});

Cypress.Commands.add('logout', function () {

	cy.declareXHRsAliasForUri(['/daut/logout?provider=daut']);

	//FIXME pedir a Menú que cree un data-cy para acceder mejor al elemento clickeable
	cy.get('#menu_menu > div:nth-child(1) > div:nth-child(2) > a').click();

	//FIXME pedir a Menú que cree un data-cy para acceder mejor al elemento clickeable
	cy.get('.menu-logout').should('exist').get('#menu_usuario > div > div.cerrar-fotter > div > a').click();;

	cy.waitXHRsFinishSuccessful(['/daut/logout?provider=daut']);

	//Luego del logout debiera ir a la página de login de Daut, pero hay un bug asociado a los redirect del logout en Cypress: https://github.com/cypress-io/cypress/issues/7614
	//Por eso se vuelve a visitar el /
	cy.visit('/');

});

Cypress.Commands.add('saveDefaultsCookie', function (cookieName) {

    Cypress.Cookies.defaults({
        preserve: cookieName
    });
});

Cypress.Commands.add('clearDefaultsCookie', function () {

    Cypress.Cookies.defaults({
        preserve: ''
    });
});