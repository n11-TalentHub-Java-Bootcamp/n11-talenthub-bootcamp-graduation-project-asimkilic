import React, { useState } from "react";
import styled from "styled-components";
import { AxiosPost } from "../constants/data";
import { toast } from "react-toastify";

const NewCustomer = () => {
  const [trId, setTrId] = React.useState("");
  const [dateOfBirth, setDateOfBirth] = React.useState("");
  const [firstName, setFirstName] = React.useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [montlySalary, setMontlySalary] = useState("");
  const [amountOfGuarantee, setAmountOfGuarantee] = useState("");
  const [primaryPhone, setPrimaryPhone] = useState("");
  const [secondaryPhone, setSecondaryPhone] = useState("");
  const customerHandler = () => {
    //doğrulama yap
    AxiosPost("/", {
      turkishRepublicIdNo: trId,
      firstName: firstName,
      lastName: lastName,
      dateOfBirth: dateOfBirth,
      email: email,
      monthlySalary: montlySalary,
      amountOfGuarantee: amountOfGuarantee,
      primaryPhone: primaryPhone,
      secondaryPhone: secondaryPhone ? null : secondaryPhone,
    })
      .then((res) => {
        console.log(res.data);
        if (res.data.response === "DENIED") {
          toast.error("Kredi başvurunuz reddedildi", {
            position: toast.POSITION.TOP_CENTER,
          });
        } else {
          toast.success(
            `Kredi başvurunuz onaylandı. Limitiniz: ${res.data.creditLimit}`,
            {
              position: toast.POSITION.TOP_CENTER,
            }
          );
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
        <h1>Yeni Müşteri Ekleme ve Kredi Başvurusu</h1>
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
              setFirstName(e.currentTarget.value);
            }}
            type="text"
            value={firstName}
            placeholder="Adınız"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setLastName(e.currentTarget.value);
            }}
            type="text"
            value={lastName}
            placeholder="Soyadınız"
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
              setEmail(e.currentTarget.value);
            }}
            type="text"
            value={email}
            placeholder="Email"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setMontlySalary(e.currentTarget.value);
            }}
            type="text"
            value={montlySalary}
            placeholder="Aylık maaşınız"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setAmountOfGuarantee(e.currentTarget.value);
            }}
            type="text"
            value={amountOfGuarantee}
            placeholder="Teminat miktarı (yok ise boş bırakınız)"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setPrimaryPhone(e.currentTarget.value);
            }}
            type="text"
            value={primaryPhone}
            placeholder="Telefon numaranız (başında sıfır olmadan 10 haneli))"
          />
        </CustomInput>
        <CustomInput>
          <input
            onChange={(e) => {
              setSecondaryPhone(e.currentTarget.value);
            }}
            type="text"
            value={secondaryPhone}
            placeholder="İkinci telefon numaranız (başında sıfır olmadan 10 haneli)"
          />
        </CustomInput>
        <Button onClick={customerHandler}>
          <span>Kaydet ve başvur</span>
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
  width: 300px;
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
export default NewCustomer;
