import BlueHeader from "../../../src/components/common/BlueHeader.tsx";
import {useInfiniteQuery, useQuery, useQueryClient} from "@tanstack/react-query";
import {useParams} from "react-router-dom";
import {getChapterList, getMangaHistory} from "../../apis/cartoon.ts";
import ChapterList from "../../components/cartoon/ChapterList.tsx";
import {Cartoon, ChapterListResponse} from "../../types/types";
import React, {Suspense, useEffect, useState} from "react";
import {useInView} from "react-intersection-observer";

import FirstEpisode from "./manga/FirstEpisode.tsx";
import MagaInfo from "./manga/MangaInfo.tsx";
import TotalEpisode from "./manga/TotalEpisode.tsx";
import MangaErrorBoundary from "./manga/MangaErrorBoundary.tsx";
import CartoonHeader from "../../components/cartoon/CartoonHeader.tsx";
import {ErrorBoundary} from "react-error-boundary";
import CartoonHeaderSuspense from "./manga/CartoonHeaderError.tsx";
import client, {get} from "../../apis";
import Chart from "react-apexcharts";
import {ChapterHistory, Level} from "../../../types";
import {colors} from "../../constants/colors.ts";

const Index = () => {
    const params = useParams()
    const mangaId = +params.title;
    const [wordLevelChartData, setWordLevelChartData] = useState({labels: [], series: []});
    const [chapterLevelChartData, setChapterLevelChartData] = useState({labels: [], series: []});
    const {data:history , isLoading:historyLoading} = useQuery<ChapterHistory>({
        queryFn:() => get<ChapterHistory>(`/history/${mangaId}`),
        queryKey: ["mangaHistory", mangaId],
        refetchOnMount:"always"
    })

    useEffect(() => {
        const fetchMangaWordLevelData = async () => {
            const data:Array<any> = await get(`/manga/word/level?mangaId=${mangaId}`);
            console.log(data);
            const labels = data.map(item => `Word Level ${item.level}`);
            const series = data.map(item => item.count);

            setWordLevelChartData({labels, series});
        };

        const fetchMangaChapterLevelData = async () => {
            const data: Array<Level> = await get(`/manga/chapter/level?mangaId=${mangaId}`);
            console.log(data);
            const labels = data.map(item => `Chapter Level ${item.level}`);
            const series = data.map(item => item.count);

            setChapterLevelChartData({labels, series});
        }

        fetchMangaWordLevelData();
        fetchMangaChapterLevelData();
    }, [mangaId]);
    console.log(history)
    const {
        data,
        error,
        fetchNextPage,
        hasNextPage,
        isFetching,
        isFetchingNextPage,
        status,
    } = useInfiniteQuery<ChapterListResponse>({
        queryKey: ['chapterList', mangaId],
        queryFn: ({pageParam}) => {
            // console.log("page param " + pageParam);
            return getChapterList({
                page: +pageParam,
                mangaId
            })
        },
        initialPageParam: 0,
        getNextPageParam: (lastPage, allPages, lastPageParam) => {
            if (typeof lastPageParam !== 'number') {
                return 0;
            }
            return lastPageParam + 1
        },
    });
    const {ref, inView} = useInView({
        threshold: 0,
        triggerOnce: false
    })
    const queryClient = useQueryClient();
    const getData = queryClient.getQueryData<Cartoon>(["mangaInfo", mangaId])
    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);
    const {data: firstChapter, isLoading, isError} = useQuery<{id: number}>({
        queryKey: [`mangaFirstChapter`, mangaId],
        queryFn:() => get(`/chapter/first/${mangaId}`)
});

    // console.log("test" ,data?.pages[0]?.totalElements)
    return <>
            <BlueHeader/>
        <div className="bg-[#ffffff] h-full absolute top-0 bottom-0 right-0 left-0 ">
            <div className="absolute top-[50px] bottom-0 left-0 right-0">
                    <div className=" flex flex-row h-full p-10">
                        <div className="flex-1 mr-2.5">
                            <ErrorBoundary fallbackRender={(props) => <CartoonHeaderSuspense type={"error"}/>}>
                                    <Suspense fallback={<CartoonHeaderSuspense type={"loading"}/>}>

                                        <CartoonHeader mangaId={mangaId}/>

                                        {firstChapter?.id && <FirstEpisode mangaId={mangaId} chapterId={firstChapter.id}/>}

                                        {data?.pages[0]?.totalElements && <TotalEpisode total={data.pages[0]?.totalElements}/>}
                                    </Suspense>
                            </ErrorBoundary>
                            {/*e도넛*/}
                            <div className="flex h-1/2">
                                <Chart
                                  type="donut"
                                  series={wordLevelChartData.series}

                                  options={{
                                      labels: wordLevelChartData.labels,
                                      chart: {
                                          type: 'donut',
                                      },
                                        colors: colors
                                  }}
                                  style={{
                                      width:"100%"
                                  }}
                                />
                                <Chart
                                  type="donut"
                                  series={chapterLevelChartData.series}

                                  options={{
                                      labels: chapterLevelChartData.labels,
                                      chart: {
                                          type: 'donut',
                                      },
                                      colors: colors
                                  }}
                                  style={{
                                      width:"100%"
                                  }}
                                />
                            </div>
                        </div>
                        <div className="flex-1 ml-2.5 relative">
                            <div className="absolute top-0 left-0 right-0 bottom-0 overflow-auto pb-10 pr-10 pl-10 pt-1">
                                {!data ? <div>...loading..</div> :
                                  <>
                                      {
                                          data.pages.map((pages) => <>
                                              <ChapterList content={pages.content} title={getData.title} mangaId={mangaId}/>
                                          </>)}
                                  </>
                                }
                                <div ref={ref} className="h-1"></div>
                                {
                                  isFetchingNextPage && <div className="text-black"> fetching...</div>
                                }
                            </div>
                        </div>
                    </div>
             </div>
        </div>
    </>

}

export default Index;