import { deleteRequest, get, post } from "@/api/action";

//自行修改
const prefix = "/base";

export const ${table.entityPath}Page = (data: any) =>
  post(prefix + "/${table.entityPath}/page", data, "application/json");

export const ${table.entityPath}Detail = (data: any) =>
  get(prefix + "/${table.entityPath}/detail", data);

export const ${table.entityPath}Save = (data: any) =>
  post(prefix + "/${table.entityPath}/save", data, "application/json");

export const ${table.entityPath}Update = (data: any) =>
  post(prefix + "/${table.entityPath}/update", data, "application/json");

export const ${table.entityPath}DeleteById = (data: any) =>
  deleteRequest(prefix + "/${table.entityPath}/delete", data);