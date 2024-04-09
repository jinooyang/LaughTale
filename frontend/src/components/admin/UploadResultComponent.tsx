import {MangaAnalyze} from "../../../types";
import React, {useEffect, useState} from "react";
import ChapterResult from "./ChapterResultComponent.tsx";
import Chart from "react-apexcharts";
import ChartResult from "./ChartComponent.tsx";
import BlueHeader from "../common/BlueHeader.tsx";

export default function UploadResult({props, isAdmin}: { props: MangaAnalyze, isAdmin: boolean }) {
    const [isInfo, setInfo] = useState(true);

    return (
        <>
            { isAdmin ? <BlueHeader/> : null}
            <div className="container mx-auto flex justify-center">
                {
                    isInfo ?
                        <div className="absolute top-1/4">
                            <div className="flex justify-between items-center">
                                <div className="grid grid-cols-1 gap-2">
                                    <div className="border border-2">
                                        <p className="text-7xl px-20 py-10">
                                            분석 완료!
                                        </p>
                                        {isAdmin && (<div>
                                            <p className="text-3xl px-20 py-5">{props.title} {props.chapter[0].chapterNo}화</p>
                                            <p className="text-xl px-20 py-3">작가 : {props.author}</p>
                                            <p className="text-xl px-20 py-3">카테고리 : {props.category}</p>
                                            <p className="text-xl px-20 py-3">설명 : {props.description}</p>
                                        </div>)}
                                        <p className="text-xl px-20 py-7">
                                            위 만화는 다음과 같은 난이도의 단어로 이루어졌어요!
                                        </p>
                                        <ChartResult {...props}></ChartResult>
                                    </div>
                                </div>
                                {/*<div>*/}
                                {/*    <img className="max-h-96 max-w-96" src={props.thumbnail} alt=""/>*/}
                                {/*</div>*/}
                            </div>
                            <div className="text-center">
                                <button
                                    onClick={() => setInfo(false)}
                                    className="bg-white hover:bg-gray-100 text-gray-800 font-semibold py-2 px-4 border border-gray-400 rounded shadow w-1/2 mt-7">
                                    상세 분석 결과 보기
                                </button>
                            </div>
                        </div> :
                        <ChapterResult {...props.chapter[0]}></ChapterResult>
                }
            </div>
        </>
    );
}