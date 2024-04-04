import client from "../../apis";

const getWordInfo = async (level, page, size) => {
    try {
        const response = await client.get(`/word-book/${level}?page=${page}&size=${size}`);
        console.log("데이터가 들어왔나",response.data);
        return response.data;
    } catch (error) {
        console.error("API 호출 중 에러 발생:", error);
        throw error;
    }
};
export default getWordInfo;

