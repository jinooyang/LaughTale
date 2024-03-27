import client from "../../apis";
// 퀴즈 정보를 가져오는 함수

const getQuizInfo = async (quizId) => {
    const response = await client.get(`/quiz/${quizId}`);
    return response.data;
};
export default getQuizInfo;
