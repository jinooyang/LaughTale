import useLocalStorage from "./useLocalStorage.ts";
import {LOCALSTORAGE_AUTH} from "../constants/LocalStorageKey.ts";

const useAuthLocalStroage = () => {
  const authStorage = useLocalStorage(LOCALSTORAGE_AUTH);
  return authStorage;
}

export default useAuthLocalStroage;