import {CSSProperties, ReactNode} from "react";

type Props = {
  flex: string;
  children?: ReactNode,
  style?: CSSProperties
}
export default function FlexItem(props: Props){
  return <div style={{flex:props.flex, ...props.style}}>{props.children}</div>
}