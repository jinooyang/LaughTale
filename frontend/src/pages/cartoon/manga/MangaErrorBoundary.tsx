import {QueryErrorResetBoundary, useQueryErrorResetBoundary} from '@tanstack/react-query'
import { ErrorBoundary } from 'react-error-boundary'
import {ThemeProvider} from "@material-tailwind/react";
import children = ThemeProvider.propTypes.children;
import {Navigate} from "react-router-dom";
import Error from "../../../components/common/Error.tsx";
export default function MangaErrorBoundary ({children}){
  const { reset } = useQueryErrorResetBoundary()
  return (
    <ErrorBoundary
      onReset={reset}
      fallbackRender={({ resetErrorBoundary }) => (
        <div>
          There was an error!
          <button onClick={() => resetErrorBoundary()}>Try again</button>
        </div>
      )}
    >
      {children}
    </ErrorBoundary>
  )
}
// (<QueryErrorResetBoundary>
//   {({ reset }) => (
//     <ErrorBoundary
//       onReset={reset}
//       fallbackRender={fallbackRender}>
//       {children}
//     </ErrorBoundary>
//   )}
// </QueryErrorResetBoundary>)
function fallbackRender({ error, resetErrorBoundary }) {
  console.log(error);
  return <div
    className="mt-6 flex rounded-3xl overflow-hidden bg-gradient-to-r from-[#64BEE2] from-5%  to-[#8395E8] to-100%">
    <div className="">
      <img src="https://random.imagecdn.app/500/150"/>
      {/*<img*/}
      {/*  src="" className="w-[300px]"/>*/}
    </div>
    <div className="p-10">
      <div className="font-bold text-3xl text-black	mb-10"></div>
      {/*<div className="font-bold text-xl text-black">작가 : {props.author}</div>*/}
      {/*<div className="font-bold text-xl text-black">장르 : {props.genre}</div>*/}
      <div className="mb-10"></div>
      <div className="font-bold text-xl text-black">줄거리</div>
      {/*<div className="mb-10"></div>*/}
      <div className="font-bold text-xl text-black"></div>
    </div>
  </div>
}