import {NavLink} from "react-router-dom";

type Props = {
  mangaId: number
}
export default function FirstEpisode (props: Props){
  const {mangaId} = props;
  return (<NavLink to={`/cartoon/${mangaId}/viewer/0`} >
    <div
      className="text-2xl font-semibold text-center p-4 bg-gradient-to-r from-[#64BEE2] from-5%   to-[#8395E8] to-100% mt-10 mb-5 rounded-full">
      첫화보기 1화
    </div>
  </NavLink>);
}

