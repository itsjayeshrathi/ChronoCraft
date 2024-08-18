import React,{ createContext, useState } from 'react';

 const AuthContext = createContext();

 const AuthProvider = ({ children }) => {
  //const [auth, setAuth] = useState(null);
  const [userId, setUser] = useState(sessionStorage.getItem('userId') || null);

  const login = (userData) => {
    setUser(userData);
    sessionStorage.setItem('userId', JSON.stringify(userData));
  };

  const logout = () => {
    setUser(null);
    sessionStorage.removeItem('userId');
  };

  const isLoggedIn = () => {
    return userId !== null;
  };

  return (
    <AuthContext.Provider value={{ userId, login, logout, isLoggedIn }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
export { AuthProvider };
