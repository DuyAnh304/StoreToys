// JavaScript Document
const name = document.getElementById("name");
const gmail = document.getElementById("gmail");
const userUrl = 'http://localhost:8080/StoreToys-API/user/';
var storedToken = JSON.parse(localStorage.getItem('tokens'));
const logoutBtn = document.querySelector(".logout-btn");

start();

function start(){
	getUserByID(storedToken.userId);
}

function getUserByID(id){
    let urlWithID = `${userUrl}${id}`;
    let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        }
    }
    fetch(urlWithID, options)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        name.textContent = `Xin chào ${datas.data.fullname}`;
        gmail.textContent = `Gmail của bạn: ${datas.data.email}`;
    })
    .catch(error => console.log(error));
}

logoutBtn.addEventListener("click", function(){
	localStorage.removeItem('tokens');
    window.location.href = '../login/Login.html';
})