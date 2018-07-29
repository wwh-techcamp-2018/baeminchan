
function logoutHandler() {

    fetch('/users/logout', {
        method: 'get',
        headers: {'content-type': 'application/json'},
        credentials: 'same-origin'
    })
    .then(response => {
        if (!response.ok) {
            alert("로그아웃 실패");
            return location.href = "/index";
        }
        alert("로그아웃 성공");
        location.href = "/";
    })
    .catch(error => {
        location.reload();
    });
}
