document.write('<script src="/js/common.js"></script>')

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
    addPromotionNextBtn();
    addPromotionPrevBtn();
})

function addPromotionNextBtn() {
    $(".direction-btn-box .next").addEventListener("click", promotionNextBtnHandler)
}

function addPromotionPrevBtn() {
    $(".direction-btn-box .prev").addEventListener("click", promotionPrevBtnHandler)
}

function clearPositionStyle(tag) {
    tag.classList.remove("prev");
    tag.classList.remove("current");
    tag.classList.remove("next");
}

function setPositionStyle(tag, style) {
    clearPositionStyle(tag);
    tag.classList.add(style)
}

function getNextElement(tag) {
    return tag === tag.parentElement.lastElementChild ?
        tag.parentElement.firstElementChild : tag.nextElementSibling;
}

function getPrevElement(tag) {
    return tag === tag.parentElement.firstElementChild ?
        tag.parentElement.lastElementChild : tag.previousElementSibling;
}

function promotionNextBtnHandler(evt) {
    //evt.preventDefault();
    let current = $(".img-box .current");
    clearPositionStyle(getPrevElement(current));
    setPositionStyle(current, "prev");
    current = getNextElement(current);
    setPositionStyle(current, "current");
    setPositionStyle(getNextElement(current), "next");
}

function promotionPrevBtnHandler(evt) {
    //evt.preventDefault();
    let current = $(".img-box .current");
    clearPositionStyle(getNextElement(current));
    setPositionStyle(current, "next");
    current = getPrevElement(current);
    setPositionStyle(current, "current");
    setPositionStyle(getPrevElement(current), "prev");
}

//setCurrent(tag): 해당 태그를 새로운 커런트로 바꾸어주고 주변을 정리한다.

function createMenu(response) {
    let html = ``;
    response.json().then(responseObject => {responseObject.children.forEach(main_menu =>{
        html = html + `
                <li>
                    <a href="#">${main_menu.title}</a>
                    <ul class="sub-menu">
                `
        main_menu.children.forEach(sub_menu => {
            html = html + `
                            <li>
                                <a href="#">${sub_menu.title}</a>
                            </li>
                        `
        })
        html = html + `
                        </ul>
                    </li>
                    `
    })

    //$(".menu").append(html).trigger("create");

    $("#gnb .menu").insertAdjacentHTML("afterbegin", html);
    });
}

function initEvents() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json' },
        callback: createMenu
    });
}

function login(response) {
    if (response.status == 200)
        window.location.replace("/");
    else {
        alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
    }
}

function loginHandler(evt) {
    evt.preventDefault();
    const loginForm = {
        "email": $("#member_id").value,
        "password": $("#pwd").value
    }

    const values = Object.values(loginForm);
    if (values.filter(value => isEmpty(value)).length > 0) {
        alert("아이디와 비밀번호를 모두 입력해주세요.");
        return;
    }

    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(loginForm),
        callback: login
    });
}

function logout(response) {
    if (response.status == 200) {
        window.location.replace("/");
    }
}

function logoutHandler(evt) {
    evt.preventDefault();

    fetchManager({
        url: '/api/users/logout',
        method: 'GET',
        headers: { 'content-type': 'application/json' },
        callback: logout
    });
}

function join(response) {
    if (response.status == 200) {
        alert("가입 완료되었습니다. 축하합니다!");
        window.location.replace("/");
    } else {
        response.json()
            .then(errorObjects => {
                let errorMessage = "";
                errorObjects.forEach(errorObject =>
                    errorMessage += errorObject.defaultMessage + "\n")
                return errorMessage;
            })
            .then(result => alert(result))
    }
}

function joinHandler(evt) {
    evt.preventDefault();
    let joinForm = {
        "email_id": $("#email_id").value,
        "email_domain": $("#email_domain").value,
        "password": $("#pw1").value,
        "passwordConfirm": $("#pw2").value,
        "name": $("#name").value,
        "cell1": $("#cell1").value,
        "cell2": $("#cell2").value,
        "cell3": $("#cell3").value
    };

    const values = Object.values(joinForm);
    if (values.filter(value => isEmpty(value)).length > 0) {
        alert("회원가입 양식을 모두 입력해주세요.");
        return;
    }

    joinForm.email = joinForm.email_id + '@' + joinForm.email_domain;
    joinForm.phoneNo = joinForm.cell1 + '-' + joinForm.cell2 + '-' + joinForm.cell3;

    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(joinForm),
        callback: join
    });
}