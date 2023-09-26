import http from "@/utils/request";

export const useOnlineUserPage = (params: any) => {
  return http.get("/sys/online/user/page", { params });
};

export const useKickOut = (token: any) => {
  return http.delete("/sys/online/user/" + token);
};
