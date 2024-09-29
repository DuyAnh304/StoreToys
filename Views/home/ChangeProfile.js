// JavaScript Document
const userUrl = 'http://localhost:8080/StoreToys-API/user/';
const inputName = document.querySelector(".box-name");
const inputPhone = document.querySelector(".box-phone");
const inputAddress = document.querySelector(".box-adrress");
const inputSex = document.querySelector(".box-sex");
const inputEmail = document.querySelector(".box-email");
const updateBtn = document.querySelector(".btn");
start();
function start(){
	getProfile();
}
function getProfile(){
	let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${tokens.accessToken}`
        }
    }
	fetch(`${userUrl}${tokens.userId}`, options)
	.then(function(res){
		return res.json();
	})
	.then(function(datas){
		console.log(datas)
		renderProfile(datas.data);
	})
}
function renderProfile(data){
	inputName.value = data.fullname;
	inputPhone.value = data.phone;
	inputAddress.value = data.address;
	inputSex.value = data.sex;
	inputEmail.value = data.email;
}
updateBtn.addEventListener("click", function(){
	let newName = inputName.value;
	let newPhone = inputPhone.value;
	let newAddress = inputAddress.value;
	let newSex = inputSex.value;
	let newEmail = inputEmail.value;
	let userProfile = {
		fullname: newName,
		phone: newPhone,
		address: newAddress,
		sex: newSex,
		email: newEmail,
	}
	updateProfile(userProfile);
})
function updateProfile(profile){
	let option = {
		method: 'PUT',
        headers:{
			'Authorization': `Bearer ${tokens.accessToken}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(profile)
	}
	fetch(`${userUrl}${tokens.userId}`, option)
	.then(function(res){
        res.json();
    })
    .then(function(){
		alert('Cập nhật thành công');
        window.location.reload();
    })
	.catch(error => console.error(error));
}