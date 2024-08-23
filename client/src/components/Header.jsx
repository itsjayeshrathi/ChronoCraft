import { Link } from "react-router-dom";

const Header = () => {
  return (
    <header className="bg-gray-800 text-white fixed w-full top-0 left-0 shadow-md z-10">
      <div className="container mx-auto flex justify-between items-center py-4 px-6">
        <div className="text-2xl font-bold">
          <Link to="/">ChronoCraft</Link>
        </div>
        <div className="flex-grow mx-6">
          <input
            type="text"
            placeholder="Search for watches..."
            className="w-full py-2 px-4 rounded-md text-black focus:outline-none"
          />
        </div>
        <div className="flex space-x-6">
          <Link to="/login" className="hover:text-gray-400">
            Login
          </Link>
          <Link to="/register" className="hover:text-gray-400">
            Register
          </Link>
          <Link to="/cart" className="hover:text-gray-400">
            Cart
          </Link>
        </div>
      </div>
    </header>
  );
};

export default Header;
