document.addEventListener("DOMContentLoaded", function(evt) {
    getRootCategories();
    $(".category-create").addEventListener("click", function(evt) {
        toggleInputDisplay();
        updateInputMethod = "POST";
    });
    $(".update-holder").addEventListener("click", function(evt) {
        toggleInputDisplay();
    })
    $(".update-holder input").addEventListener("click", function(evt) {
        evt.stopPropagation();
    });
    $(".update-holder input").addEventListener("keydown", function(evt) {
        if (evt.keyCode == 13) {
            console.log(evt.target.value);
            const object = {
                "title": evt.target.value,
                "parent": null
            };
            fetchManager({
                url: $(".category-create").getAttribute("action"),
                method: updateInputMethod,
                headers: {"content-type": "application/json"},
                body: JSON.stringify(object),
                callback: function(result) {
                    getCategoriesByUrl($(".category-create").getAttribute("action"));
                }
            });

            toggleInputDisplay();
            return false;
        }
    });
});

let updateInputMethod = "POST";

function getRootCategories() {
    getManager({
        url: "/admin/categories/",
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawCategories
    });

    $(".category-create").setAttribute("action", "/admin/categories/");
}

function getCategoriesByUrl(url) {
    getManager({
        url,
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawCategories
    });
    $(".category-create").setAttribute("action", url);
}

function drawCategories(obj) {
    let categories;
    if(Array.isArray(obj)) {
        categories = obj;
    } else {
        categories = obj.categories;
    }

    $(".container").innerHTML = "";
    for(category of categories) {
        const htmlCode = `
            <div class="category-container" action="/admin/categories/${category.id}">
                <span class="category-title">
                    ${category.title}
                </span>
                <div class="category-delete">DELETE</div>
                <div class="category-update">UPDATE</div>
            </div>
        `;
        $(".container").innerHTML += htmlCode;
    }
    const categoryList = $_all(".category-container");
    for (let i = 0; i < categoryList.length; i++) {
        categoryList[i].addEventListener("click", function (evt) {
            const action = this.getAttribute("action");
            if(evt.target.classList.contains("category-delete")) {
                fetchManager({url: action,
                    method: "DELETE",
                    body: "",
                    headers: {"Content-type": "application/json"},
                    callback: deleteNode.bind(this)
                })
            } else if(evt.target.classList.contains("category-update")) {
                updateInputMethod = "PUT";
                $(".update-holder input").value = this.querySelector(".category-title").textContent.trim();
                toggleInputDisplay();
                $(".category-create").setAttribute("action", action);

            } else {
                getCategoriesByUrl(action);
            }

        });

    }
}

function deleteNode(result) {
    if(!result) this.parentNode.removeChild(this);
}

function toggleInputDisplay() {
    if($(".update-holder").style.display == "block") {
        $(".update-holder").style.display = "none";
        $(".update-holder input").value = "";
    } else {
        $(".update-holder").style.display = "block";
    }
}