import KakaoLogo from '../../assets/kakao_logo.png';

export default function BeforeLoginButton() {
    return (
        <a href="https://j10a705.p.ssafy.io/oauth2/authorization/kakao">
            {/*<img src={KakaoLogo} className="w-[30px]"/>*/}
            <div className="text-white text-center text-3xl hover:text-gray-800">ログイン : 로그인</div>
        </a>
    )
}