import React from "react";
import { useNavigate } from 'react-router-dom';

function buttonIcon({ icon, onClick, alt, className}){

    const navigate = useNavigate();

    return(
        <>
            <button className={`btn-icon ${className || ''}`} onClick={onClick} alt={alt}>
                <i className={icon}></i>
            </button>
        </>
    )
}
export default buttonIcon;