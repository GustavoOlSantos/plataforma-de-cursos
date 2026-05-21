import React, {useState, useEffect} from "react";
import { useNavigate } from "react-router-dom";

import api from "../../services/api";
import {getCloudImageUrl} from "../../services/cloud_images";
import Tags from "../tags-cursos";

function CardCursos({ curso, maisVendidos, origin, aulasConcluidas }) {

    const navigate = useNavigate();
    const [avaliacoes, setAvaliacoes] = useState(null);
    const [nota, setNota] = useState(0);
    const [avaliacoesNum, setAvaliacoesNum] = useState(0);
    const [imgLoaded, setImgLoaded] = useState(false);
    

    useEffect(() => {
        api.get(`avaliacoes/curso/id-curso/${curso.id}`)
        .then(res =>{
        setAvaliacoes(res.data);
        })
        .catch(err => {
        console.error("Falha ao obter avaliações do curso: ", err);
        });
    }, [])

    useEffect(() => {
        if(!avaliacoes || avaliacoes.length === 0 ) return;

        const total = avaliacoes.reduce((acc, avaliacao) => acc + avaliacao.nota, 0);
        setAvaliacoesNum(avaliacoes.length);
        setNota((total / avaliacoes.length).toFixed(1));

    }, [avaliacoes])


    
    return (
        <article key={curso.id} className="card-curso" onClick={() => navigate(`/cursos/${curso.slug}`)}>

            <section className={`card-image ${imgLoaded ? "loaded" : ""}`}>
                <img src={getCloudImageUrl(curso.imagemUrl)} alt={curso.nome} onLoad={() => setImgLoaded(true)}/>
            </section>

            <section>
                <h2>{curso.nome}</h2>
                <section className="curso-infos"> {curso.instrutor} | {curso.alunosMatriculados} alunos | {curso.nivel}</section>
            </section>

            <section className="curso-avaliacao">  
               {maisVendidos && <Tags className="mais-vendidos" texto="Mais vendidos" />}
               <Tags icone="fa-solid fa-star star" dado={nota}/>
               <Tags dado={avaliacoesNum} texto="Avaliações"/>
            </section>

            {origin == null || origin == undefined ?
                <section className="curso-preco">
                    <h2>R$ {curso.preco.toLocaleString()}</h2>
                </section>
            : 
                <section className="curso-progress">
                    <i className="fa-solid fa-trophy"></i>
                    <div className="progress-bar">
                        <div className="progress-fill" style={{ width: `${((aulasConcluidas / curso.numeroAulas) * 100).toFixed(0)}%` }} ></div>
                    </div>
                    <p>{((aulasConcluidas / curso.numeroAulas) * 100).toFixed(0)}%</p>
                </section>
            }
           

        </article>
    );
}

export default CardCursos;