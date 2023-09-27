import http from "@/utils/request";

export const useOperateLogPage = (params: any) => {
  return http.get("/sys/operate/log/page", { params });
};