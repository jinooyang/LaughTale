import {useAuth} from "../stores/useAuth";
import {Navigate, Outlet} from "react-router-dom";

const AuthRoute = ({}) => {
  const {user} = useAuth();

  return user ? <Outlet/> : <Navigate to="/login" />
}
export default AuthRoute;