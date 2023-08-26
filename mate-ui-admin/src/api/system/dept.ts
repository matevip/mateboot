import http from "@/utils/request";

export const useDeptList = () => {
  return http.get("/sys/dept/list");
};

export const useDeptSave = (dataForm: any) => {
  if (dataForm.id) {
    return http.put("/sys/dept/update", dataForm);
  } else {
    return http.post("/sys/dept/add", dataForm);
  }
};

export const useDeptDel = (ids: any) => {
  return http.post("/sys/org/del", ids);
};
