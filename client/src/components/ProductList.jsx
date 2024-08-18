// src/components/ProductList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function ProductList({ onAddToCart }) {
    const [products, setProducts] = useState([]);
    const [sortOrder, setSortOrder] = useState('asc');

    useEffect(() => {
        fetchProducts();
    }, [sortOrder]);

    const fetchProducts = async () => {
        try {
            const url = 'http://localhost:8080/watches/sorted-by-price';
            const params = { ascending: sortOrder === 'asc' };
            const response = await axios.get(url, { params });
            setProducts(response.data);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    return (
        <div>
            <div className="sorting-options mb-3">
                <select
                    value={sortOrder}
                    onChange={(e) => setSortOrder(e.target.value)}
                    className="form-select"
                >
                    <option value="asc">Price: Low to High</option>
                    <option value="desc">Price: High to Low</option>
                </select>
            </div>

            <div className="row">
                {products.map((product) => (
                    <div key={product.id} className="col-md-4">
                        <div className="card mb-4">
                            <img
                                src={product.imagePath}
                                alt={product.brand || product.title}
                                className="card-img-top"
                                style={{ width: '100%', height: 'auto' }}
                            />
                            <div className="card-body">
                                <h5 className="card-title">{product.brand || product.title}</h5>
                                <p className="card-text">{product.description}</p>
                                <p className="card-text">Rs. {product.price}</p>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => onAddToCart(product)}
                                >
                                    Add to Cart
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ProductList;
