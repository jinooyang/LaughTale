import Logo from '../../assets/logo.png';

const Header = () => {
    return <div className="bg-[rgba(83,82,95,0.23)] flex justify-center items-center">
        <img src={Logo} height="200" width="170"></img>
    </div>
}

export default Header;