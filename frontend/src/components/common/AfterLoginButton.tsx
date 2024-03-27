import {NavLink} from "react-router-dom";

export default function AfterLoginButton() {
  return <>
    <NavLink to="/mypage">mypage</NavLink> <NavLink to="/logout" >logout</>
}
