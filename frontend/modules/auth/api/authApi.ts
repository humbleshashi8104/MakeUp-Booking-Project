import apiClient from "@/lib/apiClient";

export const loginUser = async (data: { email: string; password: string }) => {
  const response = await apiClient.post("/auth/login", data);
  return response.data;
};

export const signupUser = async (data: {
  name: string;
  email: string;
  password: string;
  phone: string;
}) => {
  const response = await apiClient.post("/users", data);
  return response.data;
};
