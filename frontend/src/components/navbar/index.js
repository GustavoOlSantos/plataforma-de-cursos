import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from 'react-router-dom';

import SearchBar from "../searchBar";
import ButtonIcon from "../buttonIcon";
import ButtonText from "../buttonText";
import {Dropdown} from "../dropdown";

import { UserContext } from "../../app/providers/user-context";
import { authService } from "../../features/auth/services/authService";

import {getCloudImageUrl} from "../../services/cloud_images";
import defaultIcon from "../../assets/default-icon.jpg";

function NavBar() {

    const navigate = useNavigate();

    const { user } = useContext(UserContext);
    const [openModal, setOpenModal] = useState(false);

    useEffect(() => {
        const handler = (e) => {
            if (!e.target.closest("[data-dropdown]")) setOpenModal(false);
        };
        document.addEventListener("mousedown", handler);
        return () => document.removeEventListener("mousedown", handler);
    }, []);

    return (
        <header>
            <img
                src="/Title.png"
                alt="Logo"
                className="logo"
                onClick={() => navigate("/")}
            />

            <ButtonText
                className="btn textOnly disabled"
                text="Descobrir"
                /*onClick={() => navigate("/cursos")}*/
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
                        className="btn textOnly disabled"
                        text="Ensine na SkillUp"
                        /*onClick={() => navigate("/ensine-na-skillUp")}*/
                    />

                    <div className="header-icons">
                        <ButtonIcon
                            className="disabled"
                            icon="fa-solid fa-cart-shopping"
                            /*onClick={() => navigate("/carrinho")}*/
                            alt="Carrinho de compras"
                        />

                        <ButtonIcon
                            className="disabled"
                            icon="fa-regular fa-heart"
                            /*onClick={() => navigate("/desejos")}*/
                            alt="Cursos desejados"
                        />

                        <div style={{ position: "relative", display: "inline-block" }} data-dropdown>
                            <img
                                className="user-icon"
                                src={user.userImagePath ? getCloudImageUrl(user.userImagePath) : defaultIcon}
                                onError={(e) => {
                                    e.currentTarget.onerror = null;
                                    e.currentTarget.src = defaultIcon;
                                }}
                                onClick={() => setOpenModal(!openModal)}
                            />

                            {openModal && (
                                <Dropdown
                                    options={[
                                        { label: "Perfil", value: "profile", onClick: () => navigate("/perfil") },
                                        { label: "Configurações", value: "settings", onClick: () => navigate("#") },
                                        { label: "Sair", value: "logout", onClick: () => authService.logout() },
                                    ]}
                                />
                            )}
                        </div>

                    </div>
                </div>
            )}
        </header>
    );
}

export default NavBar;