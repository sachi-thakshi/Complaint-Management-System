function validateLoginForm() {
    const usernameField = document.getElementById('username');
    const passwordField = document.getElementById('password');

    const username = usernameField.value.trim();
    const password = passwordField.value.trim();

    if (username === "" && password === "") {
        alert("Please fill in both username and password.");
        usernameField.focus();
        return false;
    }

    if (username === "") {
        alert("Please enter your username.");
        usernameField.focus();
        return false;
    }

    if (password === "") {
        alert("Please enter your password.");
        passwordField.focus();
        return false;
    }

    return true;
}
