import {CutAnalyze} from "../../../types";
import React, {useEffect, useState} from "react";
import WordResult from "./WordResultComponent.tsx";
import Pagination from "./Pagination.tsx";
import ImageWithIndexAndRect from "./ImageWithIndexAndRect.tsx";
import SentenceResult from "./SentenceResultComponent.tsx";


export default function CutResult(props: CutAnalyze) {
    const colors = ["#CDFADB", "#F6FDC3", "#FFCF96", "#FF8080", "#D2E0FB", "#F9F3CC", "#D7E5CA", "#8EACCD"]
    const [speechLength, setSpeechLength] = useState(props.sentence?.length ?? 0);
    const [speechCur, setSpeechCur] = useState(props.sentence == null || false ? 0 : 1);
    const [speechContent, setSpeechContent] = useState(props.sentence[0] ?? null);

    const [wordLength, setWordLength] = useState(props.sentence[0]?.words.length ?? 0);
    const [wordCur, setWordCur] = useState(props.sentence[0]?.words[0] == null ? 0 : 1);
    const [wordContent, setWordContent] = useState(props.sentence[0]?.words[0] ?? null);

    const [colorIdx, setColorIdx] = useState(0);
    useEffect(() => {
        if (props.sentence == null) return;
        setSpeechLength(props.sentence.length);
        setSpeechCur(Math.min(1, props.sentence.length));
        setSpeechContent(props.sentence[0]);
    }, [props, props.imageUrl]);

    useEffect(() => {
        if (speechContent?.words == null) {
            setWordContent(null);
            setWordCur(0);
            setWordLength(0);
            return;
        }
        setWordLength(speechContent.words.length);
        setWordCur(Math.min(speechContent.words.length, 1));
        setWordContent(speechContent.words[0]);
    }, [speechContent]);

    const handleSpeechCur = (value: number) => {
        setSpeechCur(speechCur + value);
        setSpeechContent(props.sentence[speechCur + value - 1]);
    }

    const handleWordCur = (value: number) => {
        setWordCur(wordCur + value);
        setWordContent(speechContent.words[wordCur + value - 1]);
        setColorIdx((wordCur - 1) % colors.length);
    }

    return (
        <div className="grid grid-cols-2 gap-5">
            {speechContent != null ? <ImageWithIndexAndRect index={speechCur} src={props.imageUrl} boxCoordinates={{
                    x: speechContent.positionBasic.leftBottomX,
                    y: speechContent.positionBasic.leftBottomY,
                    width: speechContent?.positionBasic.rightTopX - speechContent.positionBasic.leftBottomX,
                    height: speechContent?.positionBasic.rightTopY - speechContent.positionBasic.leftBottomY
                }}/> :
                <img src={props.imageUrl} alt=""/>
            }
            <div className="flex flex-col border rounded">
                <p className="text-3xl font-bold p-4 border-b">문장</p>
                <div className="p-6 shadow rounded">
                    {speechContent != null ?
                        <div className="text-center mb-5">
                            <SentenceResult sentence={speechContent.sentence} word={wordContent?.word}
                                            color={colors[colorIdx]}/>
                        </div>
                        :
                        <div>
                            <p className="font-bold">인식된 문장이 없습니다.</p>
                        </div>
                    }
                </div>
                <Pagination length={speechLength} cur={speechCur} setCur={handleSpeechCur}></Pagination>
                <p className="text-3xl font-bold p-4 border rounded">단어</p>
                <div className="relative flex-1">
                    <div
                        className="absolute top-0 bottom-0 left-0 right-0 overflow-y-scroll">
                        <div className="absolute top-0 bottom-0 left-0 right-0 p-4">
                            <div>
                                {<WordResult props={wordContent} color={colors[colorIdx]}/>}
                            </div>
                        </div>
                    </div>
                </div>
                <Pagination length={wordLength} cur={wordCur} setCur={handleWordCur}></Pagination>
            </div>
        </div>
    );
}