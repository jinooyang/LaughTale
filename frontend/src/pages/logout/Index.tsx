import {Navigate} from "react-router-dom";
import {useAuth} from "../../stores/useAuth.ts";

export default function Index(){

  return <Navigate to="/home"/>
}