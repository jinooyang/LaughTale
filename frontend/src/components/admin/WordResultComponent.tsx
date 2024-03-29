import {WordAnalyze} from "../../../types";

export default function WordResult(props: WordAnalyze) {
    return (
        <div className="grid grid-cols-1 mb-2">
            <div className="font-bold">단어 : {props.word}</div>
            <div dangerouslySetInnerHTML={{__html:props.definition}}></div>
            <div>난이도 : {props.level}</div>
        </div>
    );
}