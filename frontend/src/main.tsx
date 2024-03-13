import ReactDOM from 'react-dom/client'
import { RouterProvider } from "react-router-dom";
import {router} from "../src/routes/index.tsx";
import "./index.css"
import './styles/SliderStyles.css';
ReactDOM.createRoot(document.getElementById("root")!).render(
  <RouterProvider router={router} />
);

