import React from "react";
import SpeechButton from "../common/SpeechButton.tsx";

type Props = {
  sentence: string
  originSentence : string;
}
export default function Sentence(props: Props){
  return <div className="sticky top-0 bg-white p-5 flex z-50">
    <div dangerouslySetInnerHTML={{__html:props.sentence}} className="text-6xl text-black"></div>
    <SpeechButton sentence={props.originSentence} style={{width:"5rem", display:"inline", color:"#b26aff"}}/>
  </div>;
}