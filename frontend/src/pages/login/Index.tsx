import {useSearchParams} from "react-router-dom";
import LoginPostProcessComponent from "./LoginProcessComponent.tsx";
import NotLoginPage from "./NotLogin.tsx";


const Login  = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  console.log(searchParams.get("accessToken"));
  return <div>
    {
      searchParams.get("accessToken") ? <LoginPostProcessComponent accessToken={searchParams.get("accessToken")}/> :<NotLoginPage/>
    }
  </div>
}
export default Login;