import http from "@/utils/request";

export const useUserInfo = () => {
  return http.get("/sys/user/info");
};

export const updatePassword = (data: any) => {
  return http.post("/sys/user/password", data);
};

export const useUser = (id: number) => {
  return http.get("/sys/user/" + id);
};

export const useUserSubmit = (dataForm: any) => {
  if (dataForm.id) {
    return http.put("/sys/user", dataForm);
  } else {
    return http.post("/sys/user", dataForm);
  }
};

export const userDelApi = (ids: any) => {
  return http.post("/sys/user/del", ids);
};

export const userSaveApi = (ids: any) => {
  return http.post("/sys/user/save", ids);
};


export const userPageApi = (params: any) => {
  return http.get("/sys/user/page", { params });
};
