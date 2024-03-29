import {ReactNode, Suspense, useEffect} from "react";

type Props = {
  children: ReactNode;
}
export default function MangaInfoFetchingSuspense({children} : Props){
  useEffect(() => {
    console.log("suspense")
  }, []);
  return <Suspense fallback={<div className="h-[100px] bg-gray-600 text-white">loading</div>}>
    {children}
  </Suspense>
}