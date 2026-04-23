import React from "react";
import { useNavigate } from 'react-router-dom';

function SearchBar(){

    const navigate = useNavigate();

    return(
        <>
            <input type="text" className="main-searcher" placeholder="Busque pelo nome do curso ou área de interesse..." />
        </>
    )
}
export default SearchBar;