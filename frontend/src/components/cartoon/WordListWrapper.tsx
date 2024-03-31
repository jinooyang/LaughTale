import {ReactNode} from "react";
import {ThemeProvider} from "@material-tailwind/react";
import children = ThemeProvider.propTypes.children;

type Props = {
  children: ReactNode;
}
export default function WordListWrapper(props:Props){
  return <div className=" top-[15%]  sticky p-10 break-all overflow-auto" style={{maxHeight:"80vh"}}>
    {props.children}
  </div>
}