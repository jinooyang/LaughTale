import {Role} from "../../constants/Role.ts";
import {Link, Navigate} from "react-router-dom";
import {useAuth} from "../../stores/useAuth.ts";
import {CSSProperties, ReactNode} from "react";
import useModal from "../../hooks/useModal.ts";
import {ThemeProvider} from "@material-tailwind/react";
import children = ThemeProvider.propTypes.children;

type Props = {
  to:string;
  roles?:Role[];
  display?:boolean;
  children?: ReactNode;
  style?:CSSProperties;
  className?: string
}

export default function AuthLink(props: Props){
  const user = useAuth(state => state.user);
  const {toggle, isOpen} = useModal(state => state);
  const isAccess = props?.roles?.includes(user?.role);
  const deinedFn = (e) => {
    if(!isAccess) {
      e.preventDefault();
      toggle();
    }
  }

  return <Link to={props.to} style={props.style} onClick={deinedFn} className={props.className}>
    {props.children}
  </Link>
}