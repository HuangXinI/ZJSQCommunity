package com.zjsq.community.entity;

/**
 * 封装分页相关的信息
 * (服务器和浏览器都需要的数据)
 */
public class Page {

    //当前页码
    private int current = 1;//默认为1
    //显示的上限
    private int limit = 10;
    //数据的总数(用于计算总页)
    private int rows;
    //查询路径(复用分页的链接)
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行(数据库需要)
     *
     * @return
     */
    public int getOffset() {
        //(current-1)*limit
        return (current - 1) * limit;
    }

    /**
     * 获取总的页数
     *
     * @return
     */
    public int getTotal() {
        //rows / limit
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return (rows / limit) + 1;
        }
    }

    /**
     * 获取需要显示的起始页码
     * @return
     */
    public int getFrom(){
        int from = current - 2;
        if(from < 1) {
            return 1;
        }
        return from;
    }

    /**
     * 获取结束页码
     * @return
     */
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return Math.min(to,total);
    }
}
