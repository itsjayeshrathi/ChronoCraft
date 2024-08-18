// src/components/Header.js
import React, { useContext, useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import AuthContext from '../context/AuthContext';
import axios from 'axios';
import SearchBar from './SearchBar';
import '../styles/Header.css'

function Header() {
    const { userId, logout } = useContext(AuthContext);
    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        if (userId) {
            axios.get(`http://localhost:8080/users/${userId}`)
                .then((response) => {
                    if (response.data) {
                        setUser(response.data);
                    } else {
                        console.log("User data is not available");
                        setError('User data is not available');
                    }
                    setLoading(false);
                })
                .catch((err) => {
                    console.log("Error fetching user data");
                    setError(err.message || 'Error fetching user data');
                    setLoading(false);
                });
        } else {
            console.log("User ID is not available");
            setError('User ID is not available');
            navigate('/login');
            setLoading(false);
        }
    }, [userId]);

    const handleSearch = (query) => {
        // Navigate to the search results page with the query
        navigate(`/search?query=${encodeURIComponent(query)}`);
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <header className="bg-dark text-white p-3">
            <div className="container">
                <h1 className="mb-3">ChronoCraft</h1>
                <SearchBar onSearch={handleSearch} />
                <nav>
    <Link to="/" className="text-white mx-2">Home</Link>
    <Link to="/products" className="text-white mx-2">Products</Link>
    <Link to="/register" className="text-white mx-2">Register</Link>
    <div className="right-nav">
        {userId ? (
            <>
                <span>Welcome, {`${user.role}-${user.firstName}`}</span>
                <Link to="/cart" className="text-white mx-2">Cart</Link>
                <Link to="/orderslist" className="text-white mx-2">Orders</Link>
                <button onClick={logout} className="btn btn-link">Logout</button>
            </>
        ) : (
            <Link to="/login" className="text-white mx-2">Login</Link>
        )}
    </div>
</nav>

            </div>
        </header>
    );
}

export default Header;
