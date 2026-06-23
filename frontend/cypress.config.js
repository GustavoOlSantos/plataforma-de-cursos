require('dotenv').config();
const { defineConfig } = require("cypress");
const baseUrl = process.env.REACT_APP_URL;

module.exports = defineConfig({
  allowCypressEnv: true,
  e2e: {
    baseUrl: baseUrl,
    env: {
      apiUrl: process.env.REACT_APP_API_URL || 'http://localhost:8080/api/'
    },
    setupNodeEvents(on, config) {
      console.log('Base URL carregada:', baseUrl);
      console.log('API URL carregada:', config.env.apiUrl);
      return config;
    },
  },
});
