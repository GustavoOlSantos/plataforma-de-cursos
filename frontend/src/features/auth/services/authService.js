const AUTH_KEY = 'isAuthenticated';
const AUTH_USER = 'authenticatedUser';
import api from "../../../services/api";

function login(user, password){
    // Simulação de autenticação
    if(user === 'admin' && password === '1234'){
        localStorage.setItem(AUTH_USER, user);
        localStorage.setItem(AUTH_KEY, 'true');
        return true;
    }
    return false;
}

function logout(){
    localStorage.removeItem(AUTH_USER);
    localStorage.removeItem(AUTH_KEY);
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
  getAuthenticatedUser,
  isAuthenticated
};
