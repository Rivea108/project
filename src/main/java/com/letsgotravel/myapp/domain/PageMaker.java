package com.letsgotravel.myapp.domain;

import org.springframework.stereotype.Component;

@Component
public class PageMaker {
    public PageMaker() {
        System.out.println("PageMaker 객체 생성됨!");
    }

    private int displayPageNum = 10; //한 그룹에 보여줄 페이지 개수 (10개씩)
    private int startPage;
    private int endPage;
    private int totalCount;
    private boolean prev;
    private boolean next;
    private Criteria cri = new Criteria(); // 기본값 설정 (null 방지)

    public int getDisplayPageNum() { return displayPageNum; }
    public void setDisplayPageNum(int displayPageNum) { this.displayPageNum = displayPageNum; }

    public int getStartPage() { return startPage; }
    public int getEndPage() { return endPage; }
    public boolean isPrev() { return prev; }
    public boolean isNext() { return next; }
    public int getTotalCount() { return totalCount; }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData(); //totalCount 설정 시 자동 계산
    }

    public Criteria getCri() { return cri; }
    
    public void setCri(Criteria cri) {
        this.cri = (cri != null) ? cri : new Criteria(); //null 체크 및 기본값 설정
    }

    private void calcData() {
        //현재 페이지가 속한 그룹의 마지막 페이지 계산
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        
        //현재 그룹의 첫 번째 페이지
        startPage = (endPage - displayPageNum) + 1;
        if (startPage < 1) startPage = 1;

        //전체 페이지 개수 계산
        int totalPage = (int) Math.ceil((double) totalCount / cri.getPerPageNum());

        //실제 마지막 페이지가 endPage보다 작으면 변경
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        //이전 버튼 (1페이지 그룹이 아니면 존재)
        prev = startPage > 1;

        //다음 버튼 (마지막 페이지 그룹이 아니면 존재)
        next = endPage < totalPage;
    }
}
