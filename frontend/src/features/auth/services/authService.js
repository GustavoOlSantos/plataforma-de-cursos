import api from "../../../services/api";

const AUTH_KEY = 'isAuthenticated';
const AUTH_USER = 'authenticatedUser';
const AUTH_TOKEN = 'token';

function login(email, password){
  return api.post('/auth/login', 
    { email, 
      password 
    })
}

function logout(){
  localStorage.removeItem(AUTH_USER);
  localStorage.removeItem(AUTH_KEY);
  localStorage.removeItem(AUTH_TOKEN);
  window.dispatchEvent(new Event("manual-logout"));
}
function autoLogout(){
    localStorage.removeItem(AUTH_USER);
    localStorage.removeItem(AUTH_KEY);
    localStorage.removeItem(AUTH_TOKEN);
    window.dispatchEvent(new Event("session-expired"));
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
  autoLogout,
  register,
  getAuthenticatedUser,
  isAuthenticated
};
