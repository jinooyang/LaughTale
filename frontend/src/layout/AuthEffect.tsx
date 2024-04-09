import useAuthLocalStorage from "../stores/useAuthLocalStorage.ts";
import {useEffect, useState} from "react";
import client, {get} from "../apis";
import {useAuth} from "../stores/useAuth.ts";
import Loading from "../components/common/Loading.tsx";
import {User} from "../../types";
import {Role} from "../constants/Role.ts";

export default function AuthEffect ({children}){
  const authLocalStorage = useAuthLocalStorage();
  const authData = authLocalStorage.get();
  const {getToken, setToken, setUser, user,token,clear} = useAuth(state => ({...state}));
  const [loading, setLoding] = useState(true);
  useEffect(() => {
    console.log("authEffect")
    console.log("TEST", token, user)
    if(!authData?.accessToken) {
      setLoding(false);
      return;
    }

    get<User>("/member").then(res => {
      setToken({accessToken: authData.accessToken});
      setUser({...res});

      setLoding(false);
    }).catch(error => {
      console.log(error);
      clear();
      setLoding(false);
    });
  }, []);
  if(loading) {
    return <Loading/>;
  }

  return <>{children}</>
}