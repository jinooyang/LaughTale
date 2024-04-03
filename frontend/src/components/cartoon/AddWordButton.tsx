import {useCallback} from "react";
import {addWordList} from "../../apis/viewer.ts";

type Props={
  wordId: number
}
export const AddWordButton = (props: Props) => {
  const onClick = useCallback(async () => {
    const response = await addWordList(+ props.wordId);
    console.log("add" , response)
  }, [props.wordId]);
  return (<button className="bg-[#73ABE5] hover:bg-blue-500 text-white font-bold py-2 px-4 rounded-full" onClick={onClick}>
    추가
    </button>)
}