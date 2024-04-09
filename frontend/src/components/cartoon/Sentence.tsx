import React from "react";
import SpeechButton from "../common/SpeechButton.tsx";
import "../../pages/cartoon/Viewer.css"
type Props = {
  sentence: string
  originSentence : string;
  close:() => void;
}
export default function Sentence(props: Props){
  return <div className="sticky top-0 bg-white p-5 flex z-50">
    <div dangerouslySetInnerHTML={{__html: props.sentence}} className="sentence text-6xl text-black"></div>

    <SpeechButton sentence={props.originSentence} style={{width: "5rem", display: "inline", color: "#b26aff"}}/>
    <button onClick={() => props.close()} style={{width: "20px", height: "20px",fontSize:"2.5rem"}}>X</button>

  </div>;
}