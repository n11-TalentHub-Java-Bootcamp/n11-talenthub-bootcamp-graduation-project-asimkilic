import React from "react";
import cloud from "../assets/cloud.png";
import styled from "styled-components";

export const Clouds = () => {
  return (
    <CloudContainer>
      <div
        style={{
          display: "flex",
          justifyContent: "flex-end",
        }}
      >
        <img
          src={cloud}
          style={{
            transform: "rotate(180deg)",
          }}
        />
      </div>
      <div>
        <img src={cloud} />
      </div>
    </CloudContainer>
  );
};
const CloudContainer = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: -1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  img {
    width: 60vw;
    height: 30vh;
  }
`;
export default Clouds;
