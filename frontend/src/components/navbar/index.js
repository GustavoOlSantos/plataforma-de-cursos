import React from "react";
import { useNavigate } from 'react-router-dom';
import SearchBar from "../searchBar";
import ButtonIcon from "../buttonIcon";
import ButtonText from "../buttonText";

function NavBar(){

    const navigate = useNavigate();

    return(
        <header>
            <img src="/Title.png" alt="Logo" className="logo" onClick={() => navigate("/")}/>

            <ButtonText className="btn textOnly" text="Ver cursos" onClick={() => navigate("/cursos")}/>

            <SearchBar />

            <ButtonIcon icon="fa-solid fa-cart-shopping" onClick={()=> navigate("/carrinho")} alt="Carrinho de compras"/>

            <ButtonText className="btn regular" text="Fazer login" onClick={() => navigate("/entrar")}/>
            <ButtonText className="btn full" text="Cadastre-se" onClick={() => navigate("/cadastro")}/>
        </header>
    )
}
export default NavBar;