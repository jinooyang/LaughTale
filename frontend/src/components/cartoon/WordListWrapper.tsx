import {ReactNode, useEffect} from "react";
import {ThemeProvider} from "@material-tailwind/react";
import children = ThemeProvider.propTypes.children;

type Props = {
  children: ReactNode;
}
export default function WordListWrapper(props:Props){

  useEffect(() => {

  }, []);
  return <div className="pl-5 pr-5 break-all overflow-auto sticky top-[70px] pb-5 " style={{maxHeight: "100vh"}}>
      {props.children}
  </div>
}