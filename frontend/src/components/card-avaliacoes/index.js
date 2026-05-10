import react, {useState, useEffect} from "react";
import api from "../../services/api";

function AvaliacoesCard({avaliacao}){

    const [user, setUser] = useState(null);

    useEffect(() => {
        api.get(`auth/id/${avaliacao.userId}`)
        .then(res =>{
            setUser(res.data);
        })
    },  [])

    if(user == null){
        return(<></>);
    }
    
    return(
        <article key={avaliacao.id} className="card-avaliacao">

            <section className="card-header">
                <figure className="user-card-pic">
                    <img src={
                        user.userImagePath
                        ? `http://localhost:8080/uploads/${user.userImagePath}`
                        : "http://localhost:8080/uploads/default-icon.jpg"
                        }
                        onError={(e) => {
                            e.currentTarget.onerror = null; // evita loop
                            e.currentTarget.src = "http://localhost:8080/uploads/default-icon.jpg";
                        }}
                    />
                </figure>

                <div>
                    <div> 
                        <b><p>{user.nome.split(" ").slice(0,2).join(" ")}</p></b>

                        <p className="card-avaliacao-data">{new Date(avaliacao.dataAvaliacao).toLocaleDateString("pt-BR")}</p>         
                    </div>
                    
                    <div className="star-rate">
                        {Array.from({ length: 5 }).map((_, index) => (
                            <i key={index}
                            className={
                                index < avaliacao.nota
                                ? "fa-solid fa-star star"
                                : "fa-solid fa-star missing"
                            }
                            ></i>
                        ))}
                    </div>
                </div>    
            </section>

            <section>
                <p>{avaliacao.mensagem}</p>
            </section>
    </article>
    )   
}

export default AvaliacoesCard;