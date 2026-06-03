const BASE_URL = import.meta.env.VITE_API_URL;

export async function request(path: string, options: RequestInit = {}) {
    const res = await fetch(`${BASE_URL}${path}`, {
        headers: {
            'Content-Type': 'application/json',
            ...(options.headers || {})
        },
        ...options
    });

    if (!res.ok) {
        const errorText = await res.text();
        const error: any = new Error(errorText || 'Request failed');
        error.status = res.status;
        throw error;
    }

    if (res.status === 204) return null;

    const text = await res.text();
    return text ? JSON.parse(text) : null;
}