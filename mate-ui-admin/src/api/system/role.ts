import http from "@/utils/request";

export const useRolePage = (params: any) => {
  return http.get("/v1/sysRole/page", { params });
};

export const useRoleList = () => {
  return http.get("/sys/role/list");
};

export const useRoleSave = (dataForm: any) => {
  let url: string = "/v1/sysRole/update";
  if (dataForm.id) {
    return http.put(url, dataForm);
  }
  url = "/v1/sysRole/add";
  return http.post(url, dataForm);
};

export const useRoleDel = (id: any) => {
  return http.delete("/v1/sysRole/delete?id=" + id);
};

export const useRoleStatus = (dataForm: any) => {
  return http.put("/v1/sysRole/updateStatus", dataForm);
};
