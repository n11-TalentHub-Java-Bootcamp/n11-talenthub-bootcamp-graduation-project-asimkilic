import React from "react";
import styled from "styled-components";
import { Home, InfoCircle, Money } from "styled-icons/fa-solid";
import { AttachMoney } from "styled-icons/material";

const NavBar = () => {
  return (
    <div>
      <NavBarContent>
        <h1>n11 Loan Application</h1>
        <ul>
          <li>
            <Home />
            Anasayfa
          </li>
          <li>
            <AttachMoney />
            Kredi Başvuru
          </li>
        </ul>
      </NavBarContent>
    </div>
  );
};

const NavBarContent = styled.div`
  background: transparent;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px;
  padding-top: 10px;
  h1 {
    font-size: 20px;
    color: white;
  }

  ul {
    list-style: none;
    display: flex;
    li {
      padding: 10px;
      font-size: 20px;
      font-weigth: 700;
      color: White;
      display: flex;
      align-items: center;
      svg {
        width: 20px;
        margin-right: 5px;
      }
    }
  }
`;
export default NavBar;
