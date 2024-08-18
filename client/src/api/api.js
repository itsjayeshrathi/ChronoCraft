// src/api/api.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Replace with your actual backend API URL

// export const fetchProducts = () => {
//   return axios.get(`${API_BASE_URL}/watches/all`);
// };
export const fetchProducts = async () => {
    try {
        const response = await axios.get('http://localhost:8080/watches/all');
        console.log('API Response:', response.data); // Log response data
        return response;
    } catch (error) {
        console.error('API Error:', error); // Log error if request fails
        throw error;
    }
};

export const loginUser = (userData) => {
  return axios.post(`${API_BASE_URL}/users/login`, userData);
};

// Fetch all users
export const fetchUsers = () => {
    return axios.get('/users');
};

// Fetch a user by ID
export const fetchUserById = (userId) => {
    return axios.get(`/users/${userId}`);
};

// Register a new user
export const registerUser = (userData) => {
    return axios.post('/users/register', userData);
};

// Add more API functions as needed
