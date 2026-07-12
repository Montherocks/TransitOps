const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async function (e) {

    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;
    const error = document.getElementById("errorMessage");

    if (!role) {
        error.textContent = "Please select a role.";
        return;
    }

    error.textContent = "";

    try {

        const response = await fetch(`${API_BASE_URL}/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email,
                password
            })
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(
                data.message ||
                data.error ||
                "Invalid email or password."
            );
        }

        // Optional: Verify selected role matches backend role
        const selectedRole = role
            .toUpperCase()
            .replace(/\s+/g, "_");

        if (selectedRole !== data.role) {
            error.textContent =
                "Selected role does not match your account.";
            return;
        }

        // Store authentication tokens
        localStorage.setItem(
            "accessToken",
            data.accessToken
        );

        localStorage.setItem(
            "refreshToken",
            data.refreshToken
        );

        // Store user information
        localStorage.setItem(
            "user",
            JSON.stringify({
                userId: data.userId,
                fullName: data.fullName,
                email: data.email,
                role: data.role
            })
        );

        window.location.href = "dashboard.html";

    } catch (err) {

        error.textContent = err.message;

    }

});