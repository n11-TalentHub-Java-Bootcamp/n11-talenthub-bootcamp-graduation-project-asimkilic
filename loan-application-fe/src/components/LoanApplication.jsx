import React from "react";
import NavBar from "./NagivationBar/NavBar";
import CustomerResultInquiry from "./Customer/CustomerResultInquiry";
import styled from "styled-components";
import Clouds from "./Clouds/Clouds";

const LoanApplication = () => {
  const Pages = [
    {
      id: 0,
      path: "/",
      components: [
        {
          id: 0,
          name: "Navigation Bar",
          component: <NavBar />,
        },
        {
          id: 1,
          name: "Customer Result Inquiry",
          component: <CustomerResultInquiry />,
        },
        {
          id: 2,
          name: "Clouds",
          component: <Clouds />,
        },
      ],
    },
  ];
  return (
    <PageRenderer
      style={{
        position: "relative",
        zIndex: 0,
      }}
    >
      {Pages[0].components.map((component_) => {
        return component_.component;
      })}
    </PageRenderer>
  );
};

const PageRenderer = styled.div`
  background: linear-gradient(to bottom, #9086f4, #e185f5);
  max-height: 100vh;
  width: 100vw;
  height: 100vh;
`;
export default LoanApplication;
