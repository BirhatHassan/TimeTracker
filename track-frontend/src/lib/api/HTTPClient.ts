const BASE_URL = import.meta.env.BASE_URL;

export async function request(path: string, options: RequestInit = {}) {
    const res = await fetch(`${BASE_URL}${path}`, {
        headers: {
            'Content-Type': 'application/json',
            ...(options.headers || {})
        },
        ...options
    });

    if (!res.ok) {
        throw new Error(await res.text());
    }

    return res.json();
}