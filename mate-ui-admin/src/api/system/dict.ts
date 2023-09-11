import http from "@/utils/request";

export const useDictList = () => {
  return http.get("/sys/dict/page");
};

export const useDictSave = (dataForm: any) => {
  if (dataForm.id) {
    return http.put("/sys/dict/update", dataForm);
  } else {
    return http.post("/sys/dict/add", dataForm);
  }
};

export const useDictItemList = (params: any) => {
  return http.get("/sys/dictItem/page",  { params });
};

export const useDictItemSave = (dataForm: any) => {
  if (dataForm.id) {
    return http.put("/sys/dictItem/update", dataForm);
  } else {
    return http.post("/sys/dictItem/add", dataForm);
  }
};

