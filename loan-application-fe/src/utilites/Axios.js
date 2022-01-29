import axios from "axios";

export const Axios = axios.create({
  baseURL: "http://localhost:8080/api/v1/customers",
  headers: {
    "Content-Type": "application/json",
  },
});
export const AxiosPost = (url, data) => Axios.post(url, data);

export const AxiosGet = (url, data) => Axios.get(url, data);
