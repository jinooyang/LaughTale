import {ReactNode, Suspense} from "react";
import Spinner from "../../components/common/Spinner.tsx";

type Props = {
  children: ReactNode
}
const LoginFetchingSuspense = ({children} : Props) => {
  return <Suspense fallback={<Spinner/>} >
    {children}
  </Suspense>
}

export default LoginFetchingSuspense;