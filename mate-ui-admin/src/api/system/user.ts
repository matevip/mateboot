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
    return http.put("/sys/user/update", dataForm);
  } else {
    return http.post("/sys/user/add", dataForm);
  }
};

export const userDelApi = (id: any) => {
  return http.delete("/sys/user/delete?id=" + id);
};

export const userSaveApi = (ids: any) => {
  return http.post("/sys/user/save", ids);
};


export const userPageApi = (params: any) => {
  return http.get("/sys/user/page", { params });
};

export const useGetRoleApi = (userId: any) => {
  return http.get("/sys/user/getRole", { params: { userId } });
};
