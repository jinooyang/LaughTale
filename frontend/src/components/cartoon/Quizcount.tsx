import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import './animations.css';

const QuizCountdown = () => {
    const navigate = useNavigate();
    const { chapterId } = useParams();
    const [dots, setDots] = useState('');

    useEffect(() => {
        // 첫 3초 동안 점을 추가하여 "퀴즈 자동 생성 중입니다..." 메시지를 보여줌
        const timerId = setTimeout(() => {
            if (dots.length < 3) {
                setDots(dots + '.');
            } else {
                // 점이 3개가 되면 페이지 이동
                navigate(`/quiz/new/${chapterId}`);
            }
        }, 1000); // 1초마다 점을 추가하거나 페이지를 이동

        return () => clearTimeout(timerId);
    }, [dots, navigate, chapterId]);

    return (
        <div className="flex flex-col items-center justify-center h-screen bg-[#73ABE5]" style={{ height: 'calc(100vh * 1.1111)' }}>
            <div className="laughtale-font text-white font-bold text-7xl">
                퀴즈 자동 생성 중입니다{dots}
            </div>
        </div>
    );
};

export default QuizCountdown;
