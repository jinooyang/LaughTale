import {useQuery, useSuspenseQuery} from "@tanstack/react-query";
import {getMangaInfo} from "../../../apis/cartoon.ts";
import {ThemeProvider} from "@material-tailwind/react";
import {ReactNode, Suspense, useEffect} from "react";
import {Cartoon} from "../../../types/types";
import CartoonHeader from "../../../components/cartoon/CartoonHeader.tsx";

type Props = {
  mangaId: number
  children: ReactNode
}

export default function MangaInfoFetcher (props: Props){
  const {mangaId, children} = props;
  const {data, isLoading} = useSuspenseQuery({
    queryKey: ["mangaInfo", mangaId],
    queryFn: () => getMangaInfo(+ mangaId),
    retry:0
  });
  useEffect(() => {
    console.log("fetcher");
  }, []);
  return <>{children}</>
}