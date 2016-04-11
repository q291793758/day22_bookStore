package cn.itcast.domain;
//分页:封装查询信息
public class QueryInfo {
    private int currentpage=1;  //当前第几页
    private int pagesize=3;     //每页显示多少数据
    private int startIndex;     //数据库查询起始点
    private String categoryId;  //书归属分类id

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getStartIndex() {
        this.startIndex = (this.currentpage-1)*this.pagesize;
        return startIndex;
    }

    public String getCategoryId() {
        if (categoryId != null) {
            return categoryId;
        } else {
            return null;
        }
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
