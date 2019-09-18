package entity;


import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * new Result(true,20000,"xxxxxx",new PageResult(100,list))
 * @param <T>
 */
public class PageResult<T> implements Serializable {

    private Long total;
    private List<T> row;

    public PageResult() {
    }

    public PageResult(Long total, List<T> row) {
        this.total = total;
        this.row = row;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }
}
