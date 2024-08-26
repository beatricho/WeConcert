function showSection(sectionId) {
    document.querySelector('.welcome-page').style.display = 'none';
    document.querySelectorAll('.section').forEach(section => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}

function goBack() {
    document.querySelectorAll('.section').forEach(section => {
        section.style.display = 'none';
    });
    document.querySelector('.welcome-page').style.display = 'block';
}
