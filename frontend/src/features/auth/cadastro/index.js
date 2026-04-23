import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import {authService} from "../services/authService";

import "./style.css";
import ButtonText from '../../../components/buttonText';

function Cadastro(){

    const navigate = useNavigate();
   //=> State do Formulário
    let [formData, setFormData] = useState({
        nome: "",
        email: "",
        password: "",
        confirmPassword: ""
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

        if(formData.nome === "" || formData.email === ""  || formData.password === "" || formData.confirmPassword === ""){
            setError("Por favor, preencha todos os campos.");
            return;
        }

        if(!isEmailValido(formData.email)){
            setError("Por favor, insira um email válido.");
            return;
        }

        if(formData.password !== formData.confirmPassword){
            setError("As senhas não coincidem.");
            return;
        }

        authService.register(formData.nome, formData.email, formData.password)
        .then(res => {
            console.log("Sucesso ao Cadastrar:", res.data);
            navigate("/Entrar");
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
            <div className="register-form">
                <h1>Cadastre-se e comece a aprender</h1>
                <p>Tenha acesso a cursos vitalícios para aprender
                no seu ritmo e desenvolver habilidades que fazem a diferença no seu futuro.</p>
                <form onSubmit={handleSubmit}>
                    <input type="text" name="nome" placeholder="Nome Completo" value={formData.nome} onChange={handleChange}/>
                    <input type="text" name="email" placeholder="Email" value={formData.email} onChange={handleChange}/>
                    <input type="password" name="password" placeholder="Senha" value={formData.password} onChange={handleChange}/>
                    <input type="password" name="confirmPassword" placeholder="Confirmar Senha" value={formData.confirmPassword} onChange={handleChange}/>

                    {error && <p className="error">{error}</p>}

                    <ButtonText className="btn full" text="Registrar" type="submit"/>
                </form>

            </div>

            <div className="register-image">
               <img src="/Full.png" alt="Logo Completo"/>
            </div>
        </div>
    )
}
export default Cadastro;