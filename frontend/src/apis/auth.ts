import axios from "axios";
import client from "./index.ts";

const getMyInfo = async ({accessToken}) => {
  const {data} = await axios.get("/member/test", {
    headers:{
      Authorization: accessToken
    }
  });
  return data;
}
export {getMyInfo};