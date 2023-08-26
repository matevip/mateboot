import http from "@/utils/request";

export const useRolePage = (params: any) => {
  return http.get("/sys/role/page", { params });
};

export const useRoleList = () => {
  return http.get("/sys/role/list");
};

export const useRoleSave = (dataForm: any) => {
  if (dataForm.id) {
    return http.put("/sys/role/update", dataForm);
  } else {
    return http.post("/sys/role/add", dataForm);
  }
};

export const useRoleDel = (id: any) => {
  return http.delete("/sys/role/delete?id=" + id);
};

export const useRoleStatus = (dataForm: any) => {
  return http.put("/sys/role/updateStatus", dataForm);
};
