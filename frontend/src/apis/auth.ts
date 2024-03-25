import axios from "axios";
import client from "./index.ts";

const getMyInfo = async ({accessToken}) => {
  const {data} = await client.get("/me");
  return data;
}
export {getMyInfo};