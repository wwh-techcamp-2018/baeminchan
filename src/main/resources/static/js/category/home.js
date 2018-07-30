document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccess,
        errCallback: alertError
    })
}

function onSuccess(response) {
    response.json().then((response) => {
    console.log(response);
        response.forEach((category) => {
            // 추가
            const html1 = `<li>
                              <a href="./side-dishs.html">` + category.name + `</a>
                              <ul class="sub-menu">`;


            const html2 = category.subCategories.reduce((prevSubCategory, nextSubCategory) => { return prevSubCategory + `<li><a href="#">` + nextSubCategory.name + `</a></li>`}, ``);

            const html3 = `</ul></li>`;
            $("#menu").insertAdjacentHTML("beforeend", html1 + html2 + html3);
        });
    });
}

function alertError() {
    alert("요기요를 이용해주세요...죄송합니다.");
}