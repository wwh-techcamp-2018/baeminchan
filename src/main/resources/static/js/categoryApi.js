document.write("<script src='/js/common.js'></script>")

function categoriesCallback(response) {
    response.json().then(result => {
        const categories = result.data;
        categories.forEach(category => {
            if (isAdminPage()) {
                createAdminCategoryTemplate(category);
            } else {
                createCategoryTemplate(category);
            }
        });
    });
}

function isAdminPage() {
    return window.location.pathname === "/admin.html";
}

function createCategoryTemplate(category) {
    const parentUl = $("#categories");

    let categoryTemplate = `<li>
        <a href="./side-dishs.html">${category.title}</a>
        <ul class="sub-menu">
        `;

    category.children.forEach(subCategory => {
        let subCategoryTemplate = `<li>
            <a href="${subCategory.cid}">${subCategory.title}</a>
        </li>
        `;
        categoryTemplate += subCategoryTemplate;
    });

    categoryTemplate += `</ul>
                            </li>`;
    parentUl.insertAdjacentHTML("beforeend", categoryTemplate);
}

function createAdminCategoryTemplate(category) {
    const parentUl = $("#categories");

    let categoryTemplate = `<li>
                                <span> ${category.cid} : ${category.title}</span>
                        <ul>`;

    category.children.forEach(subCategory => {
        let subCategoryTemplate = `<li>
                                    <span>${subCategory.cid} : ${subCategory.title}</span>
                               </li>`;

        categoryTemplate += subCategoryTemplate;
    });

    categoryTemplate += `</ul> 
                      </li>`;

    parentUl.insertAdjacentHTML("beforeend", categoryTemplate);
}

window.addEventListener("load", function (event) {
    getData("/api/category", categoriesCallback);
});