document.addEventListener("DOMContentLoaded", () => {
    const menuButton = document.getElementById("menu-toggle");
    const sideMenu = document.getElementById("side-menu");

    // 메뉴 버튼 클릭 시 슬라이드 메뉴 활성화
    menuButton.addEventListener("click", () => {
        sideMenu.classList.toggle("active");
    });

    // 메뉴 외부 클릭 시 자동 닫기
    document.addEventListener("click", (event) => {
        if (!menuButton.contains(event.target) && !sideMenu.contains(event.target)) {
            sideMenu.classList.remove("active");
        }
    });
});