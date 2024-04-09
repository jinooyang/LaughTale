import {useQueryClient, useSuspenseQuery} from "@tanstack/react-query";
import {Suspense, useEffect} from "react";
import {Cartoon} from "../../types/types";
import {getMangaInfo} from "../../apis/cartoon.ts";
import {ErrorBoundary} from "react-error-boundary";


// type Props = {
//   title:string;
//   author?:string;
//   genre?: string;
//   summary: string;
//   thumbnail:string;
// }
type Props = {
  mangaId:number;
}
export default function CartoonHeader(p: Props) {
  const {mangaId} = p;
  const {data:props, isLoading, isError,} = useSuspenseQuery({
    queryKey: ["mangaInfo", mangaId],
    queryFn: () => getMangaInfo(+ mangaId),
    retry:0
  });
  console.log("data is", props.thumbnail)
  return <>
      <div
        className=" flex bg-gradient-to-r from-[#64BEE2] from-5%  to-[#8395E8] to-100% rounded-2xl overflow-hidden h-1/2">
        {/*{props.thumbnail && <div className="flex-1 min-h-[300px] w-[50%]" style={{backgroundImage:`url(${props.thumbnail})`,backgroundSize: "cover"}}></div>}*/}
            <img src={`${props.thumbnail}`} style={{all:"unset",margin:0}} />

        <div className="p-10 flex-1">
          { props.level &&<div className="font-bold text-4xl text-white mb-5">레벨 : {props.level}</div>}
          <div className="font-bold text-5xl text-white	mb-10">{props?.title}</div>
          { props.author && <div className="font-bold text-2xl text-white">작가 : {props.author}</div>}
          { props.category && <div className="font-bold text-2xl text-white">카테고리 : {props.category}</div> }

          <div className="mb-10"></div>
          {
            props.summary && <>
                  <div className="font-bold text-xl text-white">줄거리</div>
                  <div className="font-bold text-xl text-white ">{props.summary}</div>
              </>
          }
        </div>
      </div>
  </>
}