import { useState, useEffect } from "react";
import axios from "../../services/axios.js";
import { useNavigate, useParams } from "react-router-dom";

const EditWatch = () => {
  const { id } = useParams();
  const [watch, setWatch] = useState(null);
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [image, setImage] = useState(null);
  const [categories, setCategories] = useState([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState(""); // Changed to categoryId
  const navigate = useNavigate();

  useEffect(() => {
    const fetchWatch = async () => {
      try {
        const response = await axios.get(`/api/admin/watch/${id}`);
        setWatch(response.data);
        setName(response.data.name);
        setDescription(response.data.description);
        setPrice(response.data.price);
        setSelectedCategoryId(response.data.categoryId); // Update to use categoryId
      } catch (error) {
        console.error("Error fetching watch:", error);
      }
    };

    fetchWatch();
  }, [id]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get("/api/admin/category");
        setCategories(response.data);
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    };

    fetchCategories();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("name", name);
    formData.append("description", description);
    formData.append("price", price);
    formData.append("categoryId", selectedCategoryId); // Changed to categoryId
    if (image) formData.append("imageFile", image); // Ensure the key matches the backend

    try {
      await axios.put(`/api/admin/watch/${id}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      navigate("/admin/watches"); // Redirect to watch management page
    } catch (error) {
      console.error("Error updating watch:", error);
    }
  };

  if (!watch) return <p>Loading...</p>;

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Edit Watch</h1>
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
        <div className="mb-4">
          <label className="block mb-2">Price</label>
          <input
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            className="border border-gray-300 px-4 py-2 w-full"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block mb-2">Category</label>
          <select
            value={selectedCategoryId} // Use categoryId here
            onChange={(e) => setSelectedCategoryId(e.target.value)}
            className="border border-gray-300 px-4 py-2 w-full"
            required
          >
            <option value="">Select Category</option>
            {categories.map((category) => (
              <option key={category.id} value={category.id}>
                {" "}
                {/* Use category.id here */}
                {category.name}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-4">
          <label className="block mb-2">Image</label>
          <input
            type="file"
            onChange={(e) => setImage(e.target.files[0])}
            className="border border-gray-300 px-4 py-2 w-full"
          />
        </div>
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Update Watch
        </button>
      </form>
    </div>
  );
};

export default EditWatch;
