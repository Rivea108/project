document.addEventListener('DOMContentLoaded', () => {
    const dropdownBtn = document.querySelector('.dropdown-btn');
    const dropdownMenu = document.querySelector('.dropdown-menu');
  
    // 버튼 클릭 시 드롭다운 메뉴 토글
    dropdownBtn.addEventListener('click', () => {
      dropdownMenu.classList.toggle('visible');
    });
  
    // 메뉴 외부 클릭 시 드롭다운 메뉴 숨기기
    document.addEventListener('click', (event) => {
      if (!dropdownBtn.contains(event.target) && !dropdownMenu.contains(event.target)) {
        dropdownMenu.classList.remove('visible');
      }
    });
  });