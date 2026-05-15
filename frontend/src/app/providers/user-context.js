import { useState, createContext, useEffect } from "react";
import api from "../../services/api";
import { getToken } from "../../services/jwt";
import { jwtDecode } from "jwt-decode";

export const UserContext = createContext({});

function UserProvider({ children }) {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(null);

    function loadUser(){
        const jwt = getToken();

            if(!jwt){
                setToken(null);
                setUser(null);
                return;
            }
            setToken(jwt);

            api.get(`/auth/id/${jwt.sub}`)
            .then(res => {
                setUser(res.data);
            })
            .catch(err => {
                console.error("Erro ao carregar usuário", err);
            })  
    }

    useEffect(() => {
        loadUser();
        window.addEventListener("user-login", loadUser);
        return () => {
            window.removeEventListener("user-login", loadUser);
        };
    }, []);

    useEffect(() => {
        function removeUser() {
            setUser(null);
        }
        window.addEventListener("manual-logout", removeUser);
        window.addEventListener("session-expired", removeUser);
        return () => {
            window.removeEventListener("manual-logout", removeUser);
            window.removeEventListener("session-expired",removeUser);
        };
    }, []);

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
}

export default UserProvider;