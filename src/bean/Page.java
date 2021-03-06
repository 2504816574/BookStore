package bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {


    //   5/19
    private int pageNo;                        //当前页码					用户
    private int totalPageNo;                //总页数=总条数/每页显示的个数	计算
    private int totalRecord;                //总条数					dao,sql:select count(*) from books
    public static final int PAGE_SIZE = 4;    //每页显示的个数				静态常量
    private List<T> list;                    //当前页的数据集合			dao,sql:SELECT * FROM books LIMIT (pageNo-1)*PAGE_SIZE,PAGE_SIZE

    public int getPageNo() {
        if (pageNo < 1) {//如果输入小于0，默认1
            return 1;
        }
        if (pageNo > getTotalPageNo()) {//如果输入大于总页数，默认总页数（即最后一页）
            return getTotalPageNo();
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 计算总页数
     *
     * @return
     */
    public int getTotalPageNo() {
        return totalRecord % PAGE_SIZE == 0 ? totalRecord / PAGE_SIZE : totalRecord / PAGE_SIZE + 1;
    }

    public void setTotalPageNo(int totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //	public static int getPageSize() {
//		return PAGE_SIZE;
//	}
    public Page(int pageNo, int totalPageNo, int totalRecord, List<T> list) {
        super();
        this.pageNo = pageNo;
        this.totalPageNo = totalPageNo;
        this.totalRecord = totalRecord;
        this.list = list;
    }
    public Page(int pageNo) {
        this.pageNo = pageNo;
    }

    public Page() {
        super();
    }

    @Override
    public String toString() {
        return "Page [pageNo=" + pageNo + ", totalPageNo=" + totalPageNo + ", totalRecord=" + totalRecord + ", list="
                + list + "]";
    }

}
