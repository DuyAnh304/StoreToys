// JavaScript Document
const userUrl = 'http://localhost:8080/StoreToys-API/user/';
const userList = document.getElementById('user-list');
const modal = document.querySelector('.js-modal');
const inputFullname = document.getElementById('fullname');
const inputEmail = document.getElementById('email');
const inputPhone = document.getElementById('phone');
const inputAddress = document.getElementById('address');
const inputGender = document.getElementById('sex');
const btnConfirmUpdate = document.getElementById('update');
var storedToken = JSON.parse(localStorage.getItem('tokens'));
var index = 0;
var ID = 0;

function start(){   
    getUsers();
}
start();

function getUsers(){
    let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        }
    }
    fetch(userUrl,options)
    .then(function(res){
        return res.json();
    })  
    .then(function(datas) {
        index = 0;
        var htmls = datas.data.map(renderUser);
        var html = htmls.join('');
        userList.innerHTML = html;
    })
    .catch(error => console.log(error));
}

function renderUser(data){
    let stt = ++index;
    return `<tr>
                <th scope="row">${stt}</th>
                <td>${data.fullname}</td>
                <td>${data.phone}</td>
                <td>${data.address}</td>
                <td>${data.sex}</td>
                <td>${data.email}</td>        
                <td>
                    <button class="btn btn-primary" onclick="handleUpdateUser(${data.user_id})">Update</button>
                    <button class="btn btn-primary" onclick="handleDeleteUser(${data.user_id})">Delete</button>
                </td>
            </tr>`;
}

function renderUserByID(datas){
    inputFullname.value = datas.data.fullname;
    inputEmail.value = datas.data.email;
    inputPhone.value = datas.data.phone;
    inputAddress.value = datas.data.address;
    inputGender.value = datas.data.sex;
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
        renderUserByID(datas);
    })
    .catch(error => console.log(error));
}

function handleUpdateUser(id){
    getUserByID(id);
    modal.classList.add('open');
    ID = id;
}

btnConfirmUpdate.addEventListener('click', function(){
    let fullname = inputFullname.value;
    let email = inputEmail.value;
    let phone = inputPhone.value;
    let address = inputAddress.value;
    let sex = inputGender.value;
    let user = {
        fullname: fullname,
        email: email,
        phone: phone,
        address: address,
        sex: sex,
    }
    updateUser(user);
    hiddenUpdateUser();
});

function updateUser(data){
    let urlWithID = `${userUrl}${ID}`;
    let options = {
        method: 'PUT',
        headers:{
            'Authorization': `Bearer ${storedToken.accessToken}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }
    fetch(urlWithID, options)
    .then(function(res){
        res.json();
    })
    .then(function(){
        getUsers();
    })
}

// function handleDeleteUser(id){
//     let user_id = id;
//     let user = {
//         user_id: user_id
//     }
//     deleteUser(user);
// }

// function deleteUser(data){
//     let options = {
//         method: 'DELETE',
//         headers:{
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(data)
//     }
//     fetch(url, options)
//     .then(function(res){
//         res.json();
//     })
//     .then(function(){
//         getUsers();
//     })
// }

function hiddenUpdateUser(){
    modal.classList.remove('open');
}
// JavaScript Document