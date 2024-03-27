
export type Token = {
    accessToken: string;
    refreshToken?: string;
}

export type Role = "USER" | "ANONYMOUS" | "ADMIN" | ROLE_NOT_CERTIFIED;

export type User = {
    id:number;
    profile:string;
    nickname: string;
    role: Role;
}
