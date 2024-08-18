import React, { useState } from 'react';
import axios from 'axios';

const UserRegistrationForm = () => {
  const [userData, setUserData] = useState({
    firstName: '',
    lastName: '',
    emailId: '',
    password: '',
    phoneNo: '',
    street: '',
    city: '',
    zipcode: '',
    role: ''
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({ ...userData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/users/register', userData);
      setMessage('Registration successful!');
      setError('');
      // Optionally redirect or perform further actions
      console.log('Registration successful:', response.data);
    } catch (error) {
      setError('Registration failed. Please check your details.');
      setMessage('');
      console.error('Error registering user:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>User Registration</h2>
      <div>
        <label>First Name:</label>
        <input
          type="text"
          name="firstName"
          value={userData.firstName}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Last Name:</label>
        <input
          type="text"
          name="lastName"
          value={userData.lastName}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Email ID:</label>
        <input
          type="email"
          name="emailId"
          value={userData.emailId}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="password"
          name="password"
          value={userData.password}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Phone No:</label>
        <input
          type="text"
          name="phoneNo"
          value={userData.phoneNo}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Street:</label>
        <input
          type="text"
          name="street"
          value={userData.street}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>City:</label>
        <input
          type="text"
          name="city"
          value={userData.city}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Zipcode:</label>
        <input
          type="text"
          name="zipcode"
          value={userData.zipcode}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Role:</label>
        <input
          type="text"
          name="role"
          value={userData.role}
          onChange={handleChange}
        />
      </div>
      <button type="submit">Register</button>
      {message && <p className="success-message">{message}</p>}
      {error && <p className="error-message">{error}</p>}
    </form>
  );
};

export default UserRegistrationForm;
