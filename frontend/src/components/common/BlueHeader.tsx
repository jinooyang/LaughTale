import React from 'react';
import Logo from '../../assets/Logo4.png';
import {Link} from "react-router-dom";
import BlueAuthButton from "./BlueAuthButton.tsx";
import {useAuth} from "../../stores/useAuth.ts";
import {Role} from "../../constants/Role.ts";
import {BsList} from "react-icons/bs";
import {useToggle} from "../../stores/toggleStorage.ts";

type Props = {
    color?: Color;
}
const Header = ({color}: Props) => {
    const menu = ['만화 읽기', '만화 분석']
    const menuLink = ['/home', '/analyze']
    const user = useAuth(state => state.user);
    const isToggled = useToggle(state => state.isToggled);
    const setIsToggle = useToggle(state => state.setIsToggle);

    return (
        <div className="sticky top-0 z-50 w-[100%] text-white laughtale-font">
            <div className="md:hidden flex justify-between px-6 items-center w-full h-[70px] bg-[#73ABE5] text-white">
                <BsList className="w-12 h-12 p-2 hover:bg-slate-500 rounded-md transform"
                        onClick={() => setIsToggle(!isToggled)}/>
                <Link to="/main" >
                    <img src={Logo} alt="Logo" height="200" width="150"></img>
                </Link>
                <BlueAuthButton/>
            </div>
            {isToggled ? <ul className="block overflow-auto md:hidden w-full h-screen p-6 bg-[#73ABE5]">
                {menu.map((menu, idx) => (
                    <li className="w-full.5 border-b p-6 text-2xl text-white font-bold">{<Link
                        to={menuLink[idx]} onClick={() => setIsToggle(!isToggled)} >{menu}</Link>}</li>
                ))}
                {user?.role == Role.ADMIN &&
                    <li className="w-full p-6 text-white font-bold bg-gray-400">
                        <Link to={`/admin`} className="ml-10 text-2xl hover:text-gray-800">
                            관리자페이지
                        </Link>
                    </li>}
            </ul> : null}

            <div className="md:flex hidden bg-[#73ABE5] flex justify-center items-center px-10 relative h-[70px]">
                <div className="absolute left-10 items-center">

                    <Link to={`/home`}  className="text-3xl  hover:text-gray-800">
                        만화읽기
                    </Link>
                    {(user?.role === Role.TEMPORARY_USER )&& <Link to={`/analyze`} className="ml-10 text-3xl  hover:text-gray-800">
                        만화분석
                    </Link>}
                    {user?.role == Role.ADMIN && <Link to={`/admin`} className="ml-10 text-3xl hover:text-gray-800">
                        관리자페이지
                    </Link>}
                </div>

                <div className="flex justify-center items-center">
                    <Link to="/main">
                        <img src={Logo} alt="Logo" height="200" width="170"></img>
                    </Link>
                </div>
                <div className="absolute right-10">
                    <BlueAuthButton/>
                </div>
            </div>
        </div>
    )
}

export default Header;
