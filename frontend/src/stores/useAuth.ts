import { create } from "zustand"
import {Token, User} from "../../types";
type AuthState  ={
    user : User
    token: Token
}

type AuthAction  ={
    setUser: (user: User) => void;
}

type AuthStore = AuthState & AuthAction;

export const useAuth = create<AuthStore>((set) => ({
    user: null,
    token: null,
    setUser: (user) => set(() => ({user})),
}));
