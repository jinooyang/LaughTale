


// const url = "http://70.12.247.51:4000/oauth2/authorization/kakao"
const url = "http://localhost:5173/login?accessToken=abcd";

const NotLoginPage = () => {
  return <div>
    <a href={url} >Login</a>
  </div>
}

export default NotLoginPage;