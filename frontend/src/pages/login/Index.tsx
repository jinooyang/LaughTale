import {Navigate, useSearchParams} from "react-router-dom";
import LoginPostProcessComponent from "./LoginProcessComponent.tsx";
import {TOKEN_PARAMETER} from "../../constants/TokenParameter.ts";


const Login  = () => {
  const [searchParams, ] = useSearchParams();
 if(searchParams.get(TOKEN_PARAMETER)) return <LoginPostProcessComponent accessToken={searchParams.get(TOKEN_PARAMETER)}/>
  return <Navigate to={`/home`} />
 }
export default Login;