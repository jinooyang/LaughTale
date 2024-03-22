
const useLocalStorage = (key: string) => {

    return {
    get:() => {
        return JSON.parse(localStorage.getItem(key));
    },
    set: (obj: object) =>
        {
            localStorage.setItem(key, JSON.stringify(obj));
        }
    }
}
export default useLocalStorage;