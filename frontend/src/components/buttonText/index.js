import React from "react";
import { useNavigate } from 'react-router-dom';

function ButtonText({ className, text, onClick }){

    const navigate = useNavigate();

    return(
        <button className={className} onClick={onClick}>
            {text}
        </button>
    )
}
export default ButtonText;