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

    $("#category-type").addEventListener("change", (e) => {
        if(e.target.value == 1) {
            $("#parent-select").style = "display:visible";
            return;
        }
        $("#parent-select").style = "display:none";
    })
}

function onSuccess(response) {
    response.json().then((response) => {
    console.log(response);
        response.forEach((category) => {
            // 추가
            const html1 = `<tr><td>` + category.priority
                         + `</td><td>` + category.name
                         + `</td><td>`
                         + `</td><td>` + category.creator
                         + `</td><td>` + category.createdTime
                         + `</td></tr>`;

            const html2 = category.subCategories.reduce((prevSubCategory, nextSubCategory) => {
                return prevSubCategory
                              + `<tr><td>` + nextSubCategory.priority
                              + `</td><td>`
                              + `</td><td>` + nextSubCategory.name
                              + `</td><td>` + nextSubCategory.creator
                              + `</td><td>` + nextSubCategory.createdTime
                              + `</td></tr>`;
            }, ``);

            $("#category-table").insertAdjacentHTML("beforeend", html1 + html2);

            const html3 = ` <option value=` + category.id + `>` + category.name + `</option>`;

            $("#parent-name").insertAdjacentHTML("beforeend", html3);

        });
    });
}

function alertError() {
    alert("관리자에게 문의바랍니다.");
}