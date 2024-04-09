import {AddWordButton} from "./AddWordButton.tsx";
import {WordInfo} from "../../../types";
import "../../pages/cartoon/Viewer.css";
import { FaStar } from "react-icons/fa6";
import SpeechButton from "../common/SpeechButton.tsx";
import React, {useCallback, useEffect, useRef} from "react";

type Props = {
  words: Array<WordInfo>,
}

export default function WordList(props: Props){
  const scroll = useRef();
  // const scrollFn= () => scroll?.current?.scrollIntoView({ behavior: "smooth", block:'start'});
  // const scrollFn  = () => scroll?.current?.scrollIntoView({
  //   block:"end",
  //   inline: "start",
  //   behavior: "smooth",
  // });
  useEffect(() => {
    // scrollFn();
  }, [props.words]);

  return props?.words?.map(word =>{
    if(word?.definition) return (<div className="sticky border-2 border-[#3ECBF7] p-5 rounded-md text-3xl word_info_box " ref={scroll}>
        <div className="mb-5 flex justify-between" >
          <div className="flex items-center "> <span style={{background:`${word.color}`, color:'#000'}} className="rounded-md p-3 inline-block">{word.word}</span>
            <span className="flex">{(new Array(word.level)).fill(1).map(d => <span className="ml-2"><FaStar color={"rgb(255, 212, 59)"}/></span>)}</span>
            <SpeechButton sentence={word.word} style={{width:"3rem", display:"inline", marginLeft:'1rem', color:"#b26aff"}}/>
          </div>
          <AddWordButton wordId={word.id}/>
        </div>
      <div dangerouslySetInnerHTML={{__html:word.definition}} className="text-2xl text-black"></div>
    </div>)
  })
}