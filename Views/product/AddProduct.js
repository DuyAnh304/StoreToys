// JavaScript Document
const proUrl = 'http://localhost:8080/StoreToys-API/product';
const catUrl = 'http://localhost:8080/StoreToys-API/category';
const braUrl = 'http://localhost:8080/StoreToys-API/brand';
const createForm = document.getElementById('product-create-form');
const tokens = localStorage.getItem('tokens');
function start() {
    handleCreateForm();
    fetchCategoriesAndBrands();
}

start();

function createProduct(data) {
    let storedToken = JSON.parse(localStorage.getItem('tokens'));
    console.log(storedToken.accessToken)
    let options = {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        },
        body: data
    }
    fetch(proUrl, options)
	.then(function(res){
		return res.json();
	})
	.then(function(datas){
		console.log(datas);
        window.location.href = "http://localhost/StoreToys-FE/Views/product/ProductManagerment.html";
	})
}

function handleCreateForm() {
    let createBtn = document.getElementById('btnSubmit');
    createBtn.onclick = function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của nút gửi
        
        let product_image = document.querySelector('input[name="product_img"]').files[0];
        if (product_image) {
            let data = new FormData(createForm);            
            // Gọi hàm tạo thương hiệu với dữ liệu đã được chọn
            createProduct(data);
        } else {
            alert('Vui lòng chọn hình ảnh');
        }
    };
}

function fetchCategoriesAndBrands() {
    fetch(catUrl)
        .then(response => response.json())
        .then(categories => {
           
            const categorySelect = document.querySelector('select[name="category_id"]');
            categories.data.forEach(category => {
              console.log(category)
                const option = document.createElement('option');
                option.value = category.category_id;
                option.textContent = category.category_name;
                categorySelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching categories:', error));

    fetch(braUrl)
        .then(response => response.json())
        .then(brands => {
            const brandSelect = document.querySelector('select[name="brand_id"]');
            brands.data.forEach(brand => {
                const option = document.createElement('option');
                option.value = brand.brand_id;
                option.textContent = brand.brand_name;
                brandSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching brands:', error));
}



