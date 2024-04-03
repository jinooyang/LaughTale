import {useAuth} from "../../stores/useAuth.ts";
import BlueBeforeLoginButton from "../common/BlueBeforeLoginButton.tsx";
import BlueAfterLoginButton from "../common/BlueAfterLoginButton.tsx";
export default function AuthButton(){
  const {token } = useAuth();
  return token===null  ? <BlueBeforeLoginButton/> : <BlueAfterLoginButton/>
}