const AUTH_KEY = 'isAuthenticated';
const AUTH_USER = 'authenticatedUser';
import axios from "axios";
import api from "../../../services/api";

function login(email, password){
    return api.post('/auth/login', 
      { email, 
        password 
      })
}

function logout(){
    localStorage.removeItem(AUTH_USER);
    localStorage.removeItem(AUTH_KEY);
    window.dispatchEvent(new Event("user-logout"));
}

function register(nome, email, password){
    return api.post('/auth/cadastro', 
      { nome, 
        email, 
        password 
      });
}

function getAuthenticatedUser() {
  return localStorage.getItem(AUTH_USER);
}

function isAuthenticated() {
  return localStorage.getItem(AUTH_KEY) === 'true';
}

export const authService = {
  login,
  logout,
  register,
  getAuthenticatedUser,
  isAuthenticated
};
