// JavaScript Document
const accUrl = 'http://localhost:8080/StoreToys-API/account/';
const name = document.getElementById('account');
const modal = document.querySelector('.js-modal');
const inputCategory = document.getElementById('category-name');
const btnConfirmUpdate = document.getElementById('update');
var storedToken = JSON.parse(localStorage.getItem('tokens'));   
var index = 0;
var ID = 0;

function start(){
	getAccount();
}
start();
function getAccount(){
    let options = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        }
    }
	fetch(accUrl, options)
	.then(function(res){
		return res.json();
	})	
	.then(function(datas) {
		index = 0;
		var htmls = datas.data.map(renderAccount);
		var html = htmls.join('');
		name.innerHTML = html;
	})
	.catch(error => console.log(error));
}
function renderAccount(data){
	let stt = ++index;
	return `<tr>
				<th scope="row">${stt}</th>
				<td>${data.username}</td>
                <td>${data.password}</td>
                <td>${data.role_name}</td>
				<td>
                    <button class="btn btn-primary" onclick="handleUpdateCategory(${data.account_id})">Detail</button>
					<button class="btn btn-primary" onclick="handleUpdateCategory(${data.account_id})">Update</button>
					<button class="btn btn-primary" onclick="handleDeleteCategory(${data.account_id})">Delete</button>
				</td>
			</tr>`;
}
function renderCategoryByID(datas){
	inputCategory.value = datas.data.category_name;
}
function getCategoryByID(id){
	let catUrlWithID = `${catUrl}${id}`;
	fetch(catUrlWithID)
	.then(function(res){
		return res.json();
	})
	.then(function(datas){
		renderCategoryByID(datas);
	})
	.catch(error => console.log(error));
}

function handleUpdateCategory(id){
	getCategoryByID(id);
	modal.classList.add('open');
	ID = id;
};

btnConfirmUpdate.addEventListener('click', function(){
		let category_id = ID;
		let category_name = document.querySelector('input[name="input-category-name"]').value;
		let category = {
			category_id: category_id,
			category_name: category_name
		}
	updateCategory(category);
	hiddenUpdateCategory();
	});

function updateCategory(data){
	let options = {
		method: 'PUT',
		headers:{
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	}
	fetch(catUrl, options)
	.then(function(){
		getCategory();
	})
}
function handleDeleteCategory(id){
	let category_id = id;
	let category = {
		category_id: category_id
	}
	deleteCategory(category);
}
function deleteCategory(data){
	let options = {
		method: 'DELETE',
		headers:{
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	}
	fetch(catUrl, options)
	.then(function(res){
		res.json();
	})
	.then(function(){
		getCategory();
	})
}

function hiddenUpdateCategory(){
	modal.classList.remove('open');
}