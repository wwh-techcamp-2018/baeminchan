function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents();
})

function initEvents() {
    const answerBtn = $(".submit-write .btn");
    if(answerBtn === null) return;
    answerBtn.addEventListener("click", registerAnswerHandler);


    let deleteBtnList = document.querySelectorAll(".qna-comment-slipp-articles .article");
    deleteBtnList.forEach((deleteBtn) => {
        deleteBtn.addEventListener("click", registerDeleteHandler);
    });
}

function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        if (!response.ok) {
            throw {"message": "failed"}
        }
        return response.json()
    }).then((result) => {
        callback(result)
    }).catch(() => {});
}

function registerAnswerHandler(evt) {
    evt.preventDefault();
    const contents = $(".submit-write textarea").value;
    $(".submit-write textarea").value = "";
    const questionId = $(".submit-write input").value;

    fetchManager({
        url: '/api/questions/' + questionId + '/answers',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({contents}),
        callback: appendAnswer
    })
}

function appendAnswer({id, contents, question, writer, createdDate}) {
    const html = `
        <article class="article answer" data-id="${id}">
            <div class="article-header">
                <div class="article-header-thumb">
                    <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
                </div>
                <div class="article-header-text">
                    <a href="#" class="article-author-name">${writer.name}</a>
                    <div class="article-header-time">${createdDate}</div>
                </div>
            </div>
            <div class="article-doc comment-doc">
                ${contents}
            </div>
            <div class="article-util">
            <ul class="article-util-list">
                <li>
                    <a class="link-modify-article" href="/api/questions/${question.id}/answers/${id}">수정</a>
                </li>
                <li>
                    <form class="delete-answer-form" action="/api/questions/${question.id}/answers/${id}" method="POST">
                        <input type="hidden" name="answer-id" value="${id}">
                        <button type="submit" class="delete-answer-button" data-id="${id}">삭제</button>
                    </form>
                </li>
            </ul>
            </div>
        </article> `

    $(".submit-write").insertAdjacentHTML("beforebegin", html);
    const deleteBtn = $(`.answer[data-id="${id}"] .delete-answer-form button`);
    deleteBtn.addEventListener("click", registerDeleteHandler);
}

function registerDeleteHandler(evt) {
    evt.preventDefault();
    const questionId = $(".submit-write input").value;
    const answerId = event.target.getAttribute('data-id');

    const deleteAnswer = function () {
        const selector = `.answer[data-id="${answerId}"]`;
        const target = $(selector);
        target.parentNode.removeChild(target);
    }

    fetchManager({
        url: '/api/questions/' + questionId + '/answers/' +  answerId,
        method: 'DELETE',
        headers: { 'content-type': 'application/json'},
        callback: deleteAnswer
    })
}