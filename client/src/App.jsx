import {
  BrowserRouter as Router,
  Routes,
  Route,
  useLocation,
} from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Login from "./components/Login";
import Register from "./components/Register";
import AdminPage from "./pages/AdminPage";
import AdminNavbar from "./components/Admin/AdminNavbar";

const App = () => {
  return (
    <Router>
      <RouteHandler />
    </Router>
  );
};

const RouteHandler = () => {
  const location = useLocation();
  const isAdminRoute = location.pathname.startsWith("/admin");

  return (
    <div className="flex flex-col min-h-screen">
      {!isAdminRoute ? <Header /> : <AdminNavbar />}
      <main className="flex-grow">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/admin/*" element={<AdminPage />} />
        </Routes>
      </main>
      {!isAdminRoute && <Footer />}
    </div>
  );
};

export default App;
