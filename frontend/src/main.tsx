import ReactDOM from 'react-dom/client'
import { RouterProvider } from "react-router-dom";
import router from "./routes";
import "./index.css"
import './styles/SliderStyles.css';
ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <RouterProvider router={router} />
);
