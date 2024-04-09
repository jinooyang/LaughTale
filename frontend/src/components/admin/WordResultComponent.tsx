import {WordAnalyze} from "../../../types";

export default function WordResult({props, color}: { props: WordAnalyze, color: string }) {
    if (props === null || props === undefined) {
        return <div className="font-bold">인식된 단어가 없습니다.</div>;
    }

    return (
        <div className="grid grid-cols-1 mb-2">
            <div className="font-bold">단어 :
                <span style={{background: color}}>{props.word}</span>
                <span> 난이도 : {props.level}</span>
            </div>
            <div dangerouslySetInnerHTML={{__html: props.definition}}></div>
        </div>
    );
}