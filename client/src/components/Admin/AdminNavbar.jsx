import { Link, useNavigate } from "react-router-dom"; // Import Link

const AdminNavbar = () => {
  const navigate = useNavigate(); // Use useNavigate for navigation

  const handleLogout = () => {
    localStorage.removeItem("authToken"); // Example of clearing a token from localStorage
    navigate("/login"); // Redirect to login page
  };

  return (
    <nav className="bg-gray-800 text-white p-4 flex items-center justify-between">
      <Link to="/admin" className="text-lg font-bold">
        ChronoCraft
      </Link>{" "}
      {/* Make logo clickable */}
      <div className="flex-grow text-center text-lg font-bold">
        Admin Dashboard
      </div>
      <button
        onClick={handleLogout}
        className="bg-red-600 px-4 py-2 rounded hover:bg-red-700"
      >
        Logout
      </button>
    </nav>
  );
};

export default AdminNavbar;
