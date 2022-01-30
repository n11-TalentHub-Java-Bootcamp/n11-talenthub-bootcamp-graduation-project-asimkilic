import React, { useState } from "react";
import { useParams, useSearchParams } from "react-router-dom";
import { Axios, AxiosGet } from "../../utilites/Axios";

const RouteMe = () => {
  const { code } = useParams();
  const [veri, setVeri] = useState([]);
  React.useEffect(() => {
    AxiosGet("/").then((res) => {
      setVeri(res.data);
      console.log(res.data);
    });
  }, []);
  return <div></div>;
};
export default RouteMe;
