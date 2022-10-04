describe('Testing Home', function() {

  it('Home', function() {
	  cy.request('/').then((response) => {
		  expect(response.status).to.eq(200);
	});
  });

});
