import React, { useState } from "react";
import NavBar from "./NagivationBar/NavBar";
import CustomerResultInquiry from "./Customer/CustomerResultInquiry";
import styled from "styled-components";
import Clouds from "./Clouds/Clouds";
import AllCustomers from "./Customer/AllCustomers";
import { useParams, useSearchParams } from "react-router-dom";

const LoanApplication = () => {
  const [pageId, setPageId] = useState(0);

  const { code } = useParams();
  const [veri, setVeri] = useState([]);
  React.useEffect(() => {
    setPageId(code);
    console.log(code);
  }, []);

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
    {
      id: 1,
      path: "/allcustomers",
      components: [
        {
          id: 0,
          name: "Navigation Bar",
          component: <NavBar />,
        },
        {
          id: 1,
          name: "All customers",
          component: <AllCustomers />,
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
      {Pages[pageId === undefined ? 0 : pageId].components.map((component_) => {
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
