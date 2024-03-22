import useLocalStorage from "../../stores/useLocalStorage.ts";
import useAuthLocalStroage from "../../stores/useAuthLocalStroage.ts";

type Props = {
  accessToken:string;
}
const LoginPostProcessComponent = ({accessToken} : Props) => {
    //
    const authStorage = useAuthLocalStroage();


    // authStorage.set({accessToken});accessToken

    return <div>accessToen  </div>
}
export default LoginPostProcessComponent;