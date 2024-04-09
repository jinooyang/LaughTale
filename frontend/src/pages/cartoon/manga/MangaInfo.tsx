
import CartoonHeader from "../../../components/cartoon/CartoonHeader.tsx";


type Props = {
  mangaId:number;
}
export default function MagaInfo(props: Props){
  const {mangaId} = props;
  // const {data, isLoading} = useSuspenseQuery({
  //   queryKey: ["mangaInfo", mangaId],
  //   queryFn: () => getMangaInfo(+ mangaId),
  //   retry:0
  // });
  // if(data){
  //   console.log("cache " , data);
  // }
  return (
    // <MangaInfoFetchingSuspense>
    //   <MangaInfoFetcher mangaId={mangaId}>
        <CartoonHeader mangaId={mangaId}/>
    //   </MangaInfoFetcher>
    // </MangaInfoFetchingSuspense>
  )
}