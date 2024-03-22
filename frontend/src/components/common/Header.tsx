import Logo from '/src/assets/logo.png';

const Header = () => {
    return <div className="bg-[rgba(23,23,26,0.40)] flex justify-center items-center">
        <img src={Logo} height="200" width="170"></img>
    </div>
}

export default Header;