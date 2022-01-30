import React from "react";
import styled from "styled-components";
import { AxiosPost } from "../constants/data";
import { toast } from "react-toastify";
const CreditInquiry = () => {
  const [trId, setTrId] = React.useState("");
  const [dateOfBirth, setDateOfBirth] = React.useState("");

  const customerHandler = () => {
    if (trId === "" || dateOfBirth === "") {
      toast.error("Tüm alanları doldurunuz", {
        position: toast.POSITION.TOP_CENTER,
      });
      return;
    }
    AxiosPost("/credit", {
      turkishRepublicIdNo: trId,
      dateOfBirth: dateOfBirth,
    })
      .then((res) => {
        if (res.data.response === "DENIED") {
          toast.error(res.data.response, {
            position: toast.POSITION.TOP_CENTER,
          });
        } else {
          toast.success(res.data.response, {
            position: toast.POSITION.TOP_CENTER,
          });
        }
      })
      .catch((err) => {
        console.log(err.response);
        toast.error(err.response.data.errors[0], {
          position: toast.POSITION.TOP_CENTER,
        });
      });
  };

  return (
    <PaddingBox>
      <div style={{ flex: 1 }}>
        <h1>n11 Bank</h1>
        <pre>Sonucunuzu hızlıca öğrenin.</pre>
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
        <Button onClick={customerHandler}>
          <span>Sorgula</span>
        </Button>
      </div>
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
  background: purple;
  width: 220px;
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
  width: 300px;
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
  padding-left: 25px;
  padding-right: 25px;
  display: flex;
  h1 {
    color: white;
  }
  pre {
    color: white;
    font-size: 15px;
    position: relative;
    width: 200px;

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
export default CreditInquiry;
