// JavaScript Document
window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    fetch(`http://localhost/StoreToys-BE/API/product?id=${productId}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById("productImage").src = `../../${product.product_img}`;
            document.querySelector(".product-name").textContent = product.product_name;
            document.querySelector(".product-price").textContent = `Giá: ${product.product_price} VNĐ`;
            document.querySelector(".product-category").textContent = `Danh mục: ${product.category_name}`;
            document.querySelector(".product-brand").textContent = `Thương hiệu: ${product.brand_name}`;
            document.querySelector(".product-sex").textContent = `Giới tính: ${product.product_sex}`;

            const addToCartBtn = document.getElementById("addToCartBtn");
            addToCartBtn.addEventListener('click', function() {
                addToCart(product.product_id);
            });
        })
        .catch(error => console.error('Error:', error));

        function addToCart(productId) {
            fetch('http://localhost/StoreToys-BE/API/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({product_id: productId, quantity: 1 }) 
            })
            .then(response => {
                if (response.ok) {
                    alert('Đã thêm sản phẩm vào giỏ hàng thành công!');
                } else {
                    alert('Thêm sản phẩm vào giỏ hàng thất bại!');
                }
            })
            .catch(error => console.error('Error:', error));
        }

};