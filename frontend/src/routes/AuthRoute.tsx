import {useAuth} from "../stores/useAuth";
import {Navigate, Outlet} from "react-router-dom";
import {Role} from "../constants/Role.ts";
type Props = {
  roles: Array<Role>
}

const AuthRoute = (props: Props) => {
  const user = useAuth((state) => state.user);
  const token = useAuth((state) => state.token);
  return props?.roles.includes(user?.role) ? <Outlet/> : <Navigate to="/home" />
}
export default AuthRoute;