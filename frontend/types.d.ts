
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

