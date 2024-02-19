document.addEventListener('DOMContentLoaded', function() {
    var btn = document.getElementById('create-btn');
    if (btn) { // btn이 실제로 존재하는지 확인
        btn.addEventListener('click', function() {
            var url = this.getAttribute('data-url');
            window.location.href = url;
        });
    }
});