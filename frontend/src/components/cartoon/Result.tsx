import BlueHeader from '../../components/common/BlueHeader.tsx';
//이전 페이지에서 보내준 데이터를 받을 수 있다.
import {Link, useLocation, useParams, useNavigate} from 'react-router-dom';
import ResultWord from '../cartoon/ResultWord.tsx';
import {useQuery} from "@tanstack/react-query";
import getChapterInfo from "../mypage/getChapterInfo.tsx";
import {useEffect} from "react";

const prefix = `/cartoon/1`

const Result = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const {  slides , correctAnswersCount, selectedAnswers } = location.state;
    console.log(correctAnswersCount);

    const {chapterId} = useParams();

    const { data: chapterdata, isLoading, error } = useQuery({
        queryKey: ['chapterdata', chapterId],
        queryFn: () => getChapterInfo(chapterId),
    });

    useEffect(() => {
        if (!isLoading && chapterdata && !error && chapterId) {
            console.log("받아졌다!");
        }
    }, [isLoading, chapterdata, error, chapterId]);


    const handleNextPageClick = (e) => {
        if (chapterdata.nextPage === null) {
            e.preventDefault(); // Link의 기본 동작을 방지
            alert('다음 회차가 없습니다.');
        } else {
            navigate(`${prefix}/viewer/${chapterdata.nextPage}`);
        }
    };

    return (
        <div className="flex flex-col h-screen bg-[#ffffff]" style={{ height: 'calc(100vh * 1.1111)' }}>
            <BlueHeader/>
            <div className="flex-grow flex justify-center items-center">
                <div className="w-full max-w-[1180px] h-[600px]">
                    <div className="flex flex-col justify-center items-center p-12">
                        <div
                            className=" laughtale-font w-[400px] text-[20px] font-bold text-black flex justify-center items-center p-6">
                            퀴즈 풀이 결과
                        </div>
                        <div className=" laughtale-font text-black font-bold mt-6 p-6 text-5xl">
                            <div>
                                {20 * correctAnswersCount}점
                            </div>
                        </div>
                    </div>
                    <div className="z-10 flex justify-center items-center">
                        <ResultWord slides={slides} selectedAnswers={selectedAnswers}/>
                    </div>
                    <div className=" laughtale-font flex text-white justify-center items-center p-6">
                        <div className=" laughtale-font flex text-white justify-center items-center p-3">
                            {chapterdata &&<Link to={`${prefix}/viewer/${chapterdata.prevPage}`} replace
                                  className=" laughtale-font p-6 text-2xl font-bold bg-[#73ABE5] brightness-100 hover:brightness-125 rounded-3xl">
                                이전 회차 보기
                            </Link>}
                        </div>

                        <div className=" laughtale-font flex text-white justify-center items-center p-3">
                            <Link to={`${prefix}/viewer/${chapterId}`} replace
                                  className=" laughtale-font p-6 text-2xl font-bold bg-[#73ABE5] brightness-100 hover:brightness-125 rounded-3xl">
                                다시보기
                            </Link>
                        </div>

                        <div className=" laughtale-font flex text-white justify-center items-center p-3">
                            {chapterdata && <Link to={`${prefix}/viewer/${chapterdata.nextPage}`} replace
                                  onClick={handleNextPageClick}
                                  className=" laughtale-font p-6 text-2xl font-bold bg-[#73ABE5] brightness-100 hover:brightness-125 rounded-3xl">
                                다음 회차 보기
                            </Link>}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Result;