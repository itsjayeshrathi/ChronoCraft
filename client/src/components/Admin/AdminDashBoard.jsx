import { Link } from "react-router-dom";

const AdminDashboard = () => {
  return (
    <div className="p-4">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <Link
          to="/admin/categories"
          className="bg-gray-200 p-4 rounded shadow hover:bg-gray-300"
        >
          <h2 className="text-xl font-bold">Categories</h2>
          <p>Manage product categories</p>
        </Link>
        <Link
          to="/admin/watches"
          className="bg-gray-200 p-4 rounded shadow hover:bg-gray-300"
        >
          <h2 className="text-xl font-bold">Watches</h2>
          <p>Manage product watches</p>
        </Link>
        <Link
          to="/admin/orders"
          className="bg-gray-200 p-4 rounded shadow hover:bg-gray-300"
        >
          <h2 className="text-xl font-bold">Orders</h2>
          <p>Manage customer orders</p>
        </Link>
      </div>
    </div>
  );
};

export default AdminDashboard;
