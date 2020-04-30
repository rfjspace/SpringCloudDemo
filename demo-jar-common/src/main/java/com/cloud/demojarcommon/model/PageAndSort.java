package com.cloud.demojarcommon.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageAndSortReq {
    private Integer page;
    private Integer size;
    private List<PageAndSortReq.Order> sort;

    public PageAndSortReq() {
    }

    public Pageable getPageable() {
        List<org.springframework.data.domain.Sort.Order> sortOrders = new ArrayList();
        if (this.page == null) {
            this.page = 0;
        }

        if (this.size == null) {
            this.size = 20;
        }

        if (this.sort != null && this.sort.size() > 0) {
            Iterator var2 = this.sort.iterator();

            while(var2.hasNext()) {
                PageAndSortReq.Order order = (PageAndSortReq.Order)var2.next();
                sortOrders.add(new org.springframework.data.domain.Sort.Order(Direction.valueOf(order.getDirection()), order.getProperty()));
            }

            return new PageRequest(this.page, this.size, new Sort(sortOrders));
        } else {
            return new PageRequest(this.page, this.size);
        }
    }

    public Integer getPage() {
        return this.page;
    }

    public PageAndSortReq setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return this.size;
    }

    public PageAndSortReq setSize(Integer size) {
        this.size = size;
        return this;
    }

    public List<PageAndSortReq.Order> getSort() {
        return this.sort;
    }

    public PageAndSortReq setSort(List<PageAndSortReq.Order> sort) {
        this.sort = sort;
        return this;
    }

    public static class Order {
        private String direction;
        private String property;

        public Order() {
        }

        public String getDirection() {
            return this.direction;
        }

        public PageAndSortReq.Order setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public String getProperty() {
            return this.property;
        }

        public PageAndSortReq.Order setProperty(String property) {
            this.property = property;
            return this;
        }
    }
}
