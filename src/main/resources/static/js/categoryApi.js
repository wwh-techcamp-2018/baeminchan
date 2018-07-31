function $(selector) {
  return document.querySelector(selector);
}

function fetchManager({ url, method, body, headers, callback }) {
  fetch(url, { method, body, headers, credentials: "same-origin" }).then(
    response => {
      callback(response);
    }
  );
}

function getCategories(url, callback) {
  fetchManager({
    url: url,
    method: "GET",
    headers: { "content-type": "application/json" },
    callback: callback
  });
}

function categoriesCallback(response) {
  response.json().then(result => {
    const categories = result.data;
    categories.forEach(category => {
      createCategoryTemplate(category);
    });
  });
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

window.addEventListener("load", function(event) {
  getCategories("/api/category", categoriesCallback);
});
