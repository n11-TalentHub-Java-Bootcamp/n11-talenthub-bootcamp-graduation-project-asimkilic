import React from "react";
import "./summary-box.scss";
import Box from "../box/Box";
import {
  buildStyles,
  CircularProgressbarWithChildren,
} from "react-circular-progressbar";
import { colors } from "../../constants";
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJs,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
ChartJs.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);
const SummaryBox = ({ item }) => {
  return (
    <Box>
      <div className="summary-box">
        <div className="summary-box__info">
          <div className="summary-box__info__title">
            <div>Ad Soyad: {item.title}</div>
            <span>TCKN: {item.turkishRepublicIdNo}</span>
            <br />
            <span>Doğum Tarihi: {item.dateOfBirth}</span>
            <br />
            <span>Email: {item.subtitle}</span>
            <br />
            <span>Teminat: {item.amountOfGuarantee}</span>
            <br />
            <span>Telefon: {item.primaryPhone}</span>
            <br />
            <span>Telefon-2: {item.secondaryPhone}</span>
            <br />
          </div>
          <div className="summary-box__info__value">Maaş: {item.value}</div>
        </div>
        <div className="summary-box__chart"></div>
      </div>
    </Box>
  );
};

export default SummaryBox;

export const SummaryBoxSpecial = ({ item }) => {
  const chartOptions = {
    responsive: true,
    scales: {
      xAxis: {
        display: false,
      },
      yAxis: {
        display: false,
      },
    },
    plugins: {
      legend: {
        display: false,
      },
    },
    elements: {
      point: {
        radius: 0,
      },
    },
  };
  const chartData = {
    labels: item.chartData.labels,
    datasets: [
      {
        label: "Revenue",
        data: item.chartData.data,
        borderColor: "#fff",
        tension: 0.5,
      },
    ],
  };
  return (
    <Box purple fullheight>
      <div className="summary-box-special">
        <div className="summary-box-special__title">{item.title}</div>
        <div className="summary-box-special__value">{item.value}</div>
        <div className="summary-box-special__chart">
          <Line options={chartOptions} data={chartData} width={`250px`} />
        </div>
      </div>
    </Box>
  );
};
