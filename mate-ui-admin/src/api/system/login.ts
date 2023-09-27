import http from "@/utils/request";

export const useLoginLogPage = (params: any) => {
  return http.get("/sys/login/log/page", { params });
};