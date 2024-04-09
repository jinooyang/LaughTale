import { useRef, useState} from 'react';
import QuizSlider from "./QuizSlider.tsx";
import Progressbar from "./Progressbar.tsx";
import Header from "../common/Header.tsx";
import BlueHeader from "../common/BlueHeader.tsx";

import { useQuery } from '@tanstack/react-query';
import getQuizInfo from './getQuizInfo.tsx';
import {useParams} from "react-router-dom";

const Quiz = () => {
    const sliderRef = useRef(null);
    const [currentSlide, setCurrentSlide] = useState(0);

    const {chapterId} = useParams();
    const { data: quizData, isLoading, error } = useQuery({
        queryKey: ['quiz', chapterId],
        queryFn: () => getQuizInfo(chapterId)
    });

    const updateCurrentSlide = (index) => {
        setCurrentSlide(index);
    };

    const handleNumberClick = (index) => {
        sliderRef.current.slickGoTo(index);
    };

    console.log(quizData);

    return (
        <div className="bg-[#ffffff]" style={{ height: 'calc(100vh * 1.1111)' }}>
            <BlueHeader/>
            <div className="max-w-[450px] m-auto">
                <div className="flex justify-center pt-6 mt-12">
                    {quizData && <Progressbar value={currentSlide} max={quizData.length} onNumberClick={handleNumberClick}/>}
                </div>
            </div>
            <div className="text-black max-w-[1200px] m-auto p-6 mt-12">
                {quizData && <QuizSlider slides={quizData} updateCurrentSlide={updateCurrentSlide} sliderRef = {sliderRef}/>}
            </div>
        </div>
    )
}

export default Quiz;