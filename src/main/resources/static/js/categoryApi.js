function $(selector) {
    return document.querySelector(selector);
}



function fetchManager({ url, method, body, headers,callback}) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        callback(response);
    })
}

function getCategories() {
    fetchManager({
        url: '/api/category',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: categoriesCallback
    })
}

function categoriesCallback(response){
    response.json().then((result)=>{
      if(result.message !== "success"){
        alert("페이지를 새로고침 해주세요.");
      }
      
      const categories = result.data;
      categories.forEach(category => {
          createCategoryTemplate(category);
      });
    })
}

function createCategoryTemplate(category) {
    const parentUl = $("#categories");
    let categoryTemplate = 
    `<li>
        <a href="./side-dishs.html">${category.title}</a>
        <ul class="sub-menu">
        `;

    category.children.forEach(subCategory => {
        let subCategoryTemplate = 
        `<li>
            <a href="${subCategory.cid}">${subCategory.title}</a>
        </li>
        `;
        categoryTemplate += subCategoryTemplate;
    })

    categoryTemplate += `</ul> 
                    </li>`;
    const categoryNode = document.createRange().createContextualFragment(categoryTemplate);
    parentUl.appendChild(categoryNode);
}
