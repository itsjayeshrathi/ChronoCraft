// src/pages/SearchResults.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function SearchResults() {
    const query = useQuery().get('query');
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [cart, setCart] = useState([]);
    const navigate =useNavigate();

    useEffect(() => {
        const fetchSearchResults = async () => {
            try {
                const response = await axios.get('http://localhost:8080/watches/search', {
                    params: { brand: query }
                });
                setProducts(response.data);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        if (query) {
            fetchSearchResults();
        }
    }, [query]);

    const handleAddToCart = (product) => {
        
        const addToCartDTO = {
            watchId: product.id,
            quantity: 1,
            userId:sessionStorage.getItem('userId')
        };

        // Log the DTO to verify
        console.log('Adding to cart:', addToCartDTO);

        axios.post('http://localhost:8080/users/cart/add', addToCartDTO)
            .then(response => {
                // Update cart state with the new item
                setCart(prevCart => [...prevCart, product]);
                console.log('Product added to cart:', response.data);
            })
            .catch(err => {
                setError('Error adding product to cart: ' + err.message);
                console.error('Error:', err);
            });
            sessionStorage.setItem('reloadPage', 'true');
            navigate('/cart');
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="container">
            <h2>Search Results for "{query}"</h2>
            <div className="row">
                {products.map((product) => (
                    <div key={product.id} className="col-md-4">
                        <div className="card mb-4">
                            <img
                                src={product.imagePath}
                                alt={product.brand || product.title}
                                className="card-img-top"
                                style={{ width: '100%', height: '100%' }}
                            />
                            <div className="card-body">
                                <h5 className="card-title">{product.brand || product.title}</h5>
                                <p className="card-text">{product.description}</p>
                                <p className="card-text">Rs. {product.price}</p>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => handleAddToCart(product)}
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

export default SearchResults;
