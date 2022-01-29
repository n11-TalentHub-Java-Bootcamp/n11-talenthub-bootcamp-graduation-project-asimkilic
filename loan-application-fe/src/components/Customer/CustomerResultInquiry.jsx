import React from "react";
import styled from "styled-components";
import { Arrow90degRight } from "styled-icons/bootstrap";
import BotImage from "../assets/Bot.png";

const CustomerResultInquiry = () => {
  return (
    <PaddingBox>
      <div style={{ flex: 1 }}>
        <h1>n11 Bank</h1>
        <pre>
          Sonucunuzu hızlıca öğrenin.
          <Arrow90degRight />
        </pre>
        <CustomInput>
          <input type="text" placeholder="TCKN giriniz" />
          {/* <input type="text" placeholder="Doğum tarihi giriniz (yyyy-mm-dd)" /> */}
        </CustomInput>
        <Button>
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

export default CustomerResultInquiry;
