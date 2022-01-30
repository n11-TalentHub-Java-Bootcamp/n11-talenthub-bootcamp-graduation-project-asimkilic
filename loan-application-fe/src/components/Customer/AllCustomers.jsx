import React from "react";
import styled from "styled-components";
import { Arrow90degRight } from "styled-icons/bootstrap";
import { Axios, AxiosPost, AxiosGet } from "../../utilites/Axios";
import BotImage from "../assets/Bot.png";
//import axios from "axios";

const AllCustomers = () => {
  const [trId, setTrId] = React.useState("");
  const [dateOfBirth, setDateOfBirth] = React.useState("");
  const [modal, setModalState] = React.useState(false);
  const [data, setData] = React.useState({
    message: "Data yüklendi",
    status: false,
  });
  const customerHandler = () => {
    if (trId === "" || dateOfBirth === "") {
      window.alert("boş bırama ");
    }
    AxiosPost("/credit", {
      turkishRepublicIdNo: trId,
      dateOfBirth: dateOfBirth,
    })
      .then((res) => {
        console.log(res.data);
        window.alert(res.data.response);

        creditResponseHandler();
      })
      .catch((err) => console.log(err.response))
      .finally(setTrId(""), setDateOfBirth(""));
  };
  const creditResponseHandler = () => {
    AxiosGet("/").then((res) => {
      console.log(res.data);
    });
  };
  return (
    <PaddingBox>
      <div style={{ flex: 1 }}>
        <h1>n11 Bank</h1>
        <pre>
          Sonucunuzu hızlıca öğrenin.
          <Arrow90degRight />
        </pre>
        <CustomInput>
          <input
            onChange={(e) => {
              setTrId(e.currentTarget.value);
            }}
            type="text"
            value={trId}
            placeholder="Türkiye Cumhuriyeti Kimlik Numaranız"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setDateOfBirth(e.currentTarget.value);
            }}
            type="text"
            placeholder="Doğum Tarihiniz (1990-01-29)"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setDateOfBirth(e.currentTarget.value);
            }}
            type="text"
            placeholder="Doğum Tarihiniz (1990-01-29)"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setDateOfBirth(e.currentTarget.value);
            }}
            type="text"
            placeholder="Doğum Tarihiniz (1990-01-29)"
          />
        </CustomInput>
        <Button onClick={customerHandler}>
          <span>Sorgula</span>
        </Button>
      </div>
      <RightSide>
        <img src={BotImage} alt="" />
      </RightSide>
    </PaddingBox>
  );
};
const RightSide = styled.div`
  flex: 0.5;
  padding-right: 30px;
  padding-top: 80px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  img {
    width: 100%;

    transform: scale(1.2);
    object-fit: containt;
  }
`;
const Button = styled.button`
  border: none;
  border-radius: 180px;
  outline: none;
  background: var(--primary);
  width: 440px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  margin-top: 40px;
  color: white;
  font-size: 25px;
`;
const CustomInput = styled.div`
  background: white;
  padding: 20px 20px;
  margin-bottom: 20px;
  width: 400px;
  border-radius: 180px;
  input {
    font-size: 15px;
    width: 100%;
    border: none;
    outline: none;
    background: transparent;
  }
`;
const PaddingBox = styled.div`
  padding-left: 100px;
  padding-right: 100px;
  display: flex;
  h1 {
    color: white;
  }
  pre {
    color: white;
    font-size: 15px;
    position: relative;
    width: 400px;
    white-space: pre-wrap;
    height: 50px;
    svg {
      // position: absolute;
      // bottom: 0;
      // right: 0;
      width: 40px;
      margin-left: 10px;
      transform: rotate(90deg);
    }
  }
`;

export default AllCustomers;
