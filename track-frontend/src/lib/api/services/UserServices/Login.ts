import { request } from "../../HTTPClient"
import type { UserLogin } from "../../interface/UserLogin";

export async function loginUser(userLogin: UserLogin) {
    try {
        return await request(`user/login`, {
            method: "POST",
            body: JSON.stringify(userLogin),
        });
    } catch (err) {
        throw err;
    }
}