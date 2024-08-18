// src/components/ProductCard.js
import React from 'react';

function ProductCard({ product }) {
  return (
    <div className="card">
      <img src={product.imageUrl} className="card-img-top" alt={product.name} />
      <div className="card-body">
        <h5 className="card-title">{product.name}</h5>
        <p className="card-text">${product.price}</p>
        <a href={`/products/${product.id}`} className="btn btn-primary">View Details</a>
      </div>
    </div>
  );
}

export default ProductCard;
