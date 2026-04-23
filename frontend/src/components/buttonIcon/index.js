import React from "react";
import { useNavigate } from 'react-router-dom';

function buttonIcon({ icon, onClick, alt}){

    const navigate = useNavigate();

    return(
        <>
            <button className="btn-icon" onClick={onClick} alt={alt}>
                <i className={icon}></i>
            </button>
        </>
    )
}
export default buttonIcon;