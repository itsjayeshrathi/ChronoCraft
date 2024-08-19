import { useState, useEffect } from "react";
import axios from "../../services/axios.js";
import { Link } from "react-router-dom";

const WatchManagement = () => {
  const [watches, setWatches] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchWatches = async () => {
      try {
        const response = await axios.get("/api/admin/watches");
        setWatches(response.data);
      } catch (error) {
        console.error("Error fetching watches:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchWatches();
  }, []);

  const handleDelete = async (id) => {
    try {
      await axios.delete(`/api/admin/watch/${id}`);
      setWatches(watches.filter((watch) => watch.id !== id));
    } catch (error) {
      console.error("Error deleting watch:", error);
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Watch Management</h1>
      <Link
        to="/admin/watches/create"
        className="bg-blue-500 text-white px-4 py-2 rounded mb-4 inline-block"
      >
        Add New Watch
      </Link>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {watches.map((watch) => (
          <div key={watch.id} className="border rounded-lg p-4 shadow-sm">
            <img
              src={watch.imageUrl}
              alt={watch.name}
              className="w-full h-48 object-cover mb-4"
            />
            <h2 className="text-lg font-bold">{watch.name}</h2>
            <p className="text-gray-700">{watch.description}</p>
            <p className="text-gray-800 font-semibold mb-4">${watch.price}</p>
            <div className="flex justify-between">
              <Link
                to={`/admin/watches/edit/${watch.id}`}
                className="bg-yellow-500 text-white px-4 py-2 rounded"
              >
                Edit
              </Link>
              <button
                onClick={() => handleDelete(watch.id)}
                className="bg-red-500 text-white px-4 py-2 rounded"
              >
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default WatchManagement;
