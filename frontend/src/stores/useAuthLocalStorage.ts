import useLocalStorage from "./useLocalStorage.ts";
import {LOCALSTORAGE_AUTH} from "../constants/LocalStorageKey.ts";

const useAuthLocalStorage = () => {
  const authStorage = useLocalStorage(LOCALSTORAGE_AUTH);
  return authStorage;
}

export default useAuthLocalStorage;