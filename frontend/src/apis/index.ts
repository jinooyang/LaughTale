import axios from "axios";
import useAuthLocalStroage from "../stores/useAuthLocalStroage.ts";

const baseURL = "http://localhost:8080";
const client = axios.create({
  baseURL
});

client.interceptors.request.use(
  (config) => {
    const authStore = useAuthLocalStroage();
    const accessToken = authStore.get();
    config.headers.Authorization = accessToken ?? undefined;
    return config;
  },
(error) => {
    console.error(error);
  return Promise.reject(error);
}
);
export default client;