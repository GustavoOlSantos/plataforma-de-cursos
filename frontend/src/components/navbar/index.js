import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import SearchBar from "../searchBar";
import ButtonIcon from "../buttonIcon";
import ButtonText from "../buttonText";
import { getToken } from "../../services/jwt";
import api from "../../services/api";
import { jwtDecode } from "jwt-decode";

function NavBar(){

    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(null);

    useEffect(() => {
        const jwt = getToken();
        setToken(jwt);
    }, []);

    useEffect(() => {
        function loadUser(){
            const jwt = getToken();

                if(!jwt){
                    setToken(null);
                    setUser(null);
                    return;
                }
                setToken(jwt);
            }

            loadUser();

            window.addEventListener("user-login", loadUser);

            return () => {
                window.removeEventListener("user-login", loadUser);
            };

    }, []);

    useEffect(() => {
        function removeUser(){
            setToken(null);
            setUser(null);
        }

            removeUser();

            window.addEventListener("user-logout", removeUser);

            return () => {
                window.removeEventListener("user-logout", removeUser);
            };

    }, []);

    useEffect(() => {
        if(token == null) {return}
        
        api.get(`/auth/id/${token.sub}`)
        .then(res => {
            setUser(res.data);
        })
    }, [token]);

    return(
        <header>
            <img src="/Title.png" alt="Logo" className="logo" onClick={() => navigate("/")}/>
            
            <ButtonText className="btn textOnly" text="Descobrir" onClick={() => navigate("/cursos")}/>

            <SearchBar />

            {token == null || user == null ? (
                <div className="header-container">
                    <ButtonText className="btn textOnly" text="Business" onClick={() => navigate("/empresas")}/>
                    <ButtonText className="btn textOnly" text="Ensine na SkillUp" onClick={() => navigate("/ensine-na-skillUp")}/>
                    <ButtonText className="btn regular" text="Fazer login" onClick={() => navigate("/entrar")}/>
                    <ButtonText className="btn full" text="Cadastre-se" onClick={() => navigate("/cadastro")}/>
                </div>
            ) : (
                <div className="header-container">  
                    <ButtonText className="btn textOnly" text="Meus cursos" onClick={() => navigate("/meus-cursos")}/>
                    <ButtonText className="btn textOnly" text="Ensine na SkillUp" onClick={() => navigate("/ensine-na-skillUp")}/>
                    
                    <div className="header-icons"> 
                        <ButtonIcon icon="fa-solid fa-cart-shopping" onClick={()=> navigate("/carrinho")} alt="Carrinho de compras"/>
                        <ButtonIcon icon="fa-regular fa-heart" onClick={()=> navigate("/desejos")} alt="Cursos desejados"/>
                    
                        <img className="user-icon" src={
                            user.userImagePath
                            ? `http://localhost:8080/uploads/${user.userImagePath}`
                            : "http://localhost:8080/uploads/default-icon.jpg"
                            }
                            onError={(e) => {
                                e.currentTarget.onerror = null; // evita loop
                                e.currentTarget.src = "http://localhost:8080/uploads/default-icon.jpg";
                            }}
                            onClick={() => navigate("/perfil")}
                        />
                    </div>    
                </div>
            )}
            
        </header>
    )
}
export default NavBar;
