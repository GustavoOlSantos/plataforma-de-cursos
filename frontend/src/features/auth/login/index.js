import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect } from "react";
import {authService} from "../services/authService";

import ButtonText from '../../../components/buttonText';
import "../style.css";

function Login(){

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("token");

        if (token) {
            navigate("/");
        }
    }, [navigate]);

   //=> State do Formulário
    let [formData, setFormData] = useState({
        email: "",
        password: ""
    });

    //=> State par mensagens de erro
    let [error, setError] = useState(null);

    const handleChange = (e) => {   //=> Função para capturar os dados do formulário
        const campo = e.target.name;
        const valor = e.target.value;
        setFormData(values => ({...values, [campo]: valor}))
    }

    function handleSubmit(e){   //=> Função para validar o formulário
        setError(null);
        e.preventDefault();

        if(formData.email === "" || formData.password === ""){
            setError("Por favor, preencha todos os campos.");
            return;
        }

        if(!isEmailValido(formData.email)){
            setError("Por favor, insira um email válido.");
            return;
        }

       authService.login(formData.email, formData.password)
       .then(res => {
            localStorage.setItem("token", res.data);
            navigate("/");
        })
        .catch(err => {
            const msg = err.response?.data?.message || "Erro inesperado";
            console.error(msg);
            setError(msg);
        });

    }

    function isEmailValido(email) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    return(
        <div className="double-container">
            <div className="register-image">
               <img src="/Full.png" alt="Logo Completo"/>
            </div>

            <div className="register-form">
                <h1>Bem-vindo de volta</h1>
                <p>Entre para acessar seus cursos e continuar aprendendo.</p>
                <form onSubmit={handleSubmit}>
                    <input type="text" name="email" placeholder="Email" value={formData.email} onChange={handleChange}/>
                    <input type="password" name="password" placeholder="Senha" value={formData.password} onChange={handleChange}/>

                    {error && <p className="error">{error}</p>}

                    <ButtonText className="btn full" text="Entrar" type="submit"/>
                </form>

            </div>
        </div>
    )
}
export default Login;