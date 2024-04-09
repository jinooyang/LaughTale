import {useCallback} from "react";
import {addWordList} from "../../apis/viewer.ts";
import toast from "react-hot-toast";

type Props={
  wordId: number
}
export const AddWordButton = (props: Props) => {
  const onClick = useCallback( () => {
    const response=  addWordList(+ props.wordId);

    // @ts-ignore
    toast.promise(response, {
      loading: 'Loading',
      success: '등록 성공!',
      error: '이미 존재하는 단어입니다.',
    });

    console.log("add" , response)

  }, [props.wordId]);
  return (<button className="bg-[#73ABE5] hover:bg-blue-500 text-white font-bold py-2 px-4 rounded-full" onClick={() => onClick()}>
    추가
    </button>)
}