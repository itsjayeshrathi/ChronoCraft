import { useState, useEffect } from "react";
import axios from "../../services/axios.js";
import { useNavigate, useParams } from "react-router-dom";

const EditCategory = () => {
  const { id } = useParams(); // Extract ID from URL parameters
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const navigate = useNavigate(); // Hook to programmatically navigate

  useEffect(() => {
    const fetchCategory = async () => {
      try {
        const response = await axios.get(`/api/admin/category/${id}`);
        setName(response.data.name);
        setDescription(response.data.description);
      } catch (error) {
        console.error("Error fetching category:", error);
      }
    };

    if (id) {
      fetchCategory(); // Fetch category details if ID exists
    }
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`/api/admin/category/${id}`, { name, description });
      navigate("/admin/categories"); // Navigate back to category management page after update
    } catch (error) {
      console.error("Error updating category:", error);
    }
  };

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Edit Category</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block mb-2">Name</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="border border-gray-300 px-4 py-2 w-full"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block mb-2">Description</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="border border-gray-300 px-4 py-2 w-full"
            required
          ></textarea>
        </div>
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Update
        </button>
      </form>
    </div>
  );
};

export default EditCategory;
