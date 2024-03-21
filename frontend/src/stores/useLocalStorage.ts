
const useLocalStorage = (key) => {
    return {
        set:(obj) => {
            localStorage.setItem(key, JSON.stringify(obj));
        },
        get:() => {
            return JSON.parse(localStorage.getItem(key));
        }
    }
}

export default useLocalStorage;