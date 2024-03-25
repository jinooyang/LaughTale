export type Token = {
    accessToken: string;
    refreshToken: string;
}

export type Role = "USER" | "ANONYMOUS" | "ADMIN";

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
  level:number;
}

export type Chapter = {
    id: number;
    chapterNo: number;
    level: number;
    image: string;
}

export type ChapterList = Array<Chapter>


