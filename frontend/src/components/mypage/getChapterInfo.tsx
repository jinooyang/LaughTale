import client from "../../apis";

const getChapterInfo = async (chapterId) => {
    try {
        const response = await client.get(`/chapter/${chapterId}`);
        console.log("chapters 데이터", response.data);
        return response.data;
    } catch (error) {
        console.error("API 호출 중 에러 발생:", error);
        throw error;
    }
};
export default getChapterInfo;

