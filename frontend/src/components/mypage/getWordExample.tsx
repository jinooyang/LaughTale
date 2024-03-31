import client from "../../apis";

const getWordExample = async (example) => {
    try {
        const response = await client.get(`/word-data/${example}`);
        console.log("데이터가 들어온거니? 들어왔네 ")
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error("API 호출 중 에러 발생:", error);
        throw error;
    }
};
export default getWordExample;

