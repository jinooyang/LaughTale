

const useLocalStorage = (key: string) => {
    return {
        get:() => {
            return JSON.parse(localStorage.getItem(key));
        },
        set: (obj: object) => {
                localStorage.setItem(key, JSON.stringify(obj));
        },
        clear: () => {
            localStorage.removeItem(key);
        }
    }
}
export default useLocalStorage;