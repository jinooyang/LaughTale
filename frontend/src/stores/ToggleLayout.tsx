import { useLocation } from 'react-router-dom';
import {useEffect} from "react";



export default function ToggleLayout ({children}){
  let location = useLocation();
  useEffect(() => {

  }, [location]);

  return <>{children}</>
}