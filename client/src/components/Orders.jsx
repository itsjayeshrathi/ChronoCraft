import React , {useContext}from "react";
import AuthContext  from "../context/AuthContext";
import axios from "axios";  
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useState } from "react";

const Showorders=()=>{
   let navigate = useNavigate();
    const {userId}=useContext(AuthContext);
    const intval=parseInt(userId);
const handlePlaceOrder=()=>{
    const resp=axios.post("http://localhost:8080/orders/order?userId="+intval)
    .then(response => {
        console.log(response.data);
        alert("order is placed...!");
        navigate('/orderslist');
    })
    .catch(error => {
        console.error(error);
    });
    return resp.data;
}

const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    useEffect(() => {
        // Define the async function inside the useEffect to fetch user data
        const fetchUser = async () => {
            try {
                setLoading(true);
                const response = await axios.get(`http://localhost:8080/users/${userId}`);
                setUser(response.data);
            } catch (error) {
                setError('User not found');
            } finally {
                setLoading(false);
            }
        };

        // Call the fetch function
        fetchUser();
    }, [userId]); // Dependency array includes userId

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;
    return(
        <div>
              <p>Customer Name:{`${user.firstName} ${user.lastName}`}</p>
              <br />
              <p>Address: {`${user.street} , ${user.city}, ${user.zipcode}`}</p>
              <br />
              <button onClick={handlePlaceOrder}>Place Order</button>
              <br />
              <p>{}</p>
        </div>
      
    );
}

export default Showorders;