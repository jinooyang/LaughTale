import {Link, useParams} from "react-router-dom";


type Props = {
    mangaId: number
    thumbnail: string;
    chapterNo: number;
    chapterId: number;
    title: string;
    level: number;
    viewed: boolean;
}
export default function ChapterItem(props: Props) {

    return (
        <Link to={`/cartoon/${props.mangaId}/viewer/${props.chapterId}`}>
            <div
                className={`border border-[#73ABE5] hover:text-white hover:bg-gradient-to-r from-[#6CB3E4] to-[#8F89EB]  text-black  text-xl flex p-5 items-center rounded-2xl mb-5`}
                style={props.viewed ? {background:"#E9ECEF"} : undefined}
            >

                <div className="w-[64px] rounded-xl overflow-hidden mr-5">
                    <img src={props.thumbnail}/>
                </div>
                <div className="flex w-[100%] justify-center items-center">
                    <div className="flex-1">{props.title} {props.chapterNo + 1}화</div>
                    <div className="ml-10 mr-10 text-3xl">Lv. {props.level}</div>
                </div>
            </div>
        </Link>
    )
}