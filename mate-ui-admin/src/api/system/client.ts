import http from "@/utils/request";

export const useClientPage = (params: any) => {
  return http.get("/sys/client/page", { params });
};

export const useClientList = () => {
  return http.get("/sys/client/list");
};

export const useClientSave = (dataForm: any) => {
  if (dataForm.id) {
    return http.put("/sys/client/update", dataForm);
  } else {
    return http.post("/sys/client/add", dataForm);
  }
};

export const useClientDel = (id: any) => {
  return http.delete("/sys/client/delete?id=" + id);
};