import "./App.css";
import { useSelector } from "react-redux";
import Main from "./pages/main/Main";
import { BrowserRouter } from "react-router-dom";


function App() {
  const token = useSelector((state) => state.auth?.token);

  return (
    <div className="App">
      <BrowserRouter>
        <Main />
      </BrowserRouter>
    </div>
  );
}

export default App;
