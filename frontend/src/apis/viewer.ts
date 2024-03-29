import client from "./index.ts";
import {MangaImageResponse} from "../../types";


export type CutImageRequest = {
  chapterId: number,
  page:number,
  size: number
}
export const getImageByChapterId = async (d: CutImageRequest)   => {
  console.log(d);
  const { data } =  await client.post<MangaImageResponse>(`/cut/images`, d, {
    headers:{
      "Content-Type":"application/json"
    }
  });
  return data;
}

