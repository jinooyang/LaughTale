import {useQuery, useSuspenseQuery} from "@tanstack/react-query";
import {getMyInfo} from "../../apis/auth.ts";
import {Navigate, Outlet} from "react-router-dom";
import Spinner from "../../components/common/Spinner.tsx";
type Props = {
  accessToken?: string;
}
const UserInfoFetcher = (props : Props) => {
  const {data} = useSuspenseQuery({
    queryKey: ["auth"],
    queryFn:() => getMyInfo({accessToken: props}),
    retry: 0,
  });

  return <div>tset</div>;
}
export default UserInfoFetcher;