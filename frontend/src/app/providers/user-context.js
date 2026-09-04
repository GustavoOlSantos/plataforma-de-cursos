import { useState, createContext, useEffect } from "react";
import api from "../../services/api";
import { getToken } from "../../services/jwt";

export const UserContext = createContext({});

function UserProvider({ children }) {
    const [user, setUser] = useState(undefined);

    function loadUser(){
        const jwt = getToken();

            if(!jwt){
                setUser(null);
                return;
            }

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