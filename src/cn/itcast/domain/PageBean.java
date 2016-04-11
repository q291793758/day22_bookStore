package cn.itcast.domain;

import java.util.List;
//分页:封装了分页显示信息.
public class PageBean {
    private List booklist;  //保存要在页面显示书的集合,查询数据库获得
    private int[] pagebar;  //页码条 根据总页码和每页显示多少计算
    private int totalrecord;  //数据库总共多少书 ,查询数据库获得
    private int pagesize;       //每页显示多少本书,从页面带过来
    private int currentpage;    //当前页,从页面带过来
    private int totalpage;      //总共多少页,通过totalrecord和pagesize计算
    private int previouspage;   //上一页 当前页-1
    private int nextpage;     //下一页  当前页+1

    public List getBooklist() {
        return booklist;
    }

    public void setBooklist(List booklist) {
        this.booklist = booklist;
    }
    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int[] getPagebar() {
        int startpage;
        int endpage;
        //固定每页显示10个页码条,如果小于,都显示
        if (this.totalpage <= 10) {
            startpage = 1;
            endpage = totalpage;
        } else { //总页码>10
            startpage = currentpage - 4;
            endpage = currentpage + 5;
            if (totalpage < 1) {
                startpage = 1;
                endpage = 10;
            }
            if (endpage > totalpage) {
                startpage = totalpage - 9;
                endpage = totalpage;
            }
        }
        this.pagebar = new int[endpage - startpage + 1];
        int index = 0;
        for (int i = startpage; i <= endpage; i++) {
            this.pagebar[index] = i;
            index++;
        }
        return pagebar;
    }

    public int getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(int totalrecord) {
        this.totalrecord = totalrecord;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotalpage() {
        if(this.totalrecord%this.pagesize==0){
            this.totalpage = this.totalrecord/this.pagesize;
        }else{
            this.totalpage = this.totalrecord/this.pagesize + 1;
        }
        return totalpage;

    }

    public int getPreviouspage() {
        if (this.currentpage - 1 <1) {
            this.previouspage = 1;
            return previouspage;
        }
        return this.currentpage-1;
    }

    public int getNextpage() {
        if (this.currentpage+1 > this.totalpage) {
            this.nextpage = totalpage;
            return nextpage;
        }
        return nextpage + 1;
    }

}
