import {ReactNode, Suspense} from "react";
import MangaInfoFetcher from "./MangaInfoFetcher.tsx";
import {ThemeProvider} from "@material-tailwind/react";
import children = ThemeProvider.propTypes.children;

type Props = {
  children: ReactNode;
}
export default function MangaInfoFetchingSuspense({children} : Props){
  return <Suspense fallback={<div className="bg-amber-50">loading..</div>}>
    {children}
  </Suspense>
}