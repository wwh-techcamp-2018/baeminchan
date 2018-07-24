function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const answerBtn = $(".join_form_box .btn_area .btn");
    if(answerBtn === null) return;
    answerBtn.addEventListener("click", registerUserHandler);
}

function fetchManager({ url, method, body, headers}) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        if(response.status === 201){
            console.log(response)
            window.location.replace("http://localhost:8080/")
            return
        }
        return response.json()
    }).then((result) => {
        alert(result.data.validationErrorList[0].errorMessage);
    })
}

function registerUserHandler(evt) {
    evt.preventDefault();
    const email = $("#email_id").value+"@"+$("#email_domain").value;
    const password = $("#pw1").value;
    const passwordConfirm = $("#pw2").value;
    const name = $("#name").value;
    const phoneNo = $("#cell1").value+$("#cell2").value+$("#cell3").value;
    
    $("#pw1").value = "";
    $("#pw2").value = "";
    

    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password,passwordConfirm,name,phoneNo})
    })
}

function goNext(evt){

}