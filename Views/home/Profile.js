// JavaScript Document
const name = document.getElementById("name");
const gmail = document.getElementById("gmail");
const userName = user.fullname;
const userGmail = user.email;
const logoutBtn = document.querySelector(".logout-btn");

name.textContent = `Xin chào ${userName}`;
gmail.textContent = `Gmail của bạn: ${userGmail}`;
logoutBtn.addEventListener("click", function(){
	localStorage.removeItem('user');
    window.location.href = '../login/Login.html';
})