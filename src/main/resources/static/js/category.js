function createMenu(response) {
  let html = ``;
  response.json().then(responseObject => {
    responseObject.children.forEach(main_menu => {
      html += `<li><a href="#">${main_menu.title}</a><ul class="sub-menu">`;
      main_menu.children.forEach(sub_menu => {
        html += `<li><a href="#">${sub_menu.title}</a></li>`;
      });
      html += `</ul></li>`;
    });
    $("#gnb .menu").insertAdjacentHTML("afterbegin", html);
  });
}

function loadCategory() {
  fetchManager({
    url: "/api/categories",
    method: "GET",
    headers: { "content-type": "application/json" },
    callback: createMenu
  });
}
