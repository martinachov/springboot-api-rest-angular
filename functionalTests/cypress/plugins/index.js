// ***********************************************************
// This example plugins/index.js can be used to load plugins
//
// You can change the location of this file or turn off loading
// the plugins file with the 'pluginsFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/plugins-guide
// ***********************************************************

// This function is called when a project is opened or re-opened (e.g. due to
// the project's config changing)

// promisified fs module
const fs = require('fs-extra');
const path = require('path');

function getConfigurationByFile (filePath) {
  
  return fs.readJson(filePath);
}

// plugins file
module.exports = (on, config) => {
  // Busca la config en el raiz de la carpeta	
  var filePath = path.join(__dirname, '../../', 'cypress-config.json');
  
  // Si se seteo 'configFile' se le da prioridad
  if (config.env.configFile){
    filePath = path.join(__dirname, '../config', config.env.configFile);
  }
  return getConfigurationByFile(filePath);
};

