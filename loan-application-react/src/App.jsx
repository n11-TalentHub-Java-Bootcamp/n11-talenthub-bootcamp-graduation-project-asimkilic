import "./assets/libs/boxicons-2.1.1/css/boxicons.min.css";
import "./scss/App.scss";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Blank from "./pages/Blank";
import Dashboard from "./pages/Dashboard";
import MainLayout from "./layout/MainLayout";
import CreditInquiry from "./pages/CreditInquiry";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import NewCustomer from "./pages/NewCustomer";
import DeleteCustomer from "./pages/DeleteCustomer";
import ApplyCredit from "./pages/ApplyCredit";
function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="credit-inquiry" element={<CreditInquiry />} />
          <Route path="new-customer" element={<NewCustomer />} />
          <Route path="delete-customer" element={<DeleteCustomer />} />
          <Route path="apply-credit" element={<ApplyCredit />} />
          <Route path="*" element={<Blank />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
