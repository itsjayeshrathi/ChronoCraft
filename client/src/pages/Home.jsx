// src/pages/Home.js
import React from 'react';
import WatchCarousel from './WatchCarousel';
import Products from './Products';

function Home() {
  return (
    <div className="container mt-5">
      <WatchCarousel></WatchCarousel>
    <Products></Products>
    </div>
  );
}

export default Home;
