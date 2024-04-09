import {NavLink, useNavigate} from "react-router-dom";
import {useAuth} from "../../stores/useAuth.ts";
import React from "react";
import {BiUser} from "react-icons/bi";
import {useToggle} from "../../stores/toggleStorage.ts";

export default function AfterLoginButton() {
    const navigate = useNavigate();
    const clear = useAuth((state) => state.clear);
    const {userToggle, setUserToggle} = useToggle((state) => state);
    const logout = () => {
        clear();
        navigate("/home");
    }
    return (
        <>
            <div className="md:hidden flex items-center bg-[#73ABE5] text-white">
                <BiUser onClick={() => setUserToggle(!userToggle)}
                        className="w-12 h-12 p-2 hover:bg-slate-500 rounded-md transform"></BiUser>
            </div>
            {userToggle ?
                <ul className="absolute top-[70px] -translate-x-[15px] w-full h-screen md:hidden bg-white">
                    <li className="w-full py-2.5 text-gray-800 font-bold">
                        <div className="flex flex-col text-2xl">
                            <div className="border-b p-6">
                                <NavLink to="/mypage" onClick={() => setUserToggle(!userToggle)}>マイページ : 마이페이지</NavLink>
                            </div>
                            <div className="p-6 border-b hover:text-black hover:cursor-pointer"
                                 onClick={() => logout()}>
                                ログアウト : 로그아웃
                            </div>
                        </div>
                    </li>
                </ul> : null}

            <div className="md:flex hidden flex justify-end">
                <div className="mr-10 text-2xl hover:text-black">
                    <NavLink to="/mypage" >マイページ : 마이페이지</NavLink>
                </div>

                <div className="text-2xl hover:text-black hover:cursor-pointer" onClick={() => logout()}>
                    ログアウト : 로그아웃
                </div>
            </div>
        </>)
}
