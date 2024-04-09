export type Token = {
    accessToken: string;
    refreshToken: string;
}

export type Role = "USER" | "ANONYMOUS" | "ADMIN";
export type Page = number;

export type User = {
    id:number;
    profile:string;
    nickname: string;
    role: Role;
}

export type Cartoon = {
    id: number;
    title:string;
    thumbnail:string;
    author:string;
    summary:string;
    category:string;
    level:number;
    genre: string;
}



export type ChapterItem = {
    chapterId: number;
    chapterNo: number;
    thumbnail: string;
    level:null | number;
}

export type MangaHistory = {
    memberId: number;
    mangaId: number;
    chapterViewed: number[];
}

export type ChapterList = Array<ChapterItem>


export type ChapterListResponse = Response<ChapterList>

