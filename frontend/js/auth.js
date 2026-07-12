function getCurrentUser() {

    const storedUser = localStorage.getItem("user");

    if (!storedUser) {
        return null;
    }

    try {
        return JSON.parse(storedUser);
    } catch (error) {
        return null;
    }
}

function isAuthenticated() {
    return Boolean(localStorage.getItem("accessToken"));
}

function requireAuthentication() {

    if (!isAuthenticated()) {
        window.location.href = "login.html";
    }
}

function clearAuthentication() {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("user");
}

async function logoutUser() {

    const refreshToken = localStorage.getItem("refreshToken");

    try {

        if (refreshToken) {
            await apiRequest(
                `/auth/logout?refreshToken=${encodeURIComponent(refreshToken)}`,
                {
                    method: "POST"
                }
            );
        }

    } catch (error) {
        console.error(error);
    } finally {
        clearAuthentication();
        window.location.href = "login.html";
    }
}

function displayCurrentUser() {

    const user = getCurrentUser();

    if (!user) {
        return;
    }

    const profileRole =
        document.getElementById("profileRole");

    const profileName =
        document.getElementById("profileName");

    if (profileRole) {
        profileRole.textContent = user.role || "";
    }

    if (profileName) {
        profileName.textContent =
            user.fullName || user.email || "";
    }
}