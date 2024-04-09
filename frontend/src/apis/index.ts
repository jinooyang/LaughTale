import axios from "axios";
import useAuthLocalStroage from "../stores/useAuthLocalStorage.ts";

const baseURL = "https://j10a705.p.ssafy.io/api";
// const baseURL = "http://localhost:4000";

const client = axios.create({
  baseURL
});
export const get = async<T> (url: string): Promise<T> => {
  const {data} = await client.get<T>(url);
  return data;
}

client.interceptors.request.use(
  (config) => {
    const authStore = useAuthLocalStroage();
    const token = authStore.get();
    const accessToken = token?.accessToken ?? "";
    console.log(token);

    // config.headers.Authorization = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE3MTE1MDI4MjYsImV4cCI6NTE4NTcxMTUwMjgyNiwicm9sZSI6IkFETUlOIn0.5eJr2_G5I4ukcOyhrt6BlvDmQHVDLDUMCyJxND9V4tU';
    config.headers.Authorization = `${accessToken}`;

    return config;
  },
(error) => {
    console.error(error);
  return Promise.reject(error);
}
);
export default client;