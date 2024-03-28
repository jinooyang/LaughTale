import {ReactNode, Suspense} from "react";

type Props = {
  children: ReactNode;
}
export default function MangaInfoFetchingSuspense({children} : Props){
  return <Suspense fallback={<div className="bg-amber-50">loading..</div>}>
    {children}
  </Suspense>
}