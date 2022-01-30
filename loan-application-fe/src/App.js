import logo from "./logo.svg";
import "./App.css";
import LoanApplication from "./components/LoanApplication";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import RouteMe from "./components/RouteMe/RouteMe";
import NoMatch from "./components/NoMatch/NoMatch";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoanApplication />} />
          <Route path="/:code" element={<LoanApplication />} />
          <Route path="*" element={<NoMatch />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
// - / (GET) => returns all customers  List< CustomerDto >
// - / (POST) => saves new custoer CustomerSaveRequestDto
// - / (PUT) => udpates customer customerUpdateRequestDto
// - / (DELETE) => deletes customer  CustomerDeleteRequestDto
// - /id/{id} (GET) => returns customer  String id
// - /trid/{trid} (GET) => returns customer string trid
// - /credit (POST) => credit result CreditResultRequestDto
