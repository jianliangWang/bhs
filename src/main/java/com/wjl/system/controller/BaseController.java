package com.wjl.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

public class BaseController<T> {

    @Autowired
    private HttpServletRequest request;

    protected Page<T> getPage() {
        int current = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        return new Page<>(current, pageSize);
    }
}
