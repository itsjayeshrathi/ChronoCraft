import axios from "axios";
const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.error("API call failed:", error);
    return Promise.reject(error);
  }
);
export default axiosInstance;
