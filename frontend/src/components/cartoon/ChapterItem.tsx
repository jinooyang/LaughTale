

type Props = {
  title:string;
  thumbnail: string;
  chapterNo: number;
  chapterId: number;
}
export default function ChapterItem(props : Props){

  return (
    <div
      className="bg-gradient-to-r from-[#747982] to-[#879099] text-white font-semibold text-xl flex p-5 items-center rounded-2xl mb-5">
      <div className="w-[64px] rounded-xl overflow-hidden mr-5">
        <img
          src={props.thumbnail}/>
      </div>
      <div>{props.title} {props.chapterNo + 1}화</div>
    </div>
  )

}