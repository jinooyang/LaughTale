import {Navigate} from "react-router-dom";
import {useAuth} from "../../stores/useAuth.ts";

export default function Index(){
  const {clear, setUser} = useAuth();

  clear();
  setUser();

  return <Navigate to="/home"/>
}