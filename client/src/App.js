// src/App.js
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Products from './pages/Products';
import CartPage from './pages/CartPage';
import Login from './pages/Login';
import Register from './pages/Register';
import AddWatchForm from './pages/AddWatchForm';
import Orders from './components/Orders';
import { AuthProvider } from './context/AuthContext';
import OrdersList from './components/OrdersList';
import SearchResult from './pages/SearchResult';
import AdminPage from './pages/AdminPage';

function App() {
  const user = sessionStorage.getItem('userId');  
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<Products />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/addwatch" element={<AddWatchForm />} />
        <Route path="/orders" element={<Orders />} />
        <Route path="/orderslist" element={<OrdersList />} />
        <Route path="/search" element={<SearchResult />} />
        <Route path="/adminpage" element={<AdminPage/>}></Route>
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
