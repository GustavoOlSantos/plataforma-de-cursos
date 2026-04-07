import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import {authService} from "../services/authService";

function Cadastro(){

    const navigate = useNavigate();
   //=> State do Formulário
    let [formData, setFormData] = useState({
        user: "",
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

        if(formData.user === "" || formData.password === ""){
            setError("Por favor, preencha todos os campos.");
            return;
        }

        let success = authService.login(formData.user, formData.password);
        if(!success){
            setError("Usuário ou senha inválidos.");
            return;
        }
        navigate("/Dashboard");
    }

    return(
        <div className="login-page">
            cadastro
        </div>
    )
    // <FormLogin handleChange={handleChange} submit={handleSubmit} error={error}/>
}
export default Cadastro;