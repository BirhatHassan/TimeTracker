import { request } from "../../HTTPClient";

export async function getMyInfo() {
    return await request(`/auth/me`, {
        method: "GET",
        credentials: "include",
    });
}