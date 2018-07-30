function appendCategories(categories) {
   let categoryHtml = ``;

    categories.forEach(function(mainCategory) {
        categoryHtml += `<li><a href="./side-dishs.html">` + mainCategory.name + `</a><ul class="sub-menu">`;

        mainCategory.subCategories.forEach(function(subCategory) {
                categoryHtml += `<li><a href="#">`;
                categoryHtml += subCategory.name
                categoryHtml += `</a></li>`
            });

        categoryHtml += `</ul></li>`;
    });

    console.log(categoryHtml);
    $("#categoryMenu").insertAdjacentHTML("afterbegin", categoryHtml);
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        return response.json()
    }).then((result) => {
        callback(result)
    })
}

function drawCategories() {
    fetchManager({
            url: '/api/categories',
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: appendCategories
        })
}

document.addEventListener("DOMContentLoaded", () => {
    drawCategories();
})
