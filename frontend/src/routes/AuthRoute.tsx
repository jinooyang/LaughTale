import {useAuth} from "../stores/useAuth";
import {Navigate, Outlet} from "react-router-dom";

const AuthRoute = () => {
  const token = useAuth((state) => state.token);

  return token ? <Outlet/> : <Navigate to="/login" />
}
export default AuthRoute;