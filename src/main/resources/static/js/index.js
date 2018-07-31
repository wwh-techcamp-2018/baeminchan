function createCategoryDomString(e) {
    function brandClass() {
        return e.title === '브랜드관' ? 'class="brand" ' : '';
    }

    return `<li ${brandClass()}data-id="${e.id}"><a href="side-dishs.html">${e.title}</a><ul class="sub-menu"></ul></li>`;
}

function initializeTopCategories() {
    fetchManager({
        url: "/api/category",
        method: "GET",
        onSuccess: ({json}) => {
            json.data.forEach((e) => {
                let menu = createCategoryDomString(e);
                selector("#gnb ul.menu").insertAdjacentHTML("beforeend", menu);
            });
        }
    });
}

function loadSubMenus(pNode) {
    if (pNode.lastChild.innerHTML !== '') return;

    fetchManager({
        url: "/api/category/" + pNode.getAttribute("data-id"),
        method: "GET",
        onSuccess: ({json}) => {
            pNode.lastChild.innerHTML = '';
            json.data.forEach((c) => {
                let subMenu = `<li><a href="#">${c.title}</a></li>`;
                pNode.lastChild.insertAdjacentHTML("beforeend", subMenu);
            });
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    initializeTopCategories();
    selector('#gnb ul.menu').addEventListener('mouseover', (e) => {
        let pNode = e.target.parentNode;
        if (pNode.hasAttribute("data-id")) loadSubMenus(pNode);
    });
});
