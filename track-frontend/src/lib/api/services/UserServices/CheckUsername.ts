import { request } from "../../HTTPClient";

/**
 * Checks if an email is already registered.
 * @param username The email to check.
 * @returns A promise that resolves to true if the email is already registered, false otherwise.
 */
export async function checkUsername(username: string): Promise<boolean> {
    try {
        await request(`/user/check/username?username=${encodeURIComponent(username)}`);
        return false;
    } catch (err: any) {
        if (err.status === 409) {
            return true;
        }
        return false;
    }
}