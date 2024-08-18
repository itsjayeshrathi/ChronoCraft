import React, { useState } from 'react';
import axios from 'axios';
import { redirect } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

function AddWatchForm() {
    const [title, setTitle] = useState('');
    const [categoryId, setCategoryId] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [quantity, setQuantity] = useState('');
    const [error, setError] = useState(null);
    const [imageURL,setImgURL]=useState('');
    const [success, setSuccess] = useState(null);
    const [allcategories,setAllCategories]=useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const res=axios.get('http://localhost:8080/categories/all')
            .then((response) => {
                // Assuming the response data is an array of products
                setAllCategories(response.data); 
                setLoading(false);
            })
            .catch((err) => {
                setError(err);
                setLoading(false);
            });
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const watchDTO = {
            title,
            categoryId: parseInt(categoryId),
            description,
            price: parseFloat(price),
            quantity: parseInt(quantity),
            imageURL,
        };

        try {
            const response = await axios.post('http://localhost:8080/watches/addwatch', watchDTO);
            setSuccess('Watch added successfully!');
           alert("watch addeed successfully...");
         
            setError(null);
            navigate('/products');
        } catch (err) {
            setError('Error adding watch: ' + err.message);
            setSuccess(null);
        }
    };

    return (
        <div className="container mt-5">
            <h2>Add a New Watch</h2>
            {error && <p className="text-danger">{error}</p>}
            {success && <p className="text-success">{success}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="title">Title</label>
                    <input
                        type="text"
                        id="title"
                        className="form-control"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </div>
                {/* <div className="form-group">
                    <label htmlFor="categoryId">Category ID</label>
                    <input
                        type="number"
                        id="categoryId"
                        className="form-control"
                        value={categoryId}
                        onChange={(e) => setCategoryId(e.target.value)}
                        required
                    />
                </div> */}

            <div className="form-group">
            <label htmlFor="categoryId">Category</label>
            <select
                id="categoryId"
                className="form-control"
                value={categoryId}
                onChange={(e) => setCategoryId(e.target.value)}
                required
            >
                <option value="">Select a category</option>
                {allcategories.map((category) => (
                    <option key={category.id} value={category.id}>
                        {`ID: ${category.id} - ${category.name}`}
                    </option>
                ))}
            </select>
            </div>

                <div className="form-group">
                    <label htmlFor="description">Description</label>
                    <textarea
                        id="description"
                        className="form-control"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="price">Price</label>
                    <input
                        type="number"
                        id="price"
                        className="form-control"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="quantity">Quantity</label>
                    <input
                        type="number"
                        id="quantity"
                        className="form-control"
                        value={quantity}
                        onChange={(e) => setQuantity(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="title">Image URL</label>
                    <input
                        type="text"
                        id="title"
                        className="form-control"
                        value={imageURL}
                        onChange={(e) => setImgURL(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">Add Watch</button>
            </form>
        </div>
    );
}

export default AddWatchForm;
