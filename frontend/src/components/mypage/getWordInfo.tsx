import client from "../../apis";

const getWordInfo = async (level, page, size) => {
    try {
        const response = await client.get(`/word-book/${level}?page=${page}&size=${size}`);
        // console.log(level, "레벨이 무엇인가?");
        return response.data;
    } catch (error) {
        console.error("API 호출 중 에러 발생:", error);
        throw error;
    }
};
export default getWordInfo;

