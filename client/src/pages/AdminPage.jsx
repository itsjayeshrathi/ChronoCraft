import React from 'react';
import { Link } from 'react-router-dom';

function AdminPage() {
  return (
    <div className="container mt-5">
      <h1>Admin Dashboard</h1>
      <ul className="list-group">
        <li className="list-group-item">
          <Link to="/addwatch">Add New Watch</Link>
        </li>
        <li className="list-group-item">
          <Link to="/admin/add-category">Add New Category</Link>
        </li>
        <li className="list-group-item">
          <Link to="/admin/remove-watch">Remove Watch by ID</Link>
        </li>
        <li className="list-group-item">
          <Link to="/admin/remove-category">Remove Category</Link>
        </li>
        <li className="list-group-item">
          <Link to="/admin/orders">View All Orders</Link>
        </li>
        <li className="list-group-item">
          <Link to="/admin/reports">Generate Reports</Link>
        </li>
      </ul>
    </div>
  );
}

export default AdminPage;
