const API_BASE=window.location.origin;

async function handleResponse(response) {
    const contentType = response.headers.get("Content-Type");
    const data = contentType && contentType.includes("application/json")
        ? await response.json()
        : await response.text();
    if (!response.ok) {
        throw new Error(data.message || data || "API error");
    }
    return data;
}

export async function post(path, data) {
    const response = await fetch(`${API_BASE}${path}`, {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(data),
    });
    return handleResponse(response);
}

export async function get(path) {
    const response = await fetch(`${API_BASE}${path}`, {
        method: "GET",
    });
    return handleResponse(response);
}

export async function put(path, data) {
    const response = await fetch(`${API_BASE}${path}`, {
        method: "PUT",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(data),
    });
    return handleResponse(response);
}

export async function del(path) {
    const response=await fetch(`${API_BASE}${path}`, {
        method: "DELETE",
    });
    return handleResponse(response);
}