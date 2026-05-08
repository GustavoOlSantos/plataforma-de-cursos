import "./loading.css";

function Loading({texto}) {
  return (
    <div className="loading-container">

      <div className="loading-content">

        <div className="loading-icon-wrapper">

          <i className="fa-solid fa-book-open loading-icon"></i>

          <div className="loading-ring"></div>

        </div>

        <h2>Carregando {texto}</h2>

        <p>Aguarde um instante...</p>

      </div>

    </div>
  );
}

export default Loading;