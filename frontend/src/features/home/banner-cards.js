import React, {useState, useEffect} from 'react';

function BannerCards(){

    const [currentIndex, setCurrentIndex] = useState(0);
    const [cards, setCards] = useState([]);

    useEffect(() => {
        const data = [
            {
                id: 1,
                title: "Transforme seu futuro hoje",
                description: "Aprenda habilidades que o mercado realmente valoriza.",
                extra: "Comece agora, no seu ritmo."
            },
            {
                id: 2,
                title: "Menos teoria, mais prática",
                description: "Aprenda com projetos reais desde o primeiro módulo.",
                extra: "Construa experiência enquanto evolui."
            },
            {
                id: 3,
                title: "Evolua sem pressão",
                description: "Estude no seu tempo, com total controle e flexibilidade.",
                extra: "Você decide quando e como aprender."
            }
        ];

        setCards(data);
    }, []);

    useEffect(() => {
        if (cards.length === 0) return;

        const interval = setInterval(() => {
            setCurrentIndex((prev) =>
                prev === cards.length - 1 ? 0 : prev + 1
            );
        }, 4000);

        return () => clearInterval(interval);
    }, [cards.length]);

    const nextSlide = () => {
        setCurrentIndex((prev) =>
            prev === cards.length - 1 ? 0 : prev + 1
        );
    };

    const prevSlide = () => {
        setCurrentIndex((prev) =>
            prev === 0 ? cards.length - 1 : prev - 1
        );
    };

    return(
        <div className="carousel banner-cards">
        
             <div className="carousel-track" style={{ transform: `translateX(-${currentIndex * 100}%)` }}>
                    {cards.map((card, index) => (
                        <div key={index} className="card">
                            <h2>{card.title}</h2>
                            <p>{card.description}</p>
                            <p>{card.extra}</p>
                        </div>
                    ))}
            </div>

            
            <footer>
                <button onClick={prevSlide} className="btn-prev">‹</button>
                    
                    {cards.map((card, index) => (
                        <div key={index} className={`indicator ${index === currentIndex ? 'active' : ''}`}>
                            •
                        </div>
                    ))}
                <button onClick={nextSlide} className="btn-next">›</button>
            </footer>
        </div>
    )
}  

export default BannerCards;