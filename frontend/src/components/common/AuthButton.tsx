import {useAuth} from "../../stores/useAuth.ts";
import BeforeLoginButton from "./BeforeLoginButton.tsx";
import AfterLoginButton from "./AfterLoginButton.tsx";

export default function AuthButton(){
  const {user, token} = useAuth();
  return (user && token) ? <BeforeLoginButton/> : <AfterLoginButton/>
}