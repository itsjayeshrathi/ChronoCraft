import { Routes, Route } from "react-router-dom";
// import AdminNavbar from "../components/Admin/AdminNavbar"; 
import AdminDashboard from "../components/admin/AdminDashboard";
import CategoryManagement from "../components/Admin/CategoryManagement";
import CreateCategory from "../components/Admin/CreateCategory";
import EditCategory from "../components/Admin/EditCategory";
import OrderManagement from "../components/Admin/OrderManagement";
import WatchManagement from "../components/Admin/WatchManagement";
import CreateWatch from "../components/Admin/CreateWatch";
import EditWatch from "../components/Admin/EditWatch";

const AdminPage = () => {
  return (
    <div>
      <div className="p-4">
        <Routes>
          <Route path="/" element={<AdminDashboard />} />{" "}
          {/* Route to AdminDashboard */}
          <Route path="/categories" element={<CategoryManagement />} />
          <Route path="/categories/create" element={<CreateCategory />} />
          <Route path="/categories/edit/:id" element={<EditCategory />} />
          <Route path="/orders" element={<OrderManagement />} />
          <Route path="/watches" element={<WatchManagement />} />
          <Route path="/watches/create" element={<CreateWatch />} />
          <Route path="/watches/edit/:id" element={<EditWatch />} />
        </Routes>
      </div>
    </div>
  );
};

export default AdminPage;
