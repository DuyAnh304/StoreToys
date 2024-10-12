const braUrl = 'http://localhost:8081/StoreToys-API/brand/';
const imgUrl = 'http://localhost:8081/StoreToys-API/img/'
const updateForm = document.getElementById('brand-update-form');
const token = localStorage.getItem('tokens');
const name = document.getElementById('brand'); 
const modal = document.querySelector('.js-modal');
const inputBrandName = document.getElementById('brand-name'); 
const inputBrandImg = document.getElementById('brand-img'); 
const btnConfirmUpdate = document.getElementById('update');
var storedToken = JSON.parse(localStorage.getItem('tokens'));
var index = 0;
var ID = 0;

// Hàm bắt đầu
function start(){    
    getBrand();
}
start();

// Hàm lấy danh sách thương hiệu
function getBrand(){
    fetch(braUrl)
    .then(function(res){
        return res.json();
    })  
    .then(function(datas) {
        index = 0;
        var htmls = datas.data.map(renderBrand);
        var html = htmls.join('');
        name.innerHTML = html;
    })
    .catch(error => console.log(error));
}

// Hàm hiển thị thông tin thương hiệu
function renderBrand(data){
    console.log(data.brand_name)
    let stt = ++index;
    return `<tr>
                <th scope="row">${stt}</th>
                <td>${data.brand_name}</td>
                <td><img src="${imgUrl}${data.brand_img}" alt="" style="max-width: 100px; max-height: 100px;"></td>
                <td>
                    <button class="btn btn-primary" onclick="handleUpdateBrand(${data.brand_id})">Update</button>
                    <button class="btn btn-primary" onclick="handleDeleteBrand(${data.brand_id})">Delete</button>
                </td>
            </tr>`;
}

//Sửa brand
// Hàm lấy thông tin thương hiệu theo ID
function getBrandByID(id){
    let urlWithID = `${braUrl}${id}`;
    fetch(urlWithID)
    .then(function(res){
        return res.json();
    })
    .then(function(datas){
        console.log(datas)
        renderBrandByID(datas);
    })
    .catch(error => console.log(error));
}

function renderBrandByID(datas){
	inputBrandName.value = datas.data.brand_name;
}

// Hàm bắt đầu cập nhật thông tin thương hiệu
function handleUpdateBrand(id){
    getBrandByID(id);
    modal.classList.add('open');
    ID = id;
};

// Sự kiện click nút cập nhật
btnConfirmUpdate.addEventListener('click', function(event){
    event.preventDefault();
    let brand = new FormData(updateForm);
    updateBrand(brand);
    hiddenUpdateBrand();
});

// Hàm cập nhật thông tin thương hiệu
function updateBrand(data){
    let url = `${braUrl}${ID}`;
    let options = {
        method: 'PUT',
        headers:{
            'Authorization': `Bearer ${storedToken.accessToken}`
        },
        body: data
    }
    fetch(url, options)
    .then(function(res){
        res.json();
    })
    .then(function(){
        getBrand();
    })
}

// Hàm bắt đầu xóa thông tin thương hiệu
function handleDeleteBrand(id){
    deleteBrand(id);
}

// Hàm xóa thông tin thương hiệu
function deleteBrand(id){
    const storedToken = JSON.parse(localStorage.getItem('tokens'));
    let url = `${braUrl}${id}`;
    let options = {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        }
    }
    fetch(url, options)
    .then(function(res){
        res.json();
    })
    .then(function(){
        getBrand();
    })
}

// Hàm ẩn modal cập nhật thương hiệu
function hiddenUpdateBrand(){
    modal.classList.remove('open');
}
