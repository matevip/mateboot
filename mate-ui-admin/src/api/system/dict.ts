import http from "@/utils/request";

export const useDictList = () => {
  return http.get("/sys/dict/page");
};

