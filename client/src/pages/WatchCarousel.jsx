import React, { useState, useEffect } from 'react';
import './WatchCarousel.css'; // Import your custom styles

const WatchCarousel = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  // Sample data for watches
  const watches = [
    {
      id: 1,
      image: 'https://images.unsplash.com/photo-1495857000853-fe46c8aefc30?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
      title: 'Luxury Gold Watch',
      description: 'A timeless piece with gold detailing and leather strap.',
    },
    {
      id: 2,
      image: 'https://images.unsplash.com/photo-1451859757691-f318d641ab4d?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
      title: 'Modern Steel Watch',
      description: 'Sleek and stylish with a durable steel band.',
    },
    {
      id: 3,
      image: 'https://plus.unsplash.com/premium_photo-1713175990155-b47bdda96331?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
      title: 'Classic Leather Watch',
      description: 'Classic design with a premium leather band.',
    },
    {
      id: 4,
      image: 'https://images.unsplash.com/photo-1434493789847-2f02dc6ca35d?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTAzfHx3YXRjaHN8ZW58MHwwfDB8fHww',
      title: 'Smart Digital Watch',
      description: 'Stay connected with this digital smartwatch.',
    },
  ];

  // Automatically change the slide every 3 seconds
  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((currentIndex + 1) % watches.length);
    }, 3000);
    return () => clearInterval(interval);
  }, [currentIndex, watches.length]);

  // Handle manual navigation
  const prevSlide = () => {
    setCurrentIndex((currentIndex - 1 + watches.length) % watches.length);
  };

  const nextSlide = () => {
    setCurrentIndex((currentIndex + 1) % watches.length);
  };

  return (
    <div className="watch-carousel">
      <button className="watch-carousel__button watch-carousel__button--prev" onClick={prevSlide}>
        &#10094;
      </button>
      <button className="watch-carousel__button watch-carousel__button--next" onClick={nextSlide}>
        &#10095;
      </button>
      {watches.map((watch, index) => (
        <div
          key={watch.id}
          className={`watch-carousel__slide ${
            index === currentIndex ? 'watch-carousel__slide--active' : ''
          }`}
        >
          <img src={watch.image} alt={watch.title} className="watch-carousel__image" />
          <div className="watch-carousel__info">
            <h2 className="watch-carousel__title">{watch.title}</h2>
            <p className="watch-carousel__description">{watch.description}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default WatchCarousel;
