import {useAuth} from "../../stores/useAuth.ts";
import BeforeLoginButton from "./BeforeLoginButton.tsx";
import AfterLoginButton from "./AfterLoginButton.tsx";
import useAuthLocalStorage from "../../stores/useAuthLocalStorage.ts";

export default function AuthButton(){
  const {token } = useAuth();
  return token===null  ? <BeforeLoginButton/> : <AfterLoginButton/>
}