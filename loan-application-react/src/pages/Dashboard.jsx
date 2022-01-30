import React, { useEffect, useState } from "react";
import { Bar } from "react-chartjs-2";
import Box from "../components/box/Box";
import DashboardWrapper, {
  DashboardWrapperMain,
  DashboardWrapperRight,
} from "../components/dashboard-wrapper/DashboardWrapper";
import SummaryBox, {
  SummaryBoxSpecial,
} from "../components/summary-box/SummaryBox";
import { data, colors } from "../constants";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import OverallList from "../components/overall-list/OverallList";
import RevenueList from "../components/revenue-list/RevenueList";
import { AxiosPost, AxiosGet } from "../constants/data";
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  BarElement,
  Title,
  Tooltip,
  Legend
);
const Dashboard = () => {
  const [summary, setSummary] = useState([]);
  useEffect(() => {
    customerHandler();
  }, []);
  const [values, setValues] = useState([]);

  const calculateValues = () => {
    var salaryArray = [];
    summary.map((item) => {
      salaryArray.push(item.monthlySalary);
    });
    var average = salaryArray.reduce(function (avg, value, _, { length }) {
      return avg + value / length;
    }, 0);
    setValues({
      title: "Ortalama maaş",
      value: average,
    });
    console.log(average);
  };

  const customerHandler = () => {
    AxiosGet("/").then((res) => {
      const summaryTemp = [];
      res.data.map((item) => {
        summaryTemp.push({
          title: item.firstName + " " + item.lastName,
          subtitle: item.email,
          value: item.monthlySalary + "₺",
          turkishRepublicIdNo: item.turkishRepublicIdNo,
          dateOfBirth: item.dateOfBirth,
          amountOfGuarantee: item.amountOfGuarantee,
          primaryPhone: item.primaryPhone,
          secondaryPhone: item.secondaryPhone,
        });
      });
      setSummary(summaryTemp);
    });
    calculateValues();
  };
  return (
    <DashboardWrapper>
      <DashboardWrapperMain>
        <div className="row">
          <div className="col-12">
            <h1>MÜŞTERİLER</h1>
          </div>
        </div>
        <div className="row">
          <div className="col-12 col-md-12">
            <div className="row">
              {summary.map((item, index) => (
                <div
                  key={`summary-${index}`}
                  className="col-6 col-md-6 col-sm-12 mb"
                >
                  <SummaryBox item={item} />
                </div>
              ))}
            </div>
          </div>
        </div>
      </DashboardWrapperMain>
      <DashboardWrapperRight>
        <div className="title mb"></div>
        <div className="mb">
          <OverallList values={values} />
        </div>
      </DashboardWrapperRight>
    </DashboardWrapper>
  );
};

export default Dashboard;

const RevenueByMonthsChart = () => {
  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      xAxes: {
        grid: {
          display: false,
          drawBorder: false,
        },
      },
      yAxes: {
        grid: {
          display: false,
          drawBorder: false,
        },
      },
    },
    plugins: {
      legend: {
        display: false,
      },
      title: {
        display: false,
      },
    },
    elements: {
      bar: {
        backgroundColor: colors.orange,
        borderRadius: 20,
        borderSkipped: "bottom",
      },
    },
  };
  const chartData = {
    labels: data.revenueByMonths.labels,
    datasets: [
      {
        label: "Revenue",
        data: data.revenueByMonths.data,
      },
    ],
  };
  return (
    <>
      <div className="title mb">Revenue by months</div>
      <div>
        <Bar options={chartOptions} data={chartData} height={`300px`} />
      </div>
    </>
  );
};
