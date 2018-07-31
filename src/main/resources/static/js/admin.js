document.write('<script src="/js/common.js"></script>')

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
    addHandler();
})

function addHandler() {
    const root_div = $("#category_list")
    if(root_div === null) return;
    root_div.addEventListener("click",categoryDivEventHandler);
}

function errorHandler(response) {
    if(response.status === 401){
        alert("인증오류입니다");
        location.replace("/login.html");
        return;
    }
    if(response.status === 403){
        alert("인가오류입니다");
        location.replace("/");
        return;
    }
}

function createAdminMenu(response) {
    errorHandler(response);
    response.json().then(root => {
        let html = ``;
        root.children.forEach(upper_menu => {
            html += `
                <ul id="category_list_${upper_menu.id}">
                    <li>
                        <input data-category-id="${upper_menu.id}" id="menu_${upper_menu.id}" style="display: inline-block" 
                                value="${upper_menu.title}" placeholder="${upper_menu.title}"/>
                        <button data-category-id="${upper_menu.id}" class="update">수정</button>
                        <button data-category-id="${upper_menu.id}" class="delete_upper">삭제</button>
                    </li>
                `;
            upper_menu.children.forEach(lower_menu => {
                html += `
                <li id="lower_menu_li_${lower_menu.id}">
                    <input id="menu_${lower_menu.id}" value="${lower_menu.title}" placeholder="${lower_menu.title}"/>
                    <button data-category-id="${lower_menu.id}" class="update">수정</button>
                    <button data-category-id="${lower_menu.id}" class="delete_lower">삭제</button>
                </li>
                `;
            })
            html += `
                <li id="create_lower_menu_li_${upper_menu.id}">
                    <input id="lower_menu_${upper_menu.id}"/>
                    <button data-category-id="${upper_menu.id}" class="create_lower">생성</button>
                </li>
            </ul>
            `;
        })
        $("#category_list").insertAdjacentHTML("afterbegin", html);
    })

}

function initEvents() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json' },
        callback: createAdminMenu
    });
}

function addUpperCategory(response) {
    if(response.status === 400) {
        alert("카테고리명을 채워주세요");
        return;
    }
    errorHandler(response);

    response.json().then(category => {
        const html = `
        <ul id="category_list_${category.id}">
            <li>
                <input data-category-id="${category.id}" value="${category.title}" id="menu_${category.id}" 
                style="display: inline-block" placeholder="${category.title}"/>
                <button data-category-id="${category.id}" class="update">수정</button>
                <button data-category-id="${category.id}" class="delete_upper">삭제</button>
            </li>
            <li id="create_lower_menu_li_${category.id}">
                <input id="menu_${category.id}"/>
                <button data-category-id="${category.id}" class="create_lower">생성</button>
            </li>
        </ul>
        `
        $("#category_list").insertAdjacentHTML("beforeend",html);
    })
}

function addUpperCategoryHandler(evt) {
    evt.preventDefault();

    const categoryForm = {
        "title" : $("#category_title").value,
        "parentId" : 0
    };

    $("#category_title").value = "";

    fetchManager({
        url: '/api/admin/category',
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(categoryForm),
        callback: addUpperCategory
    });
}

function addLowerCategory(response) {
    if(response.status === 400) {
        alert("카테고리명을 채워주세요");
        return;
    }
    errorHandler(response);

    response.json().then(category => {
        const html = `
            <li id="lower_menu_li_${category.id}">
                <input id="menu_${category.id}" value="${category.title}" placeholder="${category.title}"/>
                <button data-category-id="${category.id}" class="update">수정</button>
                <button data-category-id="${category.id}" class="delete_lower">삭제</button>
            </li>
            `;
        $("#create_lower_menu_li_"+category.parent.id).insertAdjacentHTML("beforebegin", html);
    })
}

function deleteLowerCategory(response) {
    errorHandler(response);
    response.json().then(category => {
        $("#lower_menu_li_"+category.id).remove();
    })
}

function deleteUpperCategory(response) {
    errorHandler(response);
    response.json().then(category => {
        $("#category_list_"+category.id).remove();
    })
}


function deleteUpperCategoryHandler(evt) {
    fetchManager({
        url: '/api/admin/category/'+evt.target.dataset.categoryId,
        method: 'DELETE',
        headers: { 'content-type': 'application/json' },
        callback: deleteUpperCategory
    });
}

function deleteLowerCategoryHandler(evt) {

    fetchManager({
        url: '/api/admin/category/'+evt.target.dataset.categoryId,
        method: 'DELETE',
        headers: { 'content-type': 'application/json' },
        callback: deleteLowerCategory
    });
}

function createLowerCategoryHandler(evt) {
    const parentId = evt.target.dataset.categoryId;
    const categoryForm = {
        "parentId" : parentId,
        "title" : $("#lower_menu_"+parentId).value
    };

    $("#lower_menu_"+parentId).value = "";

    fetchManager({
        url: '/api/admin/category',
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(categoryForm),
        callback: addLowerCategory
    });
}

function updateCategory(response) {
    errorHandler(response);
    response.json().then(category => {
        $("#menu_"+category.id).value = category.title;
        $("#menu_"+category.id).placeholder = category.title;
    })

}

function updateCategoryHandler(evt) {
    evt.preventDefault();

    const id = evt.target.dataset.categoryId;
    const title = $("#menu_"+id);

    console.log(title.placeholder);
    if(title.value === title.placeholder) {
        alert("카테고리명을 수정하세요");
        return;
    }

    const categoryForm = {
        "title" : title.value,
        "id" : id
    };

    fetchManager({
        url: '/api/admin/category/'+id,
        method: 'PUT',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(categoryForm),
        callback: updateCategory
    });
}

function categoryDivEventHandler(evt) {
    evt.preventDefault();

    const target_class = evt.target.className;

    if(target_class === "create_lower"){
        createLowerCategoryHandler(evt);
        return;
    }
    if(target_class === "delete_lower"){
        deleteLowerCategoryHandler(evt);
        return;
    }
    if(target_class === "delete_upper") {
        deleteUpperCategoryHandler(evt);
        return;
    }
    if(target_class == "update"){
        updateCategoryHandler(evt);
        return;
    }
}