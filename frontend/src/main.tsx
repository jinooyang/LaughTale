import ReactDOM from 'react-dom/client'
import { RouterProvider } from "react-router-dom";
import router from "./routes";
import "./index.css"
import './styles/SliderStyles.css';
import './styles/fontstyle.css';
import { QueryClient } from '@tanstack/react-query';
import App from "./App.tsx";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(<App/>);
