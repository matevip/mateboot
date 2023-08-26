import http from "@/utils/request";

export const useOrgList = () => {
  return http.get("/sys/dept/list");
};

export const useOrgSave = (dataForm: any) => {
  return http.post("/sys/org/save", dataForm);
};

export const useOrgDel = (ids: any) => {
  return http.post("/sys/org/del", ids);
};
