function validateRegisterForm() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (username === "" || password === "") {
        alert("Please fill in all fields.");
        return false;
    }
    if (password.length < 4) {
        alert("Password must be at least 4 characters.");
        return false;
    }
    return true;
}
