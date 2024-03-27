import {useAuth} from "../../stores/useAuth.ts";
import BeforeLoginButton from "./BeforeLoginButton.tsx";
import AfterLoginButton from "./AfterLoginButton.tsx";

export default function AuthButton(){
  const {user, token} = useAuth();
  console.log(user ===null && token === null);
  return (user ===null && token === null) ? <BeforeLoginButton/> : <AfterLoginButton/>
}