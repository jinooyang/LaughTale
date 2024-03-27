import client, {get} from "./index.ts";
import {Cartoon, ChapterItem, ChapterList, Page} from "../types/types";
import {Response} from "../types/common/page.ts";

export type ChapterListRequest = {
  page: Page | undefined;
  mangaId: number;
}

export const getMangaInfo = (bookNumber: number) => get<Cartoon>(`/manga/info/${bookNumber}`);

export const getChapterList = (param : ChapterListRequest ) => get<Response<ChapterList>>(`/chapter/list?page=${param.page}&mangaid=${param.mangaId}`);

