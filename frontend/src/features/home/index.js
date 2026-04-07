import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

function Home(){

    const navigate = useNavigate();
   
    return(
        <div className="HomePage">
            <p>Bem-vindo à página inicial!</p>
        </div>
    )
    
}
export default Home;