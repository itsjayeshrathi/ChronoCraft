// src/pages/Products.js
import React, { useEffect, useState } from 'react';
import { fetchProducts } from '../api/api';
import ProductList from '../components/ProductList';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function Products() {
    const navigate=useNavigate();
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [cart, setCart] = useState([]);

    useEffect(() => {
        fetchProducts()
            .then((response) => {
                // Assuming the response data is an array of products
                setProducts(response.data); 
                setLoading(false);
            })
            .catch((err) => {
                setError(err);
                setLoading(false);
            });
    }, []);

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
        return <p>Loading products...</p>;
    }

    if (error) {
        return <p>Error fetching products: {error.message}</p>;
    }

    if (products.length === 0) {
        return <p>No products available</p>;
    }

    return (
        <div className="container mt-5">
            <h2>Our Products</h2>
            <ProductList products={products} onAddToCart={handleAddToCart} />
        </div>
    );
}

export default Products;
