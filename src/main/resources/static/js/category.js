document.addEventListener('DOMContentLoaded', () =>{
    fetchManager({
      url: '/api/categories',
      method: 'GET',
      headers: { 'content-type': 'application/json'},
      callback: appendAnswer
    });
}, false);

function appendAnswer(data) {
  const outer = ({name}, subMenu) => `<li> <a href="#">${name}</a><ul class="sub-menu"> ${subMenu} </ul></li>`;
  const inner = ({name})=> `<li><a href="#">${name}</a></li>`;

  let resultHTML = '';
  data.forEach(function(topMenu) {
    let appendhtml ='';
    topMenu.children.forEach(function(subMenu) {
      appendhtml += inner(subMenu);
    });
   resultHTML += outer(topMenu, appendhtml);
  });
  document.querySelector("#nav-menu").insertAdjacentHTML('afterbegin', resultHTML);
}