import react, {useState, useEffect} from "react"
import { useNavigate, useParams } from "react-router-dom";
import { Navigate } from "react-router-dom";
import ReactMarkdown from "react-markdown";

import api from "../../../services/api";
import ButtonText from "../../../components/buttonText";
import Tags from "../../../components/tags-cursos";
import AvaliacoesCard from "../../../components/card-avaliacoes";
import Loading from "../../../components/loading";
import NotFound from "../../../features/not-found/";

import "../../../styles/cards-avaliacao.css";

function CursoPage() {
  const { slug } = useParams();
  const navigate = useNavigate();

  const [curso, setCurso] = useState(null);
  const [avaliacoes, setAvaliacoes] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [nota, setNota] = useState(0);
  const [avaliacoesNum, setAvaliacoesNum] = useState(0);

  const [loading, setLoading] = useState(true);
  const [notFound, setNotFound] = useState(false);

  useEffect(() => {

    setLoading(true);

    api.get(`cursos/slug/${slug}`)
    .then(res =>{
      setCurso(res.data[0]);

    })
    .catch(err => {
      console.error("Falha ao obter curso: ", err);
      setLoading(false);
      setNotFound(true)
    });
  }, [slug])

  useEffect(() => {
     if (!curso) return;

     api.get(`avaliacoes/curso/id-curso/${curso.id}`)
    .then(res =>{
      setAvaliacoes(res.data);
      setLoading(false);
    })
    .catch(err => {
      console.error("Falha ao obter avaliações do curso: ", err);
      setLoading(false);
    });
  }, [curso])

  useEffect(() => {
    if(!avaliacoes || avaliacoes.length === 0 ) return;

    const total = avaliacoes.reduce((acc, avaliacao) => acc + avaliacao.nota, 0);
    setAvaliacoesNum(avaliacoes.length);
    setNota((total / avaliacoes.length).toFixed(1));

  }, [avaliacoes])

  if (loading) {
    return <Loading texto="curso"/>;
  }

  if(notFound){
    return <NotFound />
  }

  const visibleCards = 3;
    const step = 1;
    const maxIndex = avaliacoes.length - visibleCards;

    const nextSlide = () => {
        setCurrentIndex((prev) =>
            prev + step > maxIndex ? 0 : prev + step
        );
    };

    const prevSlide = () => {
        setCurrentIndex((prev) =>
            prev - step < 0 ? maxIndex : prev - step
        );
    };
  

  return (
    <main className="curso-page">

      <section>
        <section className="curso-hero">

          <figure className="curso-banner">
            <img
              src={`http://localhost:8080/uploads/${curso.imagemUrl}`}
              alt={curso.nome}
            />
          </figure>

          <div className="curso-hero-content">

            <span> 
              <h1>{curso.nome}</h1>

              <p className="curso-subtitulo">
                {curso.subtitulo}
              </p>

              <div className="curso-tags">
                <Tags icone="fa-solid fa-award" texto={curso.nivel} 
                className={curso.nivel.normalize("NFD")
                  .replace(/[\u0300-\u036f]/g, "") 
                  .toLowerCase()                 
                  .replace(/[^a-z0-9 ]/g, "")} />

                {curso.subcategorias.map(categoria => (
                  <Tags texto={categoria.nome} />
                ))}
              </div>

              <div className="curso-atualizacao">
                <i className="fa-solid fa-chalkboard-user"></i>
                <span>Instruído por  <u>{curso.instrutor}</u></span>
              </div>

              <div className="curso-atualizacao">
                <i className="fa-solid fa-user"></i>
                <span>Se junte a {curso.alunosMatriculados} alunos</span>
              </div>
            </span>
            
            <div className="curso-atualizacao">
              <i className="fa-regular fa-calendar-days"></i>
              <span>Última atualização em  {new Date(curso.ultimaAtualizacao).toLocaleDateString("pt-BR")}</span>
            </div>
          </div>
        </section>

        <section className="curso-avaliacoes">
          <h2>Avaliação do curso: <i className="fa-solid fa-star star"></i> <span className="h2-slim"> {nota} |  {avaliacoesNum} Avaliações</span></h2>  
        </section>

        <section>
          <div className="curso-info">
            <h2>Requisitos:</h2>
            
            <ul>
              {curso.requisitos.map(req =>(
                  <li>{req}</li>
              ))}
            </ul>

            <h2>Descrição:</h2>

              <div className="curso-descricao">
                <ReactMarkdown>
                  {curso.descricao}
                </ReactMarkdown>
              </div>
          </div>

          <hr></hr>

          <div className="curso-avaliacoes">
            <h2>Avaliações:</h2>
            <div className="carousel card-avaliacoes">
              
              <button onClick={prevSlide} className="btn-prev avaliacoes">‹</button>
                
              <div className="carousel-track-avaliacoes" style={{ transform: `translateX(-${currentIndex * 300}px)` }}>
                {avaliacoes.map(avaliacao => (
                    <AvaliacoesCard avaliacao={avaliacao} />
                  ))}
              </div>

              <button onClick={nextSlide} className="btn-next avaliacoes">›</button>
            </div>
          </div>

          <div className="cursos-relacionados">
              
          </div>
        </section>
      </section>

      <aside className="curso-sidebar">

        <div className="curso-card-compra">
          
          <div>
            <span className="curso-card-label">
              Adquira este curso
            </span>

            <h1 className="curso-preco">
              R$ {curso.preco.toLocaleString()}
            </h1>
          

            <div className="curso-beneficio">

              <span className="beneficio-row">
                <i className="fa-solid fa-chalkboard"></i>
                <span>{curso.numeroAulas} aulas</span>
              </span>

              <span className="beneficio-row">
                <i className="fa-solid fa-film"></i>
                <span>{curso.duracao} horas de vídeo</span>
              </span>

              <span className="beneficio-row">
                <i className="fa-solid fa-infinity"></i>
                <span>Acesso vitalício</span>
              </span>

              <span className="beneficio-row">
                <i className="fa-solid fa-certificate"></i>
                <span>Certificado de conclusão</span>
              </span>

              <span className="beneficio-row">
                <i className="fa-regular fa-clock"></i>
                <span>Cancelamento gratuito em até 7 dias</span>
              </span>
            </div>
          </div>

          <div className="curso-actions">
            <ButtonText className="btn full full-sized" text="Comprar agora" onClick={null}/>
            <ButtonText className="btn regular full-sized" text="Adicionar ao carrinho" onClick={null} />
          </div>
        </div>

        <hr style={{marginBottom: 15}}></hr>

        <div className="outras-actions">
          <div className="action-row">
            <i className="fa-solid fa-share-nodes"></i>
            <span>Compartilhe este curso</span>
          </div>
        </div>

      </aside>

    </main>
  );
}

export default CursoPage;