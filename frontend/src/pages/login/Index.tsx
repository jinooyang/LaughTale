import {useSearchParams} from "react-router-dom";
import LoginPostProcessComponent from "./LoginProcessComponent.tsx";
import NotLoginPage from "./NotLogin.tsx";
import {TOKEN_PARAMETER} from "../../constants/TokenParameter.ts";
import {Suspense} from "react";


const Login  = () => {
  const [searchParams, ] = useSearchParams();
  return searchParams.get(TOKEN_PARAMETER) ? <LoginPostProcessComponent accessToken={searchParams.get(TOKEN_PARAMETER)}/>
  :<NotLoginPage/>
}
export default Login;