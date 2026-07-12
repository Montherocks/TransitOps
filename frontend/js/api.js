async function apiRequest(endpoint, options = {}) {

    const accessToken = localStorage.getItem("accessToken");

    const headers = {
        ...options.headers
    };

    if (options.body && !(options.body instanceof FormData)) {
        headers["Content-Type"] = "application/json";
    }

    if (accessToken) {
        headers.Authorization = `Bearer ${accessToken}`;
    }

    const response = await fetch(
        `${API_BASE_URL}${endpoint}`,
        {
            ...options,
            headers
        }
    );

    if (response.status === 401) {

        const refreshed = await refreshAccessToken();

        if (refreshed) {
            return apiRequest(endpoint, options);
        }

        clearAuthentication();
        window.location.href = "index.html";
        throw new Error("Your session has expired.");
    }

    if (response.status === 204) {
        return null;
    }

    const contentType = response.headers.get("content-type");

    let data;

    if (contentType && contentType.includes("application/json")) {
        data = await response.json();
    } else {
        data = await response.text();
    }

    if (!response.ok) {
        throw new Error(
            data?.message ||
            data?.error ||
            data ||
            "Request failed."
        );
    }

    return data;
}

async function refreshAccessToken() {

    const refreshToken = localStorage.getItem("refreshToken");

    if (!refreshToken) {
        return false;
    }

    try {

        const response = await fetch(
            `${API_BASE_URL}/auth/refresh`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    refreshToken
                })
            }
        );

        if (!response.ok) {
            return false;
        }

        const data = await response.json();

        localStorage.setItem(
            "accessToken",
            data.accessToken
        );

        if (data.refreshToken) {
            localStorage.setItem(
                "refreshToken",
                data.refreshToken
            );
        }

        return true;

    } catch (error) {
        return false;
    }
}