import {Position, SpeechBubble} from "../../../types";
import absoluteToPercent from "../../utils/position.ts";
type Pos = {
  x: number;
  y: number;
}
type Props ={
  leftTop: Pos;
  rightBottom :Pos;
  onClick: Function;
}

export default function SpeechBubbleBox(props : Props){

  return <div className="hoverBox" onClick={() =>props.onClick()} style={{
    position: "absolute",
    left: `${props.leftTop.x}%`,
    top: `${props.leftTop.y}%`,
    right: `${props.rightBottom.x}%`,
    bottom: `${props.rightBottom.y}%`
  }}></div>
}