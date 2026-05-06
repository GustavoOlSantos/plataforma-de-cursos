import React from "react";
import { useNavigate } from "react-router-dom";
import Tags from "../tags-cursos";

function CardCursos({ curso, maisVendidos }) {

    const navigate = useNavigate();
    
    return (
        <article key={curso.id} className="card-curso" onClick={() => navigate(`/curso?id=${curso.id}`)}>

            <section className="card-image">
                <img src={`http://localhost:8080/uploads/${curso.imagemUrl}`} />
            </section>

            <section>
                <h2>{curso.nome}</h2>
                <section className="curso-infos"> {curso.instrutor} | {curso.alunosMatriculados} alunos | {curso.nivel}</section>
            </section>

            <section className="curso-avaliacao">  
               {maisVendidos && <Tags className="mais-vendidos" texto="Mais vendidos" />}
               <Tags icone="fa-solid fa-star star" dado={curso.avalicao}/>
               <Tags dado={curso.qtdAvaliacao} texto="Avaliações"/>
            </section>

            <section className="curso-preco">
                <h2>R$ {curso.preco}</h2>
            </section>

        </article>
    );
}

export default CardCursos;