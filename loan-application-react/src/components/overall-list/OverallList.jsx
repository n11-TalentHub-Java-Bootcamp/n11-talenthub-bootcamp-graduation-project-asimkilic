import React, { useState, useEffect } from "react";
import "./overall-list.scss";
import { data } from "../../constants";
import { AxiosGet } from "../../constants/data";

const icons = [
  <i className="bx bx-receipt"></i>,
  <i className="bx bx-user"></i>,
  <i className="bx bx-cube"></i>,
  <i className="bx bx-dollar"></i>,
];
const OverallList = () => {
  const [value, setValue] = useState(0);
  useEffect(() => {
    calculateValues();
  }, []);

  const calculateValues = () => {
    var salaryArray = [];
    AxiosGet("/").then((res) => {
      res.data.map((item) => {
        salaryArray.push(item.monthlySalary);
      });
      const sum = salaryArray.reduce((a, b) => a + b, 0);
      const avg = sum / salaryArray.length || 0;

      setValue(avg);
    });
  };
  return (
    <>
      <ul className="overall-list">
        <li className="overall-list__item">
          <div className="overall-list__item__icon">{icons[0]}</div>
          <div className="overall-list__item_info">
            <div className="title">Ortalama maaş</div>
            <span>{value.toFixed(2)}₺</span>
          </div>
        </li>
      </ul>
    </>
  );
};

export default OverallList;
