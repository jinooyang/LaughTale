import axios from "axios";
import client from "./index.ts";

const getMyInfo = async () => {
  const {data} = await client.get("/me");
  return data;
}
export {getMyInfo};