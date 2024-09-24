const url = 'http://localhost:8080/StoreToys-API/auth/access';

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    if (username.trim() === '' || password.trim() === '') {
        alert('Vui lòng nhập đầy đủ tài khoản và mật khẩu.');
        return;
    }
    const data = {
        username: username,
        password: password
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    fetch(url, options)
        .then(response => response.json())
        .then(tokens => {
            if (tokens.message) {
                alert(tokens.message);
            } else {
                localStorage.setItem('tokens', JSON.stringify(tokens));
                const storedToken = JSON.parse(localStorage.getItem('tokens'));
                console.log(storedToken);
                if (storedToken.role === "ADMIN") {
                    window.location.href = 'http://localhost/StoreToys-FE/Views/product/ProductManagerment.html';
                } else {
                    window.location.href = 'http://localhost/StoreToys-FE/Views/home/Home.html';
                }
            }
        })
        .catch(error => console.error(error));
}
