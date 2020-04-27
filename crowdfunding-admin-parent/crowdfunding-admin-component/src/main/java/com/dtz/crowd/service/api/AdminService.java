package com.dtz.crowd.service.api;

import com.dtz.crowd.entity.Admin;

import java.util.List;

public interface AdminService {

    void saveAdmin(Admin admin) throws Exception;

    List<Admin> getAll();
}
