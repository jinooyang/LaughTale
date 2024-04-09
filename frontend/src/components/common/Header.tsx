import React, {useEffect} from 'react';
import Logo from '../../assets/logo.png';
import {Link, useLocation} from "react-router-dom";
import BlueAuthButton from "./BlueAuthButton.tsx";
import {useAuth} from "../../stores/useAuth.ts";
import {Role} from "../../constants/Role.ts";
import {BsList} from "react-icons/bs";
import {useToggle} from "../../stores/toggleStorage.ts";

type Props = {
    color?: Color;
}
const Header = ({color}: Props) => {
    const user = useAuth(state => state.user);
    const {isToggled, setIsToggle, setUserToggle} = useToggle(state => state);

    let location = useLocation();
    useEffect(() => {
        setIsToggle(false);
        setUserToggle(false);
    }, [location]);

    return (
        <div className="sticky top-0 z-50 w-[100%] text-white laughtale-font">
            <div className="md:hidden flex justify-between px-6 items-center w-full h-[70px] bg-[#73ABE5] text-white">
                <BsList className="w-12 h-12 p-2 hover:bg-slate-500 rounded-md transform"
                        onClick={() => setIsToggle(!isToggled)}/>
                    <Link to="/main">
                        <img src={Logo} alt="Logo" height="200" width="150"></img>
                    </Link>

                <BlueAuthButton/>
            </div>
            {isToggled ? <ul className="block overflow-auto md:hidden w-full h-screen p-6 bg-[#73ABE5]">
                <li className="w-full border-b p-6text-white font-bold">
                    <Link to={`/home`}>
                        만화읽기
                    </Link>
                </li>
                <li className="w-full border-b p-6 text-2xl text-white font-bold">
                    {(user?.role === Role.TEMPORARY_USER) &&
                        <Link to={`/analyze`}>
                            만화분석
                        </Link>}
                </li>
                <li className="w-full border-b p-6 text-2xl text-white font-bold">
                    {user?.role == Role.ADMIN && <Link to={`/admin`}>
                        관리자페이지
                    </Link>}
                </li>
            </ul> : null}

            <div className="md:flex hidden bg-[#73ABE5] flex justify-center items-center px-10 relative h-[70px]">
                <div className="items-center">
                    <Link to={`/home`} className="text-3xl  hover:text-gray-800">
                        만화읽기
                    </Link>
                    {(user?.role === Role.TEMPORARY_USER) &&
                        <Link to={`/analyze`} className="ml-10 text-3xl  hover:text-gray-800">
                            만화분석
                        </Link>}
                    {user?.role == Role.ADMIN && <Link to={`/admin`} className="ml-10 text-3xl hover:text-gray-800">
                        관리자페이지
                    </Link>}
                </div>

                <div className="-translate-y-2 center">
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
