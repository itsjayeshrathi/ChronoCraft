// src/components/Footer.js
import React from 'react';

function Footer() {
  return (
    <footer className="bg-dark text-white p-3 text-center">
      <div className="container">
        <p>&copy; {new Date().getFullYear()} ChronoCraft. All rights reserved.</p>
      </div>
    </footer>
  );
}

export default Footer;
