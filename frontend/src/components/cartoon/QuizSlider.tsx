import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import { useNavigate } from 'react-router-dom';
import './QuizSlider.css';
import ImageWithWhiteBox from './ImageWithWhiteBox';
import React, {useState} from "react";
import {useQueryClient} from '@tanstack/react-query';
import axios from 'axios';
import client from "../../apis";
import {MdChevronLeft, MdChevronRight} from "react-icons/md";

type SelectAnswer = {
    id:number;
    answerId:number;
    answerword: string
}

type AnswerResult = { [key:number]:SelectAnswer }

function Modal({ isOpen, onClose ,  modalData}) {
    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="bg-[#ffffff] p-4 rounded-lg w-[600px] ">

                <div className="flex justify-between items-center w-full p-3">
                    <div className=" laughtale-font text-black font-bold flex-1 text-center">
                        단어 해석
                    </div>
                    <button
                        onClick={onClose}
                        className=" laughtale-font text-black rounded-full bg-grey-500 hover:bg-grey-700">
                        X
                    </button>
                </div>

                <div className="flex flex-col justify-between ">
                    <div className="flex flex-col justify-center items-center h-full text-black p-6">
                        <div className="rounded-xl bg-[#73ABE5] mb-6 p-12">
                            <div>
                                <h2 className=" laughtale-font text-2xl text-black font-bold"
                                    dangerouslySetInnerHTML={{__html: modalData}}></h2>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
};

// type Answerarray = { id: number; answerId: number; answerword: number;}
// type AnswerItem = { [ key : number ] : Answerarray; }
// type SelectedAnswer  = Array<{ id: number; answerId: number; answerword: number;}>

