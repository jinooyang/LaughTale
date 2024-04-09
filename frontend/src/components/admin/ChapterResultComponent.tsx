import CutResult from "./CutResultComponent.tsx";
import {ChapterAnalyze} from "../../../types";
import React, {useState} from "react";

export default function ChapterResult(props: ChapterAnalyze) {
    const [cur, setCur] = useState(1);
    const [content, setContent] = useState(props.cuts[0]);

    const handleCur = (value) => {
        if ((value === -1 && cur === 1) || ((value === 1 && cur === props.cuts.length))) {
            return;
        }
        setContent(props.cuts[cur + value - 1]);
        setCur(cur + value);
    }
    return (
        <div className="mt-3 p-3">
            <CutResult {...content}></CutResult>
            <div className="">
                <div className="grid grid-cols-3 mx-auto">
                    <button
                        onClick={() => handleCur(-1)}
                        className="bg-white hover:bg-gray-100 text-gray-800 font-semibold py-2 px-4 rounded border mt-5 flex justify-center items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                             stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M10.5 19.5 3 12m0 0 7.5-7.5M3 12h18"/>
                        </svg>
                    </button>
                    <button
                        className="bg-white cursor-default text-gray-800 font-semibold py-2 px-4 rounded border mt-5">{cur}</button>
                    <button
                        onClick={() => handleCur(1)}
                        className="bg-white hover:bg-gray-100 text-gray-800 font-semibold py-2 px-4 rounded border mt-5 flex justify-center items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                             stroke="currentColor" className="w-6 h-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3"/>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    );
}