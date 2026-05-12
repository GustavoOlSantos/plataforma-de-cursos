import React, {useState} from 'react';
import { Routes, Route } from 'react-router-dom';
import AppRoutes from './app/routes';

import UserProvider from './app/providers/user-context';
import Login from './features/auth/login';
import Home from './features/home';
import Perfil from './features/perfil';
import NotFound from './features/not-found';

import NavBar from './components/navbar';
import Footer from './components/footerSection'

function App(){
  return(
    <UserProvider>
      <div className="App">
        <NavBar/>
        <AppRoutes />
        <Footer />
      </div>
    </UserProvider>
  )
}

export default App;
