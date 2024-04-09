import React, {useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from "@material-tailwind/react";
import { MdChevronLeft, MdChevronRight } from 'react-icons/md';
import {useQuery} from "@tanstack/react-query";
import {get} from "../../apis";

type QuizButtonProps = {
    chapterId: number;
    mangaId: number;
};
type Page = {
  nextPage:number;
  prevPage:number;
}

const QuizButton: React.FC<QuizButtonProps> = ({ chapterId, mangaId }) => {
  const navigate = useNavigate();
  // const data = {
  //   prevPage:1,
  //   nextPage:1
  // }
  const {data, isLoading, isFetching} = useQuery<Page>({
    queryKey: ["pageNextPrev", chapterId],
    queryFn: () => get<Page>(`/chapter/${chapterId}`)
  })

  useEffect(() => {
    if(data) {
      console.log(typeof data.nextPage)
      console.log(typeof data.prevPage)

    }
  }, [data]);
  const handlePreviousClick = () => {
    navigate(`/cartoon/${mangaId}/viewer/${data.prevPage}`);
  };

  const handleNextClick = () => {
    navigate(`/cartoon/${mangaId}/viewer/${data.nextPage}`);
  };

  const handleQuizButtonClick = () => {
    navigate(`/quiz/new/${chapterId}/cnt`);
  };
  console.log(data);
  return data ? <div className="flex justify-center items-center space-x-4 p-4 m-10 rounded shadow">
      {data.prevPage && <MdChevronLeft
          className="cursor-pointer text-5xl"
          onClick={handlePreviousClick}
      />}
      <button
          onClick={handleQuizButtonClick}
          className="text-5xl py-6 px-12 bg-blue-500 hover:bg-blue-700 text-white font-bold rounded-3xl"
      >
          퀴즈 풀면서 단어 학습하기!
      </button>
    {
      data.nextPage && <MdChevronRight
            className="cursor-pointer text-5xl"
            onClick={handleNextClick}
        />
    }
  </div> : <></>
};

export default QuizButton;
