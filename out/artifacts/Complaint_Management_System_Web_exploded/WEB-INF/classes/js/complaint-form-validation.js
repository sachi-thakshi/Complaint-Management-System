function validateComplaintForm() {
    const subject = document.getElementById('subject').value.trim();
    const description = document.getElementById('description').value.trim();

    if (subject === "" || description === "") {
        alert("Please fill in both Subject and Description.");
        return false;
    }
    return true;
}