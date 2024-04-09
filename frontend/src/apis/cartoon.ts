import client, {get} from "./index.ts";
import {Cartoon, ChapterList, MangaHistory, Page} from "../types/types";
import {Response} from "../types/common/page.ts";

export type ChapterListRequest = {
  page: Page | undefined;
  mangaId: number;
}

export type NextPrevPage  ={
  nextPage: number;
  prevPage: number;
};
export type FistChpaterId = {
  first: number
}

export const getMangaInfo = async (mangaId: number) =>  await get<Cartoon>(`/manga/info/${mangaId}`);

export const getChapterList = async (param : ChapterListRequest ) => await get<Response<ChapterList>>(`/chapter/list?page=${param.page}&mangaid=${param.mangaId}`);

export const getMangaHistory =  (mangaId: number) =>  get<MangaHistory>(`/history/${mangaId}`);

export const getNextPrevPageNumber = (chapterId: number) => get<NextPrevPage>(`/chapter/${chapterId}`);

export const getMangaFirstChapter = (mangaId: number) => get<FistChpaterId>(`/manga/${mangaId}/first`);