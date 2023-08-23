import http from "@/utils/request";

export const useRolePage = (params: any) => {
  return http.get("/v1/sysRole/page", { params });
};

export const useRoleList = () => {
  return http.get("/sys/role/list");
};

export const useRoleSave = (dataForm: any) => {
  return http.post("/sys/role/save", dataForm);
};
