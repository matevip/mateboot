import http from "@/utils/request";

export const useDictList = () => {
  return http.get("/sys/dict/page");
};

export const useDictItemList = (params: any) => {
  return http.get("/sys/dictItem/page",  { params });
};

