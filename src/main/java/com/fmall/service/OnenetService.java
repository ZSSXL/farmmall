package com.fmall.service;

import com.fmall.pojo.Enviroment;
import com.fmall.pojo.Livestock;
import com.fmall.pojo.Logistics;

public interface OnenetService {
    void insertById(Double id);

    Logistics select(int i);

    void sava(Livestock liveStock);

    void save_box(Logistics logistics);

    void save_environment(Enviroment enviroment);
}
