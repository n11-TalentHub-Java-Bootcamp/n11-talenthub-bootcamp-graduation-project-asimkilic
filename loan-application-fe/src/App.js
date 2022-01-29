import logo from "./logo.svg";
import "./App.css";
import LoanApplication from "./components/LoanApplication";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoanApplication />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
