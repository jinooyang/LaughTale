import CutResult from "./CutResultComponent.tsx";
import {ChapterAnalyze} from "../../../types";
export default function ChapterResult(props: ChapterAnalyze) {
    return (
            <div className="border-2 border-amber-200 mt-3 p-3">
                <div>{props.chapterNo}화</div>
                <div>Lv. {props.level}</div>
                {props.cuts.map((cut) => <CutResult {...cut}></CutResult>)}
            </div>
        );
}