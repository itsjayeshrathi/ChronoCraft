import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import '../styles/UserLoginForm.css';

const UserLoginForm = () => {
  const [loginData, setLoginData] = useState({
    emailId: '',
    password: '',
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData({ ...loginData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/users/login', loginData);
      const { data } = response;

      if (data.role === 'Admin') {
        navigate('/adminpage');
      } else {
        navigate('/');
      }
      sessionStorage.setItem('userId', data.id);
      setMessage('Login successful!');
      setError('');
    } catch (error) {
      setError('Login failed. Please check your credentials.');
      setMessage('');
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2>User Login</h2>
        <div>
          <label>Email ID:</label>
          <input
            type="email"
            name="emailId"
            value={loginData.emailId}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={loginData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Login</button>
        {message && <p className="success-message">{message}</p>}
        {error && <p className="error-message">{error}</p>}
      </form>
      <div className="registration-link">
        <p>Don't have an account? <Link to="/register">Register here</Link></p>
      </div>
    </div>
  );
};

export default UserLoginForm;
  