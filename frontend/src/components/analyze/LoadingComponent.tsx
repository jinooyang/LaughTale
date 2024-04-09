import React, {useEffect, useState} from "react";
import client from "../../apis";
import Header from "../common/Header.tsx";

export default function LoadingBar() {
    const texts = ['분석 중', '分석 중', '分析 중', '分析 中', '分析 中', '分析 中.', '分析 中..', '分析 中...']
    const [textIndex, setTextIndex] = useState(0);
    const [randomData, setRandomData] = useState(null);
    const [wordIdx, setWordIndex] = useState(0);
    const textToSpeech = (sentence) => {
        const speech = new SpeechSynthesisUtterance(sentence);
        speech.lang = "ja";
        speech.pitch = 1;
        speech.rate = 1;
        speechSynthesis.speak(speech);
    }

    const handlePageChange = (value) => {
        const len = randomData.length;
        setWordIndex((wordIdx + len + value) % len);
    }

    useEffect(() => {
        client.get('/word-data/random').then((response) => {
            setRandomData(response.data);
        }).catch(error => console.error(error));
        const interval = setInterval(() => {
            setTextIndex((prevIndex) => (prevIndex + 1) % texts.length);
        }, 500);
        return () => clearInterval(interval);
    }, []);

    useEffect(() => {
        if (randomData == null) {
            return;
        }
        const len = randomData.length;
        const interval = setInterval(() => {
            setWordIndex(prev => (prev + 1) % len);
        }, 10000);
        return () => clearInterval(interval);
    }, [randomData]);

    return (
        <div className="items-center block">
            <div role="status" className="absolute -translate-x-1/2 -translate-y-1/2 top-2/4 left-1/2">
                <div className="flex flex-col justify-center items-center">

                    <p className="mb-5 text-5xl">{texts[textIndex]}</p>
                    <div className="relative"></div>
                    {randomData && <div className="border-2 rounded-lg p-4 flex">
                        <button
                            onClick={() => handlePageChange(-1)}
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                 strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                <path strokeLinecap="round" strokeLinejoin="round"
                                      d="M15.75 19.5 8.25 12l7.5-7.5"/>
                            </svg>
                        </button>
                        <div className="ms-5 me-5 flex flex-col w-[40vw] h-[40vh] overflow-y-hidden">
                            {<div className="flex justify-between">
                                <p className="font-bold text-5xl mb-3">[오늘의 랜덤 학습 단어]</p>
                                <br/>
                                <p className="font-bold text-5xl mb-3">{randomData[wordIdx].word}</p>
                                <button
                                    onClick={() => textToSpeech(randomData[wordIdx].word)}
                                >
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
                                         className="w-6 h-6">
                                        <path
                                            d="M13.5 4.06c0-1.336-1.616-2.005-2.56-1.06l-4.5 4.5H4.508c-1.141 0-2.318.664-2.66 1.905A9.76 9.76 0 0 0 1.5 12c0 .898.121 1.768.35 2.595.341 1.24 1.518 1.905 2.659 1.905h1.93l4.5 4.5c.945.945 2.561.276 2.561-1.06V4.06ZM18.584 5.106a.75.75 0 0 1 1.06 0c3.808 3.807 3.808 9.98 0 13.788a.75.75 0 0 1-1.06-1.06 8.25 8.25 0 0 0 0-11.668.75.75 0 0 1 0-1.06Z"/>
                                        <path
                                            d="M15.932 7.757a.75.75 0 0 1 1.061 0 6 6 0 0 1 0 8.486.75.75 0 0 1-1.06-1.061 4.5 4.5 0 0 0 0-6.364.75.75 0 0 1 0-1.06Z"/>
                                    </svg>
                                </button>
                            </div>}
                            {<p className="font-bold text-3xl mb-3">난이도: {randomData[wordIdx].level}</p>}
                            {<div>
                                <p className="font-bold text-3xl mb-3">뜻</p>
                                <div dangerouslySetInnerHTML={{__html: randomData[wordIdx]?.definition}}></div>
                            </div>}

                        </div>
                        <div className="absolute top-full left-1/2 -translate-x-2/4 flex justify-center">
                            {randomData.map((data, index) => (
                                index == wordIdx ? (
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                         fill="currentColor" className="w-6 h-6">
                                        <path fillRule="evenodd"
                                              d="M4.5 7.5a3 3 0 0 1 3-3h9a3 3 0 0 1 3 3v9a3 3 0 0 1-3 3h-9a3 3 0 0 1-3-3v-9Z"
                                              clipRule="evenodd"/>
                                    </svg>) : (
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                         strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                        <path strokeLinecap="round" strokeLinejoin="round"
                                              d="M5.25 7.5A2.25 2.25 0 0 1 7.5 5.25h9a2.25 2.25 0 0 1 2.25 2.25v9a2.25 2.25 0 0 1-2.25 2.25h-9a2.25 2.25 0 0 1-2.25-2.25v-9Z"/>
                                    </svg>)
                            ))}
                        </div>
                        <button
                            onClick={() => handlePageChange(1)}
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                 strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                <path strokeLinecap="round" strokeLinejoin="round"
                                      d="m8.25 4.5 7.5 7.5-7.5 7.5"/>
                            </svg>
                        </button>
                    </div>}
                </div>
            </div>
        </div>
    );
}