// type Props = {
//   message: string;
//   statusCode: string;
// }

import BlueHeader from "./BlueHeader.tsx";
import {Link} from "react-router-dom";

type Props = {
    message?: string,
    statusCode?:string
}
export default function Error (props : Props){
  console.log(props)
    // const props = {
    //     message : `존재하지 않는 주소를 입력하셨거나, \n요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다.`,
    //     statusCode : "400",
    // }
  return (
      <div className="bg-[#ffffff] min-h-screen text-black overflow-hidden ">
          <div>
              <BlueHeader/>
              <div className="flex justify-center items-center">
                  <div>
                      <div className="text-8xl p-3 mt-40">{props?.statusCode ?? props.statusCode }</div>
                      <div className="text-lg whitespace-pre-wrap flex flex-col items-center justify-center p-6">{props.message ? props.message : "Error"}</div>
                      <div className="flex justify-center">
                          <Link to={"/home"} replace
                            className="p-3 text-base text-black bg-[#2D2D32] brightness-75 hover:brightness-100 rounded-3xl ">홈으로
                              돌아가기
                          </Link>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  );
}
