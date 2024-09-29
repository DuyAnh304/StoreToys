const url = 'http://localhost:8080/StoreToys-API/brand';
const tokens = localStorage.getItem('tokens');
const updateForm = document.getElementById('brand-create-form');
function start() {
    handleCreateForm();
}

start();

function createBrand(data) {
    let storedToken = JSON.parse(localStorage.getItem('tokens'));
    let options = {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${storedToken.accessToken}`
        },
        body: data
    };
    
    fetch(url, options)
    .then(function(res) {
        return res.json();
    })
    .then(function(datas) {
        console.log(datas);
        // Chuyển hướng về trang quản lý thương hiệu sau khi thêm thành công
        window.location.href = "http://localhost/StoreToys-FE/Views/brand/BrandManagerment.html";
    })
    .catch(function(error) {
        console.log(error);
    });
}

function handleCreateForm() {
    let createBtn = document.querySelector('button[name="submit"]');
    createBtn.addEventListener('click', function(event) {
        event.preventDefault();
        let image = document.querySelector('input[name="brand_image"]').files[0]; // Lấy file hình ảnh thương hiệu
        // Kiểm tra xem người dùng đã chọn hình ảnh hay chưa
        if (image) {
            let data = new FormData(updateForm);            
            // Gọi hàm tạo thương hiệu với dữ liệu đã được chọn
            createBrand(data);
        } else {
            alert('Vui lòng chọn hình ảnh');
        }
    });
}
