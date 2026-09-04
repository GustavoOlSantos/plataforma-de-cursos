import AppRoutes from './app/routes';

import UserProvider from './app/providers/user-context';
import {AuthListener} from  './features/auth/services/authListener';

import NavBar from './components/navbar';
import Footer from './components/footerSection'

function App(){
  return(
    <UserProvider>
      <AuthListener />
      <div className="App">
        <NavBar/>
        <AppRoutes />
        <Footer />
      </div>
    </UserProvider>
  )
}

export default App;
