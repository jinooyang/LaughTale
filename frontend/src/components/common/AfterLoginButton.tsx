import {NavLink} from "react-router-dom";

export default function AfterLoginButton() {
  return (
      <>
      <div className="text-lg mr-3">
        <NavLink to="/mypage">마이페이지</NavLink>
      </div>

      <div className="text-lg">
        <NavLink to="/logout" >로그아웃</NavLink>
      </div>
  </>)
}
