import images from "./images";
import axios from "axios";
export const Axios = axios.create({
  baseURL: "http://localhost:8080/api/v1/customers",
  headers: {
    "Content-Type": "application/json",
  },
});

export const AxiosPost = (url, data) => Axios.post(url, data);
export const AxiosGet = (url, data) => Axios.get(url, data);
export const AxiosDelete = (url, data) =>
  axios.delete(url, {
    data: { turkishRepublicIdNo: data.turkishRepublicIdNo },
    headers: { "Content-Type": "application/json" },
  });
const data = {
  user: {
    name: "Abdullah Asım KILIÇ",
    img: images.avt,
  },
  summary: [
    {
      title: "Title",
      subtitle: "subtitle",
      value: "$1.000",
      percent: 70,
    },
  ],
  revenueSummary: {
    title: "Revenue",
    value: "$678",
    chartData: {
      labels: ["May", "Jun", "July", "Aug", "May", "Jun", "July", "Aug"],
      data: [300, 300, 280, 380, 200, 300, 280, 350],
    },
  },
  overall: [
    {
      value: "300K",
      title: "Credits",
    },
  ],
  revenueByChannel: [
    {
      title: "Direct",
      value: 70,
    },
    {
      title: "External search",
      value: 40,
    },
    {
      title: "Referal",
      value: 60,
    },
    {
      title: "Social",
      value: 30,
    },
  ],
  revenueByMonths: {
    labels: [
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "July",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
      "Jan",
    ],
    data: [250, 200, 300, 280, 100, 220, 310, 190, 200, 120, 250, 350],
  },
};

export default data;
