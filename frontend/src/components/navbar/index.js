import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import SearchBar from "../searchBar";
import ButtonIcon from "../buttonIcon";
import ButtonText from "../buttonText";
import { getToken } from "../../services/jwt";

function NavBar(){

    const navigate = useNavigate();
    const [token, setToken] = useState(null);

    useEffect(() => {
        const jwt = getToken();
        setToken(jwt);
    }, []);

    return(
        <header>
            <img src="/Title.png" alt="Logo" className="logo" onClick={() => navigate("/")}/>
            
            <ButtonText className="btn textOnly" text="Descobrir" onClick={() => navigate("/cursos")}/>

            <SearchBar />

            {token == null ? (
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
                    </div>

                    <ButtonIcon icon="fa-solid fa-circle-user" className="user-icon" onClick={()=> navigate("/perfil")} alt="Perfil do usuário"/>
                </div>
            )}
            
        </header>
    )
}
export default NavBar;