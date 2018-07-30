
function initEvents(){
    fetchManager({
        url: "/category",
        method: "get",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        success: getCategorySuccess,
        error: getCategoryFailed
    })
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function getCategorySuccess(response){
    console.log(response);
    if(response.status == 200) {
        createMenu(response);
    }
    // TODO error 처리
}

function getCategoryFailed(error){
    alert(error);
}

function createMenu(response) {
    let html = ``;
    response.json().then(responseObject => {responseObject.children.forEach(main_menu =>{
        html = html + `
                <li>
                    <a href="#">${main_menu.name}</a>
                    <ul class="sub-menu">
                `
        main_menu.children.forEach(sub_menu => {
            html = html + `
                            <li>
                                <a href="#">${sub_menu.name}</a>
                            </li>
                        `
        })
        html = html + `
                        </ul>
                    </li>
                    `
    })

    $("#gnb .menu").insertAdjacentHTML("afterbegin", html);
    });
}