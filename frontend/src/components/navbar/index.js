import React, { useContext } from "react";
import { useNavigate } from 'react-router-dom';

import SearchBar from "../searchBar";
import ButtonIcon from "../buttonIcon";
import ButtonText from "../buttonText";

import { UserContext } from "../../app/providers/user-context";
import {getCloudImageUrl} from "../../services/cloud_images";
import defaultIcon from "../../assets/default-icon.jpg";

function NavBar() {

    const navigate = useNavigate();

    const { user } = useContext(UserContext);

    return (
        <header>
            <img
                src="/Title.png"
                alt="Logo"
                className="logo"
                onClick={() => navigate("/")}
            />

            <ButtonText
                className="btn textOnly"
                text="Descobrir"
                onClick={() => navigate("/cursos")}
            />

            <SearchBar />

            {!user ? (
                <div className="header-container">
                    <ButtonText
                        className="btn textOnly"
                        text="Business"
                        onClick={() => navigate("/empresas")}
                    />

                    <ButtonText
                        className="btn textOnly"
                        text="Ensine na SkillUp"
                        onClick={() => navigate("/ensine-na-skillUp")}
                    />

                    <ButtonText
                        className="btn regular"
                        text="Fazer login"
                        onClick={() => navigate("/entrar")}
                    />

                    <ButtonText
                        className="btn full"
                        text="Cadastre-se"
                        onClick={() => navigate("/cadastro")}
                    />
                </div>
            ) : (
                <div className="header-container">
                    <ButtonText
                        className="btn textOnly"
                        text="Meus cursos"
                        onClick={() => navigate("/meus-cursos")}
                    />

                    <ButtonText
                        className="btn textOnly"
                        text="Ensine na SkillUp"
                        onClick={() => navigate("/ensine-na-skillUp")}
                    />

                    <div className="header-icons">
                        <ButtonIcon
                            icon="fa-solid fa-cart-shopping"
                            onClick={() => navigate("/carrinho")}
                            alt="Carrinho de compras"
                        />

                        <ButtonIcon
                            icon="fa-regular fa-heart"
                            onClick={() => navigate("/desejos")}
                            alt="Cursos desejados"
                        />

                        <img
                            className="user-icon"
                            src={
                                user.userImagePath
                                    ? getCloudImageUrl(user.userImagePath)
                                    : defaultIcon
                            }
                            onError={(e) => {
                                e.currentTarget.onerror = null;
                                e.currentTarget.src = defaultIcon;
                            }}
                            onClick={() => navigate("/perfil")}
                        />
                    </div>
                </div>
            )}
        </header>
    );
}

export default NavBar;