import { useState, useEffect } from "react";
import axios from "../../services/axios.js";
const OrderManagement = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get("/api/admin/placedOrders");
        setOrders(response.data);
      } catch (error) {
        console.error("Error fetching orders:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, []);

  const handleStatusChange = async (orderId, newStatus) => {
    try {
      await axios.post(`/api/admin/order/${orderId}/${newStatus}`);
      setOrders(
        orders.map((order) =>
          order.id === orderId ? { ...order, status: newStatus } : order
        )
      );
    } catch (error) {
      console.error("Error changing order status:", error);
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Order Management</h1>
      <table className="min-w-full bg-white border border-gray-300">
        <thead>
          <tr>
            <th className="border-b px-4 py-2">Order ID</th>
            <th className="border-b px-4 py-2">Customer</th>
            <th className="border-b px-4 py-2">Status</th>
            <th className="border-b px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.id}>
              <td className="border-b px-4 py-2">{order.id}</td>
              <td className="border-b px-4 py-2">{order.customerName}</td>
              <td className="border-b px-4 py-2">{order.status}</td>
              <td className="border-b px-4 py-2">
                <button
                  onClick={() => handleStatusChange(order.id, "PROCESSING")}
                  className="bg-yellow-500 text-white px-4 py-2 rounded mr-2"
                >
                  Processing
                </button>
                <button
                  onClick={() => handleStatusChange(order.id, "SHIPPED")}
                  className="bg-blue-500 text-white px-4 py-2 rounded mr-2"
                >
                  Shipped
                </button>
                <button
                  onClick={() => handleStatusChange(order.id, "DELIVERED")}
                  className="bg-green-500 text-white px-4 py-2 rounded"
                >
                  Delivered
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OrderManagement;
