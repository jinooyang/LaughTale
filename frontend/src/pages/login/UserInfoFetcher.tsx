import {useQuery} from "@tanstack/react-query";
import {getMyInfo} from "../../apis/auth.ts";
import {Navigate} from "react-router-dom";
type Props = {
  accessToken?: string;
}
const UserInfoFetcher = (props : Props) => {
  const {data, isLoading, isError} = useQuery(["test"], () => getMyInfo({accessToken: props}));
  console.log("UserInfoFetcher")
  return <Navigate to="/home" />
}
export default UserInfoFetcher;