const QuizSlider = ({slides, updateCurrentSlide, sliderRef}) => {
    let navigate = useNavigate();


    function handleClick() {
        if (Object.keys(selectedAnswers).length < slides.length) {
            alert("모든 문제를 풀지 않았습니다. 모든 문제를 해결해 주세요.");
            return; // 여기서 함수 실행을 중단
        }

        const correctAnswersCount = calculateCorrectAnswers();
        navigate('result', { state: { slides, correctAnswersCount, selectedAnswers }});
        console.log(selectedAnswers);

        // @ts-ignore
        const updatedIds = Object.values(selectedAnswers).map(obj => obj.id + 1);
        console.log(updatedIds);

        client.post('/quiz/solve', { answer: updatedIds })
            .then(response => {
                console.log('Request successful', response.data);
            })
            .catch(error => {
                console.error('Request failed', error);
            });
    }


    const settings = {
        dots: false, // 점으로 페이지 위치 표시
        infinite: false, // 무한 슬라이딩 비활성화
        speed: 500,
        slidesToShow: 1, // 한 번에 보여질 슬라이드 페이지 수
        slidesToScroll: 1, // 스크롤할 때마다 넘어갈 슬라이드 페이지 수
        afterChange: current => updateCurrentSlide(current)
    };

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalData, setModalData] = useState(null);
    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    // 정답넣기
    const [selectedAnswers, setSelectedAnswers] = useState({});

    // 답안 버튼 클릭 이벤트 핸들러
    const handleAnswerClick = (slideIndex, checkwordId, answerwordId , answerword ) => {
        // 선택된 답안 정보 업데이트
        setSelectedAnswers(prev => ({
            ...prev,
            [slideIndex]: { id: checkwordId, answerId: answerwordId, answerword: answerword}
        }));
        console.log(selectedAnswers);
    };
    const goNext = () => {
        sliderRef.current.slickNext()
    }
    const goPrev = () => {
        sliderRef.current.slickPrev()
    }
    const calculateCorrectAnswers = () => {
        let correctCount = 0; // 정답 개수를 저장할 변수

        Object.values(selectedAnswers).forEach(answer => {
            // @ts-ignore
            if (answer.id + 1 === answer.answerId) { // 여기를 수정함
                correctCount += 1; // 조건이 맞을 경우 정답 개수 증가
            }
        });

        console.log(`정답 개수: ${correctCount}`); // 콘솔에 정답 개수 출력
        return correctCount; // 정답 개수 반환
    };

    return (
        <div>
            <Slider ref={sliderRef} {...settings}>
                {slides.map((slide, index) => {
                    const replaceWord = slide.option[slide.answerNo - 1];
                    const modifiedSentence = slide.sentence.replace(new RegExp(replaceWord, 'gi'), '  🟦🟦🟦  ');

                    return (
                        <div key={index} className="w-[1300px] ">
                            <div className=" laughtale-font text-black p-12 flex justify-center items-center grid grid-cols-2">
                                <div className="flex justify-end items-center">
                                    <pre className="font-semibold p-3"></pre>
                                    <ImageWithWhiteBox src={slide.imageUrl} boxCoordinates={{
                                        x: slide.leftBottomX,
                                        y: slide.leftBottomY,
                                        width: slide.rightTopX - slide.leftBottomX,
                                        height: slide.rightTopY - slide.leftBottomY
                                    }}/>
                                </div>
                                <div className="flex flex-row items-center">
                                    <div><MdChevronLeft
                                        className="cursor-pointer text-5xl"
                                        onClick={() => goPrev()}
                                    /></div>
                                    <div className="flex-1">
                                        <div
                                            className="bg-white font-semibold laughtale-font text-black p-6 flex justify-center items-center">
                                            Q{index + 1} 다음 말풍선에 들어갈 단어를 고르세요.
                                            <button
                                                className="laughtale-font ml-6 font-bold text-xl flex justify-center items-center text-black border-2 border-[#73ABE5] hover:bg-[#73ABE5] hover:text-white rounded-xl w-[50px] h-[30px]"
                                                onClick={() => {
                                                    openModal();
                                                    setModalData(slide.definition);
                                                }}>힌트</button>
                                        </div>

                                        <div className="flex justify-center items-center p-3 mb-3">
                                            <div
                                                className="flex justify-center items-center bg-white border-2 bg-[#C1C1C1] rounded-3xl p-3 max-w-[450px]">
                                                <div
                                                    className="flex justify-center items-center font-bold p-6 mr-12 ml-12 text-wrap overflow-wrap"
                                                    style={{whiteSpace: 'normal', wordWrap: 'break-word'}}>
                                                    {modifiedSentence}
                                                </div>
                                            </div>
                                        </div>

                                        <div className="flex justify-center items-center p-3">
                                            <div className="w-[450px]">
                                                <div
                                                    className="grid grid-cols-2 gap-6 justify-items-center items-center">
                                                    {slide.option.map((option, idx) => (
                                                        <button
                                                            key={idx}
                                                            className={` laughtale-font text-black font-bold border-2 border-[#59CDE0] hover:bg-gradient-to-b from-[#59CDE0] to-[#8F89EB] rounded-xl w-[200px] h-[50px] ${
                                                                selectedAnswers[index]?.id === idx ? "bg-gradient-to-b from-[#59CDE0] to-[#8F89EB]" : ""
                                                            }`} // 조건부 클래스 추가
                                                            onClick={() => handleAnswerClick(index, idx, slide.answerNo, slide.option[slide.answerNo - 1])} // 클릭 이벤트 핸들러 연결
                                                        >
                                                            {option}
                                                        </button>
                                                    ))}
                                                </div>
                                            </div>
                                        </div>
                                        {index === slides.length - 1 && (
                                            <div className="flex justify-center items-center mt-4 p-12">
                                                <button onClick={handleClick}
                                                        className=" laughtale-font text-black font-bold px-4 py-2 bg-gradient-to-b from-[#5ACDE1] to-[#8F89EB] rounded-3xl w-[150px]">제출하기
                                                </button>
                                            </div>
                                        )}
                                    </div>
                                    <div>
                                        <MdChevronRight
                                            className="cursor-pointer text-5xl"
                                            onClick={() => goNext()}
                                        /></div>

                                </div>

                            </div>
                        </div>
                    );
                })}
            </Slider>
            <Modal isOpen={isModalOpen} onClose={closeModal} modalData={modalData}/>
        </div>
    );
};

export default QuizSlider;
