import { request } from "../../HTTPClient"
import type { UserRegistration } from "../../interface/UserRegistration";

interface RegistrationResponse {
    userId: string;
    success: boolean;
    message?: string;
}

/**
 * Registers a new user.
 */
export async function createUser(UserRegistration: UserRegistration): Promise<RegistrationResponse> {
    try {
        return await request(`/user/register`, {
            method: 'POST',
            body: JSON.stringify(UserRegistration)
        });
    } catch (err) {
        throw err;
    }
